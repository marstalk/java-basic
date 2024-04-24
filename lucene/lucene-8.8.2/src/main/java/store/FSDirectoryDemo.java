package store;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.file.*;

public class FSDirectoryDemo {
    public static void main(String[] args) {
        try {
            FilterOutputStream filterOutputStream = new FilterOutputStream(Files.newOutputStream(Paths.get("txt/lucene.txt"), StandardOpenOption.APPEND)) {
                @Override
                public void write(byte[] b, int off, int len) throws IOException {
                    while(len > 0){
                        final int chunk = Math.min(len, 8192);
                    }
                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
