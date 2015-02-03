package ch20.ex20_09;

import java.io.IOException;
import java.io.File;

public class FileInfoPrinter {
  public static void main(String[] args) {
    for (String arg : args) {
      File file = new File(arg);
      System.out.println(String.format("name = %s", arg));
      System.out.println(String.format("getName() = %s", file.getName()));
      System.out.println(String.format("getPath() = %s", file.getPath()));
      try {
        System.out.println(String.format("getCanonicalPath() = %s", file.getCanonicalPath()));
      } catch (IOException ignore) {}
      System.out.println(String.format("getParent() = %s", file.getParent()));
      System.out.println(String.format("exists() = %s", file.exists()));
      System.out.println(String.format("canRead() = %s", file.canRead()));
      System.out.println(String.format("canWrite() = %s", file.canWrite()));
      System.out.println(String.format("isFile() = %s", file.isFile()));
      System.out.println(String.format("isDirectory() = %s", file.isDirectory()));
      System.out.println(String.format("isAbsolute() = %s", file.isAbsolute()));
      System.out.println(String.format("isHidden() = %s", file.isHidden()));
      System.out.println(String.format("lastModified() = %s", file.lastModified()));
      System.out.println(String.format("length() = %s", file.length()));
      System.out.println();
    }
  }
}
