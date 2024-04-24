package io;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileUtilTests {
    @Test
    public void testCopyFile() throws IOException {
        String source = "src/test/resources/2.txt";
        String dest = "src/test/resources/2.copy.txt";

        FileUtil.copyFile(source, dest, false);
        File file = new File(dest);
        Assert.assertTrue(file.exists());
        FileUtil.printLineByLine(dest, StandardCharsets.UTF_8);


        source = "src/test/resources/1.txt";
        FileUtil.copyFile(source, dest, false);
        FileUtil.printLineByLine(dest, StandardCharsets.UTF_8);

        FileUtil.printLineByLine("src/test/resources/3.gbk.txt", Charset.forName("gbk"));
    }


}
