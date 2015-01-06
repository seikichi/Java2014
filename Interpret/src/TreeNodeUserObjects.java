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

  public static TreeNodeUserObject fromMethod(Method method, Object receiver) {
    return new MethodTreeNodeUserObject(method, receiver);
  }

  public static TreeNodeUserObject fromField(Field field, Object receiver) {
    return new FieldTreeNodeUserObject(field, receiver);
  }

  public static TreeNodeUserObject fromConstructor(Constructor ctor) {
    return new ConstructorTreeNodeUserObject(ctor);
  }

  public static TreeNodeUserObject fromInstance(String name, Object object) {
    return new InstanceTreeNodeUserObject(name, object);
  }
}

class InstanceTreeNodeUserObject implements TreeNodeUserObject {
  String name;
  Object instance;
  InstanceTreeNodeUserObject(String name, Object instance) {
    this.name = name;
    this.instance = instance;
  }

  @Override public String toString() { return name; }
  @Override public boolean isLeaf() { return false; }
  @Override public List<TreeNodeUserObject> getChildren() {
    if (this.instance == null) { return new ArrayList<>(); }
    List<TreeNodeUserObject> list = new ArrayList<>();
    Class<?> klass = this.instance.getClass();
    for (Method method : klass.getMethods()) {
      method.setAccessible(true);
      if (Modifier.isStatic(method.getModifiers()) || Modifier.isAbstract(method.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromMethod(method, instance));
    }
    for (Field field : klass.getFields()) {
      field.setAccessible(true);
      if (Modifier.isStatic(field.getModifiers()) || Modifier.isAbstract(field.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromField(field, this.instance));
    }
    return list;
  }
  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    // return target == ExplorerDialogPresenter.TargetType.CONSTRUCTOR ||
    //   target == ExplorerDialogPresenter.TargetType.ALL;
    return true;
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
      ctor.setAccessible(true);
      list.add(TreeNodeUserObjects.fromConstructor(ctor));
    }

    for (Method method : klass.getDeclaredMethods()) {
      method.setAccessible(true);
      if (!Modifier.isStatic(method.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromMethod(method, null));
    }

    for (Field field : klass.getDeclaredFields()) {
      field.setAccessible(true);
      if (!Modifier.isStatic(field.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromField(field, null));
    }

    for (Class<?> inner : klass.getDeclaredClasses()) {
      list.add(TreeNodeUserObjects.fromClass(inner));
    }
    return list;
  }

  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return true;
  }
}

class MethodTreeNodeUserObject implements TreeNodeUserObject {
  Method method;
  Object receiver;
  MethodTreeNodeUserObject(Method method, Object receiver) {
    this.method = method;
    this.receiver = receiver;
  }

  @Override public String toString() { return method.toGenericString(); }
  @Override public boolean isLeaf() { return true; }
  @Override public List<TreeNodeUserObject> getChildren() {
    return new ArrayList<>();
  }
  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.METHOD ||
      target == ExplorerDialogPresenter.TargetType.ALL;
  }
}

class FieldTreeNodeUserObject implements TreeNodeUserObject {
  Field field;
  Object receiver;
  FieldTreeNodeUserObject(Field field, Object receiver) {
    this.field = field;
    this.receiver = receiver;
  }

  @Override public String toString() { return field.toGenericString(); }
  // @Override public String toString() { return field.getName(); }
  @Override public boolean isLeaf() { return false; }
  @Override public List<TreeNodeUserObject> getChildren() {
    Object value = null;
    try {
      value = this.field.get(this.receiver);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    if (value == null) { return new ArrayList<>(); }
    List<TreeNodeUserObject> list = new ArrayList<>();
    Class<?> klass = value.getClass();
    for (Method method : klass.getMethods()) {
      method.setAccessible(true);
      if (Modifier.isStatic(method.getModifiers()) || Modifier.isAbstract(method.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromMethod(method, value));
    }
    for (Field field : klass.getFields()) {
      field.setAccessible(true);
      if (Modifier.isStatic(field.getModifiers()) || Modifier.isAbstract(field.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromField(field, value));
    }
    return list;
  }
  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.FIELD ||
      target == ExplorerDialogPresenter.TargetType.ALL;
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
  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.CONSTRUCTOR ||
      target == ExplorerDialogPresenter.TargetType.ALL;
  }
}
