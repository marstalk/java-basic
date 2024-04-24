package indexWriter;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;

public class IndexReaderDemo1 {
    public static void main(String[] args) throws IOException {
        FSDirectory fsDirectory = FSDirectory.open(Path.of("/Users/liujiacheng/tmp/index_writer_demo"));

        DirectoryReader directoryReader = DirectoryReader.open(fsDirectory);


    }
}
