package ch21.ex21_06;

import java.io.*;
import java.util.Enumeration;

public class Concat {
    public static void main(String[] args) throws IOException { 
        InputStream in;
        if (args.length == 0) {
            in = System.in;
        } else {
            in = new SequenceInputStream(new ConcatEnumeration(args));
        }
        
        int ch;
        while ((ch = in.read()) != -1) {
            System.out.write(ch);
        }
    }
    
    private static class ConcatEnumeration implements Enumeration<InputStream> {

        private int currentIndex = 0;
        private final String[] args;

        ConcatEnumeration(String[] args) {
            this.args = args;
        }

        @Override
        public boolean hasMoreElements() {
            return currentIndex < args.length;
        }

        @Override
        public InputStream nextElement() {
            try {
                return new BufferedInputStream(new FileInputStream(args[currentIndex++]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
