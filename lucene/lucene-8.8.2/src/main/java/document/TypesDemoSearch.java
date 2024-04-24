package document;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class TypesDemoSearch {
    public static void main(String[] args) throws IOException, ParseException {
        FSDirectory fsDirectory = FSDirectory.open(Paths.get("lucene/lucene-8.8.2/lucene-data2"));

        DirectoryReader directoryReader = DirectoryReader.open(fsDirectory);

        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        Query query = new MatchAllDocsQuery();

        TopDocs topDocs = indexSearcher.search(query, 10);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.println("name=" + doc.get("name") + " ,address=" + doc.get("address") + " ,age= " + doc.get("age") + " ,phone=" + doc.get("phone"));
        }

    }
}
