package crud;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableFieldType;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class IndexingTest{
    String[] ids = {"1", "2"};
    String[] unindexed = {"Netherlands", "Italy"};
    String[] unstorred = {"Asmterdam has llots of brirdges", "Venice has llots of cannals"};
    String[] text = {"Amsterdam", "Venice"};

    private Directory directory;

    @BeforeClass
    private void setup(){
        directory = new RAMDirectory();

        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            doc.add(new StoredField("id", ids[i]));
//            doc.add(new TextField())
        }

    }

    private IndexWriter getWriter(){
        try {
            return new IndexWriter(directory, new IndexWriterConfig(new WhitespaceAnalyzer()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
