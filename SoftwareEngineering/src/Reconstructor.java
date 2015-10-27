import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Reconstructor {
	
	
	public void reconstruct(Class c, PrintStream ps) {

		ps.print(getClassPackage(c) + "\n\n");

		ps.print(getClassImports(c) + "\n\n");

		ps.print(getClassHeader(c) + "{\n\n");

		ps.print(getClassFields(c) + "\n\n");

		ps.print(getClassConstructors(c) + "\n");

		ps.print(getClassMethods(c));

		ps.print("}");

	}

	// public void reconstruct(String fullClassname, PrintStream ps) throws
	// ClassNotFoundException;

	private String getClassPackage(Class c) {

		return "package " + c.getPackage().getName();

	}

	private String getClassHeader(Class c) {

		StringBuilder classHeader = new StringBuilder();

		int modifiers = c.getModifiers();

		classHeader.append(getModifierString(modifiers));
		if (Modifier.isInterface(modifiers))
			classHeader.append("interface ");
		else
			classHeader.append("class ");

		classHeader.append(c.getSimpleName() + "Dummy ");

		// check if the class extends another class and add "extends $SuperClassName " to the header
		if (c.getSuperclass() != null)
			classHeader.append("extends " + c.getSuperclass().getSimpleName() + " ");

		// check if the class implements any interfaces and add "implements $interfaceName " to the header
		Class[] interfaces = c.getInterfaces();

		if (interfaces.length > 0) {
			classHeader.append("implements ");
			for (Class i : interfaces) {
				classHeader.append(i.getSimpleName() + ", ");
			}
			// delete the last comma
			classHeader.delete(classHeader.length() - 2, classHeader.length() - 1);
		}

		return classHeader.toString();
	}

	private String getClassImports(Class c) {
		
		StringBuilder classImports = new StringBuilder();
		boolean accessible = false;

		
		for (Class i : c.getInterfaces()) {
			
			if (accessible)
				classImports.append("import " + i.getName() + ";\n");
			
			accessible = false;
		}

		return classImports.toString();
	}


	private String getClassFields(Class c) {

		Field[] fields = c.getDeclaredFields();
		StringBuilder classFields = new StringBuilder();

		for (Field f : fields) {

			classFields.append("\t");

			int modifiers = f.getModifiers();

			classFields.append(getModifierString(modifiers));

			classFields.append(f.getType().getSimpleName() + " " + f.getName() + " = "
					+ getDefaultValue(f.getType().getSimpleName()) + "\n");
		}

		return classFields.toString();
	}

	private String getClassConstructors(Class c) {

		StringBuilder classConstructors = new StringBuilder();
		StringBuilder constructorParameters = new StringBuilder();

		Constructor[] constructors = c.getDeclaredConstructors();

		for (Constructor con : constructors) {

			int args = 0;
			Parameter[] params = con.getParameters();

			for (int i = 0; i < params.length; i++) {

				constructorParameters.append(params[i].getType().getSimpleName());

				if (i != params.length - 1)
					constructorParameters.append(" arg" + args + ", ");
				else
					constructorParameters.append(" arg" + args);

				args++;
			}

			int modifiers = con.getModifiers();

			classConstructors.append("\t" + getModifierString(modifiers) + con.getName() + " ("
					+ constructorParameters.toString() + ") {\n");
			classConstructors.append("\t\tSystem.out.println(\"constructor\");\n\t}\n\n");
			constructorParameters.setLength(0);
		}

		return classConstructors.toString();
	}

	private String getClassMethods(Class c) {

		StringBuilder classMethods = new StringBuilder();
		StringBuilder methodParameters = new StringBuilder();

		Method[] methods = c.getDeclaredMethods();

		for (Method m : methods) {

			int args = 0;
			Parameter[] params = m.getParameters();

			for (int i = 0; i < params.length; i++) {

				methodParameters.append(params[i].getType().getSimpleName());

				if (i != params.length - 1)
					methodParameters.append(" arg" + args + ", ");
				else
					methodParameters.append(" arg" + args);

				args++;
			}

			Class[] exceptions = m.getExceptionTypes();
			StringBuilder methodExceptions = new StringBuilder();

			if (exceptions.length > 0) {
				methodExceptions.append("throws ");
				for (Class i : exceptions) {
					methodExceptions.append(i.getSimpleName() + ", ");
				}
				// delete the last comma
				methodExceptions.delete(methodExceptions.length() - 2, methodExceptions.length() - 1);
			}

			int modifiers = m.getModifiers();

			classMethods.append("\t" + getModifierString(modifiers) + m.getReturnType().getSimpleName());
			classMethods.append(" " + m.getName() + " (" + methodParameters.toString() + ") " + methodExceptions.toString() + "{\n");
			classMethods.append("\t\tSystem.out.println(\"" + m.getName() + "\");\n");
			
			if (!m.getReturnType().equals(Void.TYPE))
				classMethods.append("\t\treturn " + getDefaultValue(m.getReturnType().getSimpleName()) + ";\n\t}\n\n");
			else
				classMethods.append("\t}\n\n");
			
			methodParameters.setLength(0);
		}

		return classMethods.toString();
	}

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
