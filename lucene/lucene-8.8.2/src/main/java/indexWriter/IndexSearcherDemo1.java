package indexWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class IndexSearcherDemo1 {
    public static void main(String[] args) throws IOException {
        FSDirectory fsDirectory = FSDirectory.open(Path.of("/Users/liujiacheng/tmp/index_writer_demo"));

        DirectoryReader directoryReader = DirectoryReader.open(fsDirectory);

        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        TermQuery termQuery = new TermQuery(new Term("name", "ljc"));

        TopDocs search = indexSearcher.search(termQuery, 10);
        System.out.println(search.totalHits.value);
        System.out.println(Arrays.toString(search.scoreDocs));

    }
}
