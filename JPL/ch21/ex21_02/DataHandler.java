package ch21.ex21_02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.WeakHashMap;

public final class DataHandler {
    private WeakHashMap<File, byte[]> file2data = new WeakHashMap<>();

    byte[] readFile(File file) {
        if (file2data.get(file) != null) {
            byte[] data = file2data.get(file);
            if (data != null) {
                return data;
            }
        }

        byte[] data = readBytesFromFile(file);
        file2data.put(file, data);
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
