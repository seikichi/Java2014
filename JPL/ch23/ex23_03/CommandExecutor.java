package ch23.ex23_03;

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
        exec(args, "hello");
    }

    public static void exec(String[] args, String keyword) throws IOException {
        Process child = new ProcessBuilder(args).
                redirectErrorStream(true).
                start();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream()))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains(keyword)) {
                    child.destroy();
                    break;
                }
                lines.add(line);
            }

            for (int i = 0; i < lines.size(); i++) {
                System.out.printf("%04d %s%n", i + 1, lines.get(i));
            }
        }
    }
}
