package ch23.ex23_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: java CommandExecutor.class [command]");
            return;
        }
        execWithLineNum(args);
    }

    public static void execWithLineNum(String[] args) throws IOException {
        Process child = new ProcessBuilder(args).
                redirectErrorStream(true).
                start();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream()))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }

            for (int i = 0; i < lines.size(); i++) {
                System.out.printf("%04d %s%n", i + 1, lines.get(i));
            }
        }
    }
}
