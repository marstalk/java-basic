package indexWriter;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;

public class IndexWriterDemo1 {
    public static void main(String[] args) throws IOException {
        IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        conf.setUseCompoundFile(false);
        conf.setInfoStream(System.out);
        IndexWriter indexWriter = new IndexWriter(
                FSDirectory.open(Path.of("/Users/liujiacheng/tmp/index_writer_demo")), conf);

        Document doc = new Document();
        doc.add(new TextField("name", "ljc-update-welcome-store-no", Field.Store.NO));
        indexWriter.addDocument(doc);
        indexWriter.flush();
        indexWriter.commit();
    }
}
