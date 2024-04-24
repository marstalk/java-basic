package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelByRandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        String filePath = "java/java-8/data/file_channel_random_access_file.txt";

        /*
         * 获取FileChannel的方式之一：FileInputStream FileOutputStream RandomAccessFile   .getChannel()
         */
        try(FileChannel fc = new RandomAccessFile(filePath, "rw").getChannel()){

            /*
             * 写，往文件里写
             */
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("FileChannel from RandomAccessFile Demo 例子".getBytes(StandardCharsets.UTF_8));

            byteBuffer.flip();
            fc.write(byteBuffer);

            byteBuffer.clear();
            byteBuffer.putDouble(99.99);
            byteBuffer.putInt(100);

            byteBuffer.flip();
            fc.write(byteBuffer);


            /*
             * 读
             */
            byteBuffer.clear();
            int len = fc.read(byteBuffer);
            byte[] bs = new byte[len];
            byteBuffer.get(bs, 0, len);
            System.out.println(new String(bs, StandardCharsets.UTF_8));

            byteBuffer.clear();
            fc.read(byteBuffer);
        }
    }
}
