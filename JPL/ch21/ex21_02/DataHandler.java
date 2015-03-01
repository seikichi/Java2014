package ch21.ex21_02;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DataHandler {
    private File lastFile;
    private WeakReference<byte[]> lastData;
    
    byte[] readFile(File file) {
        byte[] data;
        if (file.equals(lastFile)) {
            data = lastData.get();
            if (data != null) {
                return data;
            }
        }
        
        data = readBytesFromFile(file);
        lastFile = file;
        lastData = new WeakReference<>(data);
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
