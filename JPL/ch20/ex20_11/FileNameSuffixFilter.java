package ch20.ex20_11;

import java.io.File;

public class FileNameSuffixFilter {
  public static void main(String[] args) {
    if (args.length != 2) { return; }
    String dirName = args[0], suffix = args[1];

    File dir = new File(dirName);
    String[] files = dir.list((d, name) -> name.endsWith(suffix));
    for (String file : files) { System.out.println(file); }
  }
}
