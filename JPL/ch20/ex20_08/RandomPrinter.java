package ch20.ex20_08;

import java.util.Random;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomPrinter {
  private final RandomAccessFile raf;
  private final ArrayList<Long> entries = new ArrayList<>();

  public int getNumOfEntries() {
    return entries.size();
  }

  public void printEntry(int entryId, PrintWriter to) throws IOException {
    if (entryId >= entries.size()) {
      throw new IllegalArgumentException("Invalid entryId");
    }
    long pos = entries.get(entryId);
    long nextPos = entryId == (entries.size() - 1) ? raf.length() : entries.get(entryId + 1);
    long size = nextPos - pos;

    byte[] bytes = new byte[(int)size];
    raf.seek(pos);
    raf.read(bytes);

    to.print(new String(bytes));
  }

  public RandomPrinter(File file) throws IOException {
    raf = new RandomAccessFile(file, "r");
    for (;;) {
      long pos = raf.getFilePointer();
      String line = raf.readLine();
      if (line == null) { break; }
      if (line.startsWith("%%")) {
        entries.add(pos);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    if (args.length != 1) { throw new IllegalArgumentException(); }
    File file = new File(args[0]);
    RandomPrinter p = new RandomPrinter(file);

    Random rand = new Random();
    for (int i = 0; i < 10; i++) {
      int entryId = rand.nextInt(p.getNumOfEntries());
      p.printEntry(entryId, new PrintWriter(System.out, true));
    }
  }
}
