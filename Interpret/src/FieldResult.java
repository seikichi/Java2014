import java.lang.reflect.Field;
import java.lang.reflect.Array;

interface FieldResult extends ExplorerResult {
  public Object get();
  public void set(Object object);

  public static FieldResult fromField(Field field, Object receiver) {
    return new NormalFieldResult(field, receiver);
  }
  public static FieldResult fromArray(Object array, int index) {
    return new ArrayFieldResult(array, index);
  }
  public static FieldResult fromInstance(Object object) {
    return new InstanceResult(object);
  }
}

class ArrayFieldResult implements FieldResult {
  Object array;
  int index;

  ArrayFieldResult(Object array, int index) {
    this.array = array;
    this.index = index;
  }

  @Override public Object get() {
    return Array.get(array, index);
  }
  @Override public void set(Object object) {
    Array.set(array, index, object);
  }
}

class InstanceResult implements FieldResult {
  Object object;

  InstanceResult(Object object) {
    this.object = object;
  }

  @Override public Object get() {
    return object;
  }
  @Override public void set(Object object) {
    return;
  }
}

class NormalFieldResult implements FieldResult {
  Field field;
  Object receiver;

  NormalFieldResult(Field field, Object receiver) {
    this.field = field;
    this.receiver = receiver;
  }

  @Override public Object get() {
    try {
      return field.get(receiver);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }
  @Override public void set(Object object) {
    try {
      field.set(receiver, object);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
