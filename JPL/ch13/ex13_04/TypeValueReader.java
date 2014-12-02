package ch13.ex13_04;

import java.util.ArrayList;
import java.lang.reflect.Constructor;

public final class TypeValueReader {
  public static ArrayList<Object> read(String str) {
    Class<?>[] klassList = new Class<?>[] {
      Byte.class, Short.class, Integer.class, Long.class,
        Float.class, Double.class, Character.class, Boolean.class };

    ArrayList<Object> result = new ArrayList<>();
    for (String line : str.split("\n")) {
      String[] splitted = line.split("\\s+");
      if (splitted.length != 2) { continue; }

      String type = splitted[0], value = splitted[1];
      for (Class<?> klass : klassList) {
        if (!klass.getSimpleName().equals(type)) { continue; }
        try {
          Constructor ctor = klass.getConstructor(new Class<?>[]{String.class});
          Object object = ctor.newInstance(new Object[]{value});
          result.add(object);
          break;
        } catch (Exception e) {}
      }
    }
    return result;
  }
}
