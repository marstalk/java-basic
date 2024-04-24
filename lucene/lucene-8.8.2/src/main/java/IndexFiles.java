
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class IndexFiles implements AutoCloseable {

    private IndexWriter indexWriter;

    /**
     * @param dir directory to place the output file like fdx, fnm, fdt, tis, tii
     * @throws IOException
     */
    public IndexFiles(String dir) throws IOException {
        FSDirectory fsDirectory = FSDirectory.open(Paths.get(dir));
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
        indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);
    }

    public int indexDir(String dir) throws IOException {
        File[] files = new File(dir).listFiles();
        if (files == null) return 0;
        for (File file : files) {
            if (file.isFile() && !file.isHidden() && file.canRead()) {
                indexFile(file);
            }
        }
        return indexWriter.numRamDocs();
    }

    private void indexFile(File file) throws IOException {
        indexWriter.addDocument(getDococument(file));
    }

    private Document getDococument(File file) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("contents", new FileReader(file))); //分词后不保存原有数据。
        doc.add(new TextField("file_name", file.getName(), Field.Store.YES));
        doc.add(new TextField("full_path", file.getCanonicalPath(), Field.Store.YES));
        return doc;
    }

    @Override
    public void close() throws Exception {
        indexWriter.close();
    }
}
