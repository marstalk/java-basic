package io;

import org.junit.Test;

import java.io.IOException;

public class UrlUtilTests {
    @Test
    public void test() throws IOException {
        UrlUtil.printUrl("http://www.baidu.com");
    }

    @Test
    public void test2() throws IOException {
        UrlUtil.printTo("http://www.baidu.com", "src/test/resources/baidu.html");
    }
}
