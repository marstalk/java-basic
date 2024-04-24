import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;


public class IndexWriterTests {

    @Test
    public void testIndexWriter() throws Exception {
        System.out.println(Paths.get("text").toAbsolutePath());
        IndexFiles indexFiles = new IndexFiles("lucene-data");
        int cnt = indexFiles.indexDir("txt");
        Assert.assertEquals(cnt, 3);
        indexFiles.close();
    }
}
