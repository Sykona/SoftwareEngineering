import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Reconstructor {
	
	  public void reconstruct(Class c, PrintStream ps) {

		  
		  
	  }
	  
	  private String getClassDeclaration(Class c) {
		  
		  StringBuilder classHeader = new StringBuilder();
		  
		  int modifiers = c.getModifiers();
		  
		  if (Modifier.isPublic(modifiers)) classHeader.append("public");
		  if (Modifier.isPrivate(modifiers)) classHeader.append("private");
		  if (Modifier.isProtected(modifiers)) classHeader.append("protected");
		  if (Modifier.isAbstract(modifiers)) classHeader.append("abstract");
		  if (Modifier.isNative(modifiers)) classHeader.append("native");
		  if (Modifier.isSynchronized(modifiers)) classHeader.append("synchronized");
		  if (Modifier.isTransient(modifiers)) classHeader.append("transient");
		  if (Modifier.isVolatile(modifiers)) classHeader.append("volatile");
		  if (Modifier.isStrict(modifiers)) classHeader.append("strict");
		  if (Modifier.isStatic(modifiers)) classHeader.append("static");
		  if (Modifier.isFinal(modifiers)) classHeader.append("final");
		  if (Modifier.isInterface(modifiers)) classHeader.append("interface"); else classHeader.append("class");
		  
		  classHeader.append(c.getSimpleName());
		 
		  
		  return classHeader.toString();
	  }
	  
//	  public void reconstruct(String fullClassname, PrintStream ps) throws ClassNotFoundException;
	  
}
