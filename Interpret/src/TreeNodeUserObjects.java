import java.lang.Class;
import java.lang.Package;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

public final class TreeNodeUserObjects {
  public static TreeNodeUserObject fromClass(Class<?> klass) {
    return new ClassTreeNodeUserObject(klass);
  }

  public static TreeNodeUserObject fromArray(Object array, int index) {
    return new ArrayTreeNodeUserObject(array, index);
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

  @Override public String getSimpleName() { return name; }
  @Override public String toString() { return name; }
  @Override public boolean isLeaf() { return false; }
  @Override public List<TreeNodeUserObject> getChildren() {
    return TreeNodeUserObjectUtil.getChildren(this.instance);
  }

  @Override public boolean matchTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.ALL ||
      target == ExplorerDialogPresenter.TargetType.FIELD ||
      target == ExplorerDialogPresenter.TargetType.FIELD_READONLY;
  }

  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return target != ExplorerDialogPresenter.TargetType.CONSTRUCTOR;
  }
  @Override public ExplorerResult getWrapperObject() {
    return FieldResult.fromInstance(this.instance);
  }
}

class ArrayTreeNodeUserObject implements TreeNodeUserObject {
  Object array;
  int index;

  ArrayTreeNodeUserObject(Object array, int index) {
    this.array = array;
    this.index = index;
  }

  @Override public String getSimpleName() { return this.toString(); }
  @Override public String toString() { return String.format("[%d]", index); }
  @Override public boolean isLeaf() { return false; }
  @Override public List<TreeNodeUserObject> getChildren() {
    Object value = null;
    value = Array.get(array, index);
    return TreeNodeUserObjectUtil.getChildren(value);
  }

  @Override public boolean matchTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.ALL ||
      target == ExplorerDialogPresenter.TargetType.FIELD ||
      target == ExplorerDialogPresenter.TargetType.FIELD_READONLY;
  }

  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return target != ExplorerDialogPresenter.TargetType.CONSTRUCTOR;
  }
  @Override public ExplorerResult getWrapperObject() {
    return FieldResult.fromArray(array, index);
  }
}

class ClassTreeNodeUserObject implements TreeNodeUserObject {
  Class<?> klass;
  ClassTreeNodeUserObject(Class<?> klass) { this.klass = klass; }

  @Override public String getSimpleName() { return klass.getSimpleName(); }
  @Override public String toString() {
    Class<?> enclosing = klass.getEnclosingClass();
    if (enclosing == null) { return klass.getName(); }
    return klass.getSimpleName();
  }
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

  @Override public boolean matchTo(ExplorerDialogPresenter.TargetType target) {
    return false;
  }

  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return true;
  }
  @Override public ExplorerResult getWrapperObject() {
    return null;
  }
}

class MethodTreeNodeUserObject implements TreeNodeUserObject {
  Method method;
  Object receiver;

  MethodTreeNodeUserObject(Method method, Object receiver) {
    this.method = method;
    this.receiver = receiver;
  }

  @Override public String getSimpleName() { return method.getName(); }
  @Override public String toString() {
    String className = method.getDeclaringClass().getName();
    return method.toGenericString()
      .replaceAll(className + "\\.", "")
      .replaceAll("java\\.lang\\.", "");
  }
  @Override public boolean isLeaf() { return true; }
  @Override public List<TreeNodeUserObject> getChildren() {
    return new ArrayList<>();
  }
  @Override public boolean matchTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.METHOD ||
      target == ExplorerDialogPresenter.TargetType.ALL;
  }
  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.METHOD ||
      target == ExplorerDialogPresenter.TargetType.ALL;
  }
  @Override public ExplorerResult getWrapperObject() {
    return FunctionResult.fromMethod(method, receiver);
  }
}

class FieldTreeNodeUserObject implements TreeNodeUserObject {
  Field field;
  Object receiver;
  FieldTreeNodeUserObject(Field field, Object receiver) {
    this.field = field;
    this.receiver = receiver;
  }

  @Override public String getSimpleName() { return field.getName(); }
  @Override public String toString() {
    String className = field.getDeclaringClass().getName();
    Package pac = field.getDeclaringClass().getPackage();
    if (pac != null) {
      return field.toGenericString()
        .replaceAll(className + "\\.", "")
        .replaceAll("java\\.lang\\.", "");
    }
    return field.toGenericString()
      .replaceAll(className + "\\.", "")
      .replaceAll(pac.getName() + "\\.", "")
      .replaceAll("java\\.lang\\.", "");
  }
  @Override public boolean isLeaf() { return false; }
  @Override public List<TreeNodeUserObject> getChildren() {
    Object value = null;
    try {
      value = this.field.get(this.receiver);
    } catch (Exception e) { JOptionPane.showMessageDialog(null, e.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
    return TreeNodeUserObjectUtil.getChildren(value);
  }
  @Override public boolean matchTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.FIELD ||
      target == ExplorerDialogPresenter.TargetType.FIELD_READONLY ||
      target == ExplorerDialogPresenter.TargetType.ALL;
  }
  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return target != ExplorerDialogPresenter.TargetType.CONSTRUCTOR;
  }
  @Override public ExplorerResult getWrapperObject() {
    return FieldResult.fromField(field, receiver);
  }
}

class ConstructorTreeNodeUserObject implements TreeNodeUserObject {
  Constructor ctor;
  ConstructorTreeNodeUserObject(Constructor ctor) { this.ctor = ctor; }

  @Override public String getSimpleName() { return ctor.getName(); }
  @Override public String toString() {
    Package pac = ctor.getDeclaringClass().getPackage();
    if (pac != null) {
      return ctor
        .toGenericString()
        .replaceAll(pac.getName() + "\\.", "")
        .replaceAll("java\\.lang\\.", "");
    }
    return ctor.toGenericString().replaceAll("java\\.lang\\.", "");
  }
  @Override public boolean isLeaf() { return true; }
  @Override public List<TreeNodeUserObject> getChildren() {
    return new ArrayList<>();
  }
  @Override public boolean matchTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.CONSTRUCTOR ||
      target == ExplorerDialogPresenter.TargetType.ALL;
  }
  @Override public boolean relatedTo(ExplorerDialogPresenter.TargetType target) {
    return target == ExplorerDialogPresenter.TargetType.CONSTRUCTOR ||
      target == ExplorerDialogPresenter.TargetType.ALL;
  }
  @Override public ExplorerResult getWrapperObject() {
    return FunctionResult.fromConstructor(ctor);
  }
}

class TreeNodeUserObjectUtil {
  public static List<TreeNodeUserObject> getChildren(Object value) {
    if (value == null) { return new ArrayList<>(); }
    List<TreeNodeUserObject> list = new ArrayList<>();
    Class<?> klass = value.getClass();
    Set<String> set = new HashSet<>();

    if (klass.isArray()) {
      int length = Array.getLength(value);
      for (int i = 0; i < length; i++) {
        list.add(TreeNodeUserObjects.fromArray(value, i));
      }
    }

    for (Method method : concat(klass.getDeclaredMethods(), klass.getMethods())) {
      if (set.contains(method.toGenericString())) { continue; }
      set.add(method.toGenericString());
      method.setAccessible(true);
      if (Modifier.isStatic(method.getModifiers()) || Modifier.isAbstract(method.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromMethod(method, value));
    }
    for (Field field : concat(klass.getDeclaredFields(), klass.getFields())) {
      if (set.contains(field.toGenericString())) { continue; }
      set.add(field.toGenericString());
      field.setAccessible(true);
      if (Modifier.isStatic(field.getModifiers()) || Modifier.isAbstract(field.getModifiers())) {
        continue;
      }
      list.add(TreeNodeUserObjects.fromField(field, value));
    }
    return list;
  }

  private static <T> T[] concat(T[] first, T[] second) {
    T[] result = Arrays.copyOf(first, first.length + second.length);
    System.arraycopy(second, 0, result, first.length, second.length);
    return result;
  }
}
