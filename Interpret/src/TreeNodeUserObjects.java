import java.lang.Package;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public final class TreeNodeUserObjects {
  public static TreeNodeUserObject fromClass(Class<?> klass) {
    return new ClassTreeNodeUserObject(klass);
  }

  public static TreeNodeUserObject fromMethod(Method method) {
    return new MethodTreeNodeUserObject(method);
  }

  public static TreeNodeUserObject fromField(Field field) {
    return new FieldTreeNodeUserObject(field);
  }

  public static TreeNodeUserObject fromConstructor(Constructor ctor) {
    return new ConstructorTreeNodeUserObject(ctor);
  }
}

class ClassTreeNodeUserObject implements TreeNodeUserObject {
  Class<?> klass;
  ClassTreeNodeUserObject(Class<?> klass) { this.klass = klass; }

  @Override public String toString() { return klass.getName(); }
  @Override public boolean isLeaf() { return false; }
  @Override public List<TreeNodeUserObject> getChildren() {
    List<TreeNodeUserObject> list = new ArrayList<>();

    for (Constructor ctor : klass.getDeclaredConstructors()) {
      list.add(TreeNodeUserObjects.fromConstructor(ctor));
    }

    for (Method method : klass.getDeclaredMethods()) {
      if (!Modifier.isStatic(method.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromMethod(method));
    }

    for (Field field : klass.getDeclaredFields()) {
      if (!Modifier.isStatic(field.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromField(field));
    }

    for (Class<?> inner : klass.getDeclaredClasses()) {
      list.add(TreeNodeUserObjects.fromClass(inner));
    }
    return list;
  }
}

class MethodTreeNodeUserObject implements TreeNodeUserObject {
  Method method;
  MethodTreeNodeUserObject(Method method) { this.method = method; }

  @Override public String toString() { return method.toGenericString(); }
  @Override public boolean isLeaf() { return true; }
  @Override public List<TreeNodeUserObject> getChildren() {
    return new ArrayList<>();
  }
}

class FieldTreeNodeUserObject implements TreeNodeUserObject {
  Field field;
  FieldTreeNodeUserObject(Field field) { this.field = field; }

  @Override public String toString() { return field.toGenericString(); }
  @Override public boolean isLeaf() { return true; }
  @Override public List<TreeNodeUserObject> getChildren() {
    return new ArrayList<>();
  }
}

class ConstructorTreeNodeUserObject implements TreeNodeUserObject {
  Constructor ctor;
  ConstructorTreeNodeUserObject(Constructor ctor) { this.ctor = ctor; }

  @Override public String toString() { return ctor.toGenericString(); }
  @Override public boolean isLeaf() { return true; }
  @Override public List<TreeNodeUserObject> getChildren() {
    return new ArrayList<>();
  }
}
