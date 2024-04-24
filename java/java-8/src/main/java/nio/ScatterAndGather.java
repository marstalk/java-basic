package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ScatterAndGather {
    public static void main(String[] args) throws IOException {

        try (FileChannel inChannel = FileChannel.open(Paths.get("java/java-8/data/io.txt"), StandardOpenOption.READ);
             FileChannel outChanel = FileChannel.open(Paths.get("java/java-8/data/io.gather.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)
        ) {
            ByteBuffer b1 = ByteBuffer.allocate(1);
            ByteBuffer b2 = ByteBuffer.allocate(2);
            //ByteBuffer b3 = ByteBuffer.allocateDirect(3);//堆外不支持Scatter
            ByteBuffer b3 = ByteBuffer.allocate(4);

            ByteBuffer[] bbs = {b1, b2, b3};
            long read = inChannel.read(bbs);
            System.out.println("bytes being read = " + read);


            for(ByteBuffer bb : bbs){
                // 切换为读模式
                bb.flip();
                System.out.println(new String(bb.array(), 0, bb.limit()));

                long write = outChanel.write(bbs);
                System.out.println("bytes being write = " + write);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
