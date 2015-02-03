import javax.swing.JOptionPane;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

interface FunctionResult extends ExplorerResult {
  Object invoke(Object ...args);
  Class<?> getReturnType();

  static FunctionResult fromMethod(Method method, Object receiver) {
    return new MethodResult(method, receiver);
  }

  static FunctionResult fromConstructor(Constructor ctor) {
    return new ConstructorResult(ctor);
  }
}

class MethodResult implements FunctionResult {
  private Object receiver;
  private Method method;

  MethodResult(Method method, Object receiver) {
    this.receiver = receiver;
    this.method = method;
  }

  public Object invoke(Object ...args) {
    method.setAccessible(true);
    try {
      return method.invoke(receiver, args);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      JOptionPane.showMessageDialog(null,
                                    e.toString(),
                                    "Exception",
                                    JOptionPane.ERROR_MESSAGE);
    } catch (InvocationTargetException _e) {
      Throwable e = _e.getTargetException();
      JOptionPane.showMessageDialog(null,
                                    e.toString(),
                                    "Exception",
                                    JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  public Class<?> getReturnType() {
    return method.getReturnType();
  }

  public Method get() { return method; }
}

class ConstructorResult implements FunctionResult {
  private Constructor ctor;

  ConstructorResult(Constructor ctor) {
    this.ctor = ctor;
  }

  public Object invoke(Object ...args) {
    try {
      return ctor.newInstance(args);
    } catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
      JOptionPane.showMessageDialog(null,
                                    e.toString(),
                                    "Exception",
                                    JOptionPane.ERROR_MESSAGE);
    } catch (InvocationTargetException _e) {
      Throwable e = _e.getTargetException();
      JOptionPane.showMessageDialog(null,
                                    e.toString(),
                                    "Exception",
                                    JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

  public Class<?> getReturnType() {
    return ctor.getDeclaringClass();
  }

  public Object newArray(int size) {
    return Array.newInstance(ctor.getDeclaringClass(), size);
  }

  public Constructor get() { return ctor; }
}
