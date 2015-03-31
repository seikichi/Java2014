package ch23.ex23_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UserProgExecutor {
    public static Process userProg(String cmd) throws IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        pullTogether(System.in, proc.getOutputStream());
        pullTogether(System.out, proc.getInputStream());
        pullTogether(System.err, proc.getErrorStream());
        return proc;
    }

    private static void pullTogether(InputStream in, OutputStream out) {
        pullTogether(out, in);
    }

    private static void pullTogether(OutputStream out, InputStream in) {
        new Thread(() -> {
            try {
                int b;
                while ((b = in.read()) != -1) {
                    out.write(b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
