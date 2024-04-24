package document;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * book_name: text, sorted
 * author: text, sorted
 * country: keyword, sorted
 * id: keyword,
 * desc: text
 * publish: datetime, sorted
 * tag: keyword, store.NO
 */
public class TypesDemoIndex {
    public static void main(String[] args) throws IOException {
        Document doc1 = new Document();
        doc1.add(new TextField("content", "Mars is my home town, not yours", Field.Store.YES));
        doc1.add(new TextField("content", "123", Field.Store.YES));


        FSDirectory fsDirectory = FSDirectory.open(Paths.get("lucene/lucene-8.8.2/lucene-types"));
        IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter indexWriter = new IndexWriter(fsDirectory, conf);

        indexWriter.addDocument(doc1);
        indexWriter.flush();

        indexWriter.forceMerge(1);

        indexWriter.close();

        // search
        DirectoryReader directoryReader = DirectoryReader.open(fsDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        //TopDocs search = indexSearcher.search(new MatchAllDocsQuery(), 10);
        TopDocs search;
        search = indexSearcher.search(new TermQuery(new Term("content", "mars")), 10);
        for (ScoreDoc scoreDoc : search.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            doc.getFields().forEach(indexableField -> System.out.println(indexableField.name() + ":" + indexableField.stringValue()));
        }
        directoryReader.close();
    }
}
