import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashSet;

/**
 * Reconstructor tries to reconstruct a .java file from an existing class.
 * Handled:
 * 			Package
 * 			Imports
 * 			Member Variables (modifiers, types, names)
 * 			Constructors (modifiers, parameters, exceptions)
 * 			Methods (modifiers, parameters, exceptions, return types)
 *  
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */

public class Reconstructor {
	
	
	/**
	 * Reconstructs a given class into a .java file
	 * "Dummy" is append to the class name and constructor name, to avoid overwriting
	 *
	 * @param c the given class
	 * @param ps the printstream
	 */
	public void reconstruct(Class c, PrintStream ps) {

		
		/* ******** package ******** */
		
		if (c.getPackage() != null)
			ps.print("package " + c.getPackage().getName() + ";\n\n");

		
		/* ******** imports ******** */
		for (Class importClass : getImports(c))
			ps.println("import " + importClass.getCanonicalName() + ";");
		
		ps.print("\n");

		
		/* ******** class header ******** */
		int classModifiers = c.getModifiers();		
		ps.print(getModifierString(classModifiers));
		
		if (Modifier.isInterface(classModifiers)) 
			ps.print("interface " + c.getSimpleName() + "Dummy ");
		else 
			ps.print("class " + c.getSimpleName() + "Dummy ");
		
		if (c.getSuperclass() != null)
			ps.print("extends " + c.getSuperclass().getSimpleName() + " ");
		
		if (c.getInterfaces().length > 0)
			ps.print("implements " + getInterfacesString(c));
		
		ps.print("{\n\n");
		

		/* ******** instance / class variables ******** */
		Field[] fields = c.getDeclaredFields();

		for (Field f : fields) {
			
			String type = f.getType().getSimpleName();
			int modifiers = f.getModifiers();
			
			ps.print("\t" + getModifierString(modifiers));
			ps.print(type + " " + f.getName() + " = ");
			ps.print(getDefaultValue(type) + ";\n");
		}
		ps.print("\n\n");

		
		/* ******** constructors ******** */

		for (Constructor constructor : c.getDeclaredConstructors()) {
			
			int constructorModifiers = constructor.getModifiers();
			
			ps.print("\t");
			ps.print(getModifierString(constructorModifiers));
			ps.print(c.getSimpleName() + "Dummy");
			ps.print("(" + getParametersString(constructor) + ") ");
			ps.print(getExceptionsString(constructor) + "{\n");
			ps.print("\t\t");
			ps.print("System.out.println(\"constructor\");\n");
			ps.print("\t}\n\n");
		}

		
		/* ******** methods ******** */

		for (Method method : c.getDeclaredMethods()) {
			
			int methodModifiers = method.getModifiers();
			
			// dont print method if it overrides an existing final method (not allowed)
			if (!(Modifier.isFinal(methodModifiers) && !(method.getDeclaringClass().equals(c)))) {
			
				ps.print("\t");
				ps.print(getModifierString(methodModifiers));
				ps.print(method.getReturnType().getSimpleName() + " ");
				ps.print(method.getName());
				ps.print("(" + getParametersString(method) + ") ");
				ps.print(getExceptionsString(method) + "{\n");
				ps.print("\t\tSystem.out.println(\"" + method.getName() + "\");\n");
				
				if (!method.getReturnType().equals(Void.TYPE)) 
					ps.print("\t\treturn " + getDefaultValue(method.getReturnType().getSimpleName()) + ";\n");
				
				ps.print("\t}\n\n");
			}
		}
		
		
		ps.print("}");
	}

	

	/**
	 * Reconstructs from a given class name (canonical name) into a .java file
	 * "Dummy" is append to the class name, to avoid overwriting
	 *
	 * @param c the given class
	 * @param ps the printstream
	 *
	 * @throws ClassNotFoundException if the given class name cannot be found
	 */
	public void reconstruct(String fullClassname, PrintStream ps) throws ClassNotFoundException {
		
		Class myClass = Class.forName(fullClassname);
		reconstruct(myClass, ps);
	}

	
	/**
	 * Gets the imports.
	 *
	 * @param c the c
	 * @return the imports
	 */
	private HashSet<Class> getImports(Class c) {
		
		HashSet<Class> usedClasses = new HashSet<Class>();
		
		// add all used classes to a HashSet (except for Superclass)
		
		for (Method m : c.getDeclaredMethods())
			usedClasses.add(m.getReturnType());
		
		for (Class i : c.getInterfaces())
			usedClasses.add(i);
		
		for (Field f : c.getDeclaredFields())
			usedClasses.add(f.getType());
		
		for (Constructor con : c.getDeclaredConstructors())
			for (Class e : con.getExceptionTypes())
				usedClasses.add(e);
		
		for (Method m : c.getDeclaredMethods()) {
			
			if (!m.getReturnType().equals(Void.TYPE)) 
				usedClasses.add(m.getReturnType());
			
			for (Class p : m.getParameterTypes())
				usedClasses.add(p);
			
			for (Class e : m.getExceptionTypes())
				usedClasses.add(e);
		}
		
		
		/* 	found Classes are only imported if:
		 * 		they are not primitive types
		 * 		they are not in the same package as our given class
		 * 		they are not in the java.lang package
		 */
		HashSet<Class> imports = new HashSet<Class>();
		
		for (Class cl : usedClasses) {
			
			if(cl.getPackage() != null && c.getPackage() != null) {

				boolean notInSamePackage = !(cl.getPackage().getName().equals(c.getPackage().getName()));
				boolean notPrimitive = !(cl.isPrimitive());
				boolean notInJavaLangPackage = !(cl.getPackage().getName().equals("java.lang"));
				
				if (notInSamePackage && notPrimitive && notInJavaLangPackage)
					imports.add(cl);
			}
		}
		
		
		return imports;
	}


	/**
	 * Gets the interfaces string.
	 *
	 * @param c the c
	 * @return the interfaces string
	 */
	private String getInterfacesString(Class c) {

		StringBuilder interfacesSB = new StringBuilder();
		Class[] interfaces = c.getInterfaces();

		if (interfaces.length > 0) {
		
			for (Class i : interfaces) 
				interfacesSB.append(i.getSimpleName() + ", ");
				
			// delete the last comma
			interfacesSB.delete(interfacesSB.length() - 2, interfacesSB.length() - 1);
		}

		return interfacesSB.toString();
	}
	
	
	/**
	 * Gets the fields string.
	 *
	 * @param c the c
	 * @return the fields string
	 */
	private String getFieldsString(Class c) {

		Field[] fields = c.getDeclaredFields();
		StringBuilder fieldsSB = new StringBuilder();

		for (Field f : fields) {
			
			String type = f.getType().getSimpleName();
			int modifiers = f.getModifiers();
			fieldsSB.append("\t" + getModifierString(modifiers));

			fieldsSB.append(type + " " + f.getName() + " = ");
			fieldsSB.append(getDefaultValue(type) + ";\n");
		}

		return fieldsSB.toString();
	}
	

	/**
	 * Gets the parameters string.
	 *
	 * @param constructor the constructor
	 * @return the parameters string
	 */
	private String getParametersString(Constructor constructor) {

		StringBuilder parametersSB = new StringBuilder();			
		Parameter[] parameters = constructor.getParameters();

		if (parameters.length > 0) {
			
			for (Parameter param : parameters) {
				parametersSB.append(param.getType().getSimpleName() + " ");
				parametersSB.append(param.getName() + ", ");
			}
		
			// delete the last comma and space
			parametersSB.delete(parametersSB.length() - 2, parametersSB.length());
		}
		
		return parametersSB.toString();
	}

	
	/**
	 * Gets the parameters string.
	 *
	 * @param method the method
	 * @return the parameters string
	 */
	private String getParametersString(Method method) {

		StringBuilder parametersSB = new StringBuilder();			
		Parameter[] parameters = method.getParameters();
		
		if (parameters.length > 0) {
			
			for (Parameter param : parameters) {
				parametersSB.append(param.getType().getSimpleName() + " ");
				parametersSB.append(param.getName() + ", ");
			}

			// delete the last comma and space
			parametersSB.delete(parametersSB.length() - 2, parametersSB.length());
		}
		
		return parametersSB.toString();
	}
	
	
	/**
	 * Gets the exceptions string.
	 *
	 * @param constructor the constructor
	 * @return the exceptions string
	 */
	private String getExceptionsString(Constructor constructor) {
		
		StringBuilder exceptionsSB = new StringBuilder();
		Class[] exceptions = constructor.getExceptionTypes();
		
		if (exceptions.length > 0) {
			
			exceptionsSB.append("throws ");
			
			for (Class exception : exceptions)
				exceptionsSB.append(exception.getSimpleName() + ", ");
			
			// delete the last comma
			exceptionsSB.delete(exceptionsSB.length() - 2, exceptionsSB.length() - 1);	
		}
		
		return exceptionsSB.toString();
	}
	
	
	/**
	 * Gets the exceptions string.
	 *
	 * @param method the method
	 * @return the exceptions string
	 */
	private String getExceptionsString(Method method) {
		
		StringBuilder exceptionsSB = new StringBuilder();
		Class[] exceptions = method.getExceptionTypes();
		
		if (exceptions.length > 0) {
			
			exceptionsSB.append("throws ");
			
			for (Class exception : exceptions) 
				exceptionsSB.append(exception.getSimpleName() + ", ");
			
			// delete the last comma
			exceptionsSB.delete(exceptionsSB.length() - 2, exceptionsSB.length() - 1);	
		}
		
		return exceptionsSB.toString();
	}
	

	/**
	 * Gets the default value.
	 *
	 * @param typeName the type name
	 * @return the default value
	 */
	private String getDefaultValue(String typeName) {

		if (typeName.equals("byte"))
			return "0";
		if (typeName.equals("short"))
			return "0";
		if (typeName.equals("int"))
			return "0";
		if (typeName.equals("long"))
			return "0L";
		if (typeName.equals("char"))
			return "'\u0000'";
		if (typeName.equals("float"))
			return "0.0F";
		if (typeName.equals("double"))
			return "0.0";
		if (typeName.equals("boolean"))
			return "false";
		if (typeName.equals("void"))
			return "void";
		else
			return "null";
	}

	
	/**
	 * Gets the modifier string.
	 *
	 * @param modifiers the modifiers
	 * @return the modifier string
	 */
	private String getModifierString(int modifiers) {

		StringBuilder modStringBuilder = new StringBuilder();

		if (Modifier.isPublic(modifiers))
			modStringBuilder.append("public ");
		if (Modifier.isPrivate(modifiers))
			modStringBuilder.append("private ");
		if (Modifier.isProtected(modifiers))
			modStringBuilder.append("protected ");
		if (Modifier.isAbstract(modifiers))
			modStringBuilder.append("abstract ");
		if (Modifier.isNative(modifiers))
			modStringBuilder.append("native ");
		if (Modifier.isSynchronized(modifiers))
			modStringBuilder.append("synchronized ");
		if (Modifier.isTransient(modifiers))
			modStringBuilder.append("transient ");
		if (Modifier.isVolatile(modifiers))
			modStringBuilder.append("volatile ");
		if (Modifier.isStrict(modifiers))
			modStringBuilder.append("strict ");
		if (Modifier.isStatic(modifiers))
			modStringBuilder.append("static ");
		if (Modifier.isFinal(modifiers))
			modStringBuilder.append("final ");

		return modStringBuilder.toString();
	}
}
