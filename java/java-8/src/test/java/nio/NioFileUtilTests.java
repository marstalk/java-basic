package nio;

import org.junit.Test;

import java.io.IOException;

public class NioFileUtilTests {

    @Test
    public void testNonDirectByteBuffer() throws IOException {
        String src =  "src/test/resources/nio/2.txt";
        String dest = "src/test/resources/nio/2.copy.nio.txt";
        NioFileUtil.copyFile(src, dest, false);
    }

    @Test
    public void testNonDirectByteBufferBigFile() throws IOException {
        String src = "/Users/liujiacheng/Downloads/cloudcanal.7z";
        String dest = "/Users/liujiacheng/Downloads/cloudcanal.copy.7z";
        NioFileUtil.copyFile(src, dest, false);
    }

    @Test
    public void testDirectByteBuffer() throws IOException {
        String src = "src/test/resources/nio/2.txt";
        String dest = "src/test/resources/nio/2.copy.nio.direct_byte_buffer.txt";
        NioFileUtil.copyFileWithDirectByteBuffer(src, dest, false);
    }

    /**
     * 1. 小文件没有问题
     */
    @Test
    public void testMMapSmallFile() {
        String src = "src/test/resources/nio/2.txt";
        String dest = "src/test/resources/nio/2.copy.nio.mmap.txt";
        NioFileUtil.copyByMMap(src, dest, 1);
    }

    /**
     * 1. 如果文件超过Xmx，会异常。因为需要将文件内容读取到byte[]中才能写出去。
     * 2. 经过优化之后可以对大文件进行拷贝，mmap(mode, offset, size)，调整offset和size到合理的值。
     */
    @Test
    public void testMMapBigFile() {
        String src = "/Users/liujiacheng/Downloads/cloudcanal.7z";
        String dest = "/Users/liujiacheng/Downloads/cloudcanal.copy.7z";
        NioFileUtil.copyByMMap(src, dest, 100);
    }

    /**
     * 最佳性能是transfer，【零拷贝】
     */
    @Test
    public void testTransferTo(){
        String src = "/Users/liujiacheng/Downloads/cloudcanal.7z";
        String dest = "/Users/liujiacheng/Downloads/cloudcanal.copy.7z";
        NioFileUtil.copyByTransferTo(src, dest);
    }

}
