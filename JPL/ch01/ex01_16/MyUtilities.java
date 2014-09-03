package ch01.ex01_16;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;

class BadDataSetException extends Exception {
  private static final long serialVersionUID = 1L;
  private final String fileName;
  private final IOException ioException;

  String getFileName() { return fileName; }
  IOException getIOException() { return ioException; }

  BadDataSetException(String fileName, IOException ioException) {
    this.fileName = fileName;
    this.ioException = ioException;
  }
}

class MyUtilities {
  public static void main(String[] args) {}

  public double[] getDataSet(String setName)
    throws BadDataSetException {
    String file = setName + ".dset";
    FileInputStream in = null;
    try {
      in = new FileInputStream(file);
      return readDataSet(in);
    } catch (IOException e) {
      throw new BadDataSetException(file, e);
    } finally {
      try {
        if (in != null) {
          in.close();
        }
      } catch (IOException e) {
        ;
      }
    }
  }

  public double[] readDataSet(InputStream in) {
    return new double[] { 1.0 };
  }
}
