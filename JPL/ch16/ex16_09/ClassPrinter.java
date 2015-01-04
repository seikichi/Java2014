package ch16.ex16_09;

import java.util.Set;
import java.util.HashSet;

import java.lang.reflect.*;
import java.lang.annotation.*;

public final class ClassPrinter {
  private java.io.PrintStream out = System.out;
  private Class<?> outerKlass;
  private String packageName;

  public ClassPrinter(Class<?> klass) {
    this.outerKlass = klass;
    packageName = klass.getPackage().getName();
  }

  public void printPackage() {
    out.println(String.format("package %s;", packageName));
    out.println();
  }

  public void printAnnotations(AnnotatedElement elem, int indent) {
    Annotation[] annotations = elem.getAnnotations();
    for (Annotation annot : annotations) {
      printIndent(indent);
      out.println(stripPackageName(annot.toString()));
    }
  }

  public void printModifiers(int modifiers) {
    String modifierString = Modifier.toString(modifiers);
    modifierString = modifierString.replaceAll("interface\\s+", "");
    modifierString = modifierString.replaceAll("\\s+interface", "");
    out.print(modifierString);
    if (modifierString.length() > 0) { out.print(" "); }
  }

  public void printType(Class<?> klass) {
    String typeString = klass.isAnnotation() ? "@interface" :
      klass.isEnum() ? "enum" :
      klass.isInterface() ? "interface" : "class";
    out.print(String.format("%s ", typeString));
  }

  public void printTypeIdentifier(Class<?> klass) {
    out.print(klass.getSimpleName());
  }

  public void printTypeParameters(GenericDeclaration type) {
    TypeVariable<?>[] params = type.getTypeParameters();
    if (params.length > 0) {
      out.print('<');
      for (int i = 0; i < params.length; i++) {
        out.print(params[i]);
        if (i < params.length - 1) { out.print(", "); }
      }
      out.print('>');
    }
  }

  public String typeName(Type type) {
    String str = type.getTypeName();
    str = str.replaceAll("java\\.lang\\.", "");
    str = str.replaceAll(packageName + "\\.", "");
    str = str.replaceAll(this.outerKlass.getSimpleName() + "\\.", "");
    return str;
  }

  public void printSuperClass(Class<?> klass) {
    Type superKlass = klass.getGenericSuperclass();
    if (superKlass != null && superKlass != Object.class) {
      out.print(String.format("\n    extends %s", typeName(superKlass)));
    }
  }

  public void printInterfaces(Class<?> klass) {
    Type[] ifs = klass.getGenericInterfaces();
    for (Type iface : ifs) {
      out.print(String.format("\n    implements %s", typeName(iface)));
    }
  }

  public String stripPackageName(String str) {
    str = str.replaceAll("java\\.lang\\.", "");
    str = str.replaceAll(packageName + "\\.", "");
    str = str.replaceAll(this.outerKlass.getSimpleName() + "\\.", "");
    return str;
  }

  public void printIndent(int indent) {
    for (int i = 0; i < indent; i++) {
      out.print(" ");
    }
  }

  public void printConstructors(Class<?> klass, int indent) {
    Constructor[] ctors = klass.getDeclaredConstructors();
    for (Constructor ctor : ctors) {
      printAnnotations(ctor, indent);
      printIndent(indent);
      out.print(stripPackageName(ctor.toGenericString()));
      out.println(";");
    }
  }

  public void printFields(Class<?> klass, int indent) {
    Field[] fields = klass.getDeclaredFields();
    for (Field field : fields) {
      printAnnotations(field, indent);
      printIndent(indent);
      out.print(stripPackageName(field.toGenericString()));
      out.println(";");
    }
  }

  public void printMethods(Class<?> klass, int indent) {
    Method[] methods = klass.getDeclaredMethods();
    for (Method method : methods) {
      printAnnotations(method, indent);
      printIndent(indent);
      out.print(stripPackageName(method.toGenericString()));
      out.println(";");
    }
  }

  public void printBody(Class<?> klass) {
    out.println(" {");
    printConstructors(klass, 2);
    printFields(klass, 2);
    printMethods(klass, 2);
    out.println("}");
  }

  public void print() {
    printPackage();
    printClass(this.outerKlass, 0);
  }

  public void printClass(Class<?> klass, int indent) {
    printAnnotations(klass, indent);

    printIndent(indent);
    printModifiers(klass.getModifiers());
    printType(klass);
    printTypeIdentifier(klass);
    printTypeParameters(klass);

    printSuperClass(klass);
    printInterfaces(klass);
    printBody(klass);
  }

  public static void main(String[] args) {
    for (String name : args) {
      try {
        Class<?> klass = Class.forName(name);
        new ClassPrinter(klass).print();
      } catch (ClassNotFoundException e) {
        System.err.println(e);
      }
    }
  }
}
