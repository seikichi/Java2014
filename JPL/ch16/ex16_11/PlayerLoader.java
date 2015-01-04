// package ch16.ex16_11;

// public class PlayerLoader extends ClassLoader {
//   public Class<?> findClass(String name) throws ClassNotFountException {
//     try {
//       byte[] buf = bytesForClass(name);
//       return defineClass(name, buf, 0, buf.length);
//     } catch (IOException e) {
//       throw new ClassNotFountException(e.toString());
//     }
//   }

//   protected byte[] bytesForClass(String name) throws IOException, ClassNotFountException {
//     FileInputStream in = null;
//     try {
//       in = streamFor(name + ".class");
//       int length = in.available();
//       if (length == 0) {
//         throw new ClassNotFoundException(name);
//       }
//       byte[] buf = new byte[length];
//       in.read(buf);
//       return buf;
//     } finally {
//       if (in != null) {
//         in.close();
//       }
//     }
//   }

//   public java.net.URL findResources(String name) {
//     File f = fileFor(name);
//     if (!f.exists()) { return null; }
//     try {
//       return f.toURL();
//     } catch (java.net.MalformedURLException e) {
//       return null;
//     }
//   }
// }
