package ch16.ex16_03;

import java.util.Set;
import java.util.HashSet;

import java.lang.reflect.*;

public class ClassContents {
  public static void main(String[] args) {
    try {
      Class<?> c = Class.forName(args[0]);
      System.out.println(c);
      Set<String> set = new HashSet<>();
      printMembers(c.getDeclaredFields(), set);
      printMembers(c.getFields(), set);
      printMembers(c.getDeclaredConstructors(), set);
      printMembers(c.getConstructors(), set);
      printMembers(c.getDeclaredMethods(), set);
      printMembers(c.getMethods(), set);
    } catch (ClassNotFoundException e) {
      System.out.println("unknown class: " + args[0]);
    }
  }

  private static void printMembers(Member[] mems, Set<String> set) {
    for (Member m : mems) {
      if (m.getDeclaringClass() == Object.class) { continue; }
      String decl = m.toString();
      if (set.contains(decl)) { continue; }
      set.add(decl);
      System.out.print(" ");
      System.out.println(strip(decl, "java\\.lang\\."));
    }
  }

  private static String strip(String str, String stripChars) {
    return str.replaceAll(stripChars, "");
  }
}
