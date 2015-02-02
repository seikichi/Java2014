package ch17.ex17_01;

import java.util.ArrayList;

public class MemoryInspector {
  public static void fullGC() {
    Runtime rt = Runtime.getRuntime();
    long isFree = rt.freeMemory();
    long wasFree;
    do {
      wasFree = isFree;
      rt.runFinalization();
      rt.gc();
      isFree = rt.freeMemory();
    } while (isFree > wasFree);
  }

  public static void main(String[] args) {
    Runtime runTime = Runtime.getRuntime();
    System.out.println("MemoryInspector.main started");
    System.out.println(" freeMemory:  " + runTime.freeMemory());

    ArrayList<Object> arrayList = new ArrayList<>();
    for (int i = 0; i < 262144; i++) {
      arrayList.add(new Object());
    }
    System.out.println("262144 Object were created");
    System.out.println(" freeMemory:  " + runTime.freeMemory());

    fullGC();
    System.out.println("System.runFinalization() and System.gc() were called");
    System.out.println(" freeMemory:  " + runTime.freeMemory());
  }
}
