package ch17.ex17_02;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

class DataHandler {
  private WeakReference<File> lastFile;
  private WeakReference<byte[]> lastData;

  byte[] readFile(File file) {
    byte[] data;

    if (lastData != null &&
        lastFile.get() != null &&
        file.equals(lastFile.get())) {
      data = lastData.get();
      if (data != null) {
        return data;
      }
    }

    data = readBytesFromFile(file);
    lastFile = new WeakReference<File>(file);
    lastData = new WeakReference<byte[]>(data);
    return data;
  }

  private byte[] readBytesFromFile(File file) {
    try {
      Path path = Paths.get(file.getPath());
      return Files.readAllBytes(path);
    } catch (IOException e) {
      e.printStackTrace();
      return new byte[0];
    }
  }
}
