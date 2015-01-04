package ch16.ex16_04;

import java.lang.annotation.*;
import java.lang.reflect.*;

public final class AnnotationPrinter {
  public static void main(String[] args) {
    for (String name : args) {
      try {
        Class<?> klass = Class.forName(name);
        printAnnotations(klass);
      } catch (ClassNotFoundException e) {
        System.err.println(e);
      }
    }
  }

  private static void printAnnotations(Class<?> klass) {
    Annotation[] annotations = klass.getAnnotations();
    for (Annotation annot : annotations) {
      System.out.println(annot);
    }
  }
}
