package ch16.ex16_11;

import java.io.FileNotFoundException;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PlayerLoader extends ClassLoader {
  public Class<?> findClass(String name) throws ClassNotFoundException {
    try {
      byte[] buf = bytesForClass(name);
      return defineClass(name, buf, 0, buf.length);
    } catch (IOException e) {
      throw new ClassNotFoundException(e.toString());
    }
  }

  protected byte[] bytesForClass(String name) throws IOException, ClassNotFoundException {
    FileInputStream in = null;
    try {
      in = streamFor(name + ".class");
      int length = in.available();
      if (length == 0) {
        throw new ClassNotFoundException(name);
      }
      byte[] buf = new byte[length];
      in.read(buf);
      return buf;
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  private FileInputStream streamFor(String name) {
    FileInputStream in = null;
    try {
      in = new FileInputStream("players/" + name);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return in;
  }

  // public java.net.URL findResources(String name) {
  //   File f = fileFor(name);
  //   if (!f.exists()) { return null; }
  //   try {
  //     return f.toURL();
  //   } catch (java.net.MalformedURLException e) {
  //     return null;
  //   }
  // }
}
