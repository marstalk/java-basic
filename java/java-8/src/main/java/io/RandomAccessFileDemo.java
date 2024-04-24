package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        String filePath = "java/java-8/data/random_access.txt";

        try(RandomAccessFile raf = new RandomAccessFile(new File(filePath), "rw")){
            /*
             * 写，因为不是【字符串写入的】，所以random_access.txt文本文件打开会乱码。
             */
            // 写字节数组
            byte[] bytes = "this is randomAccessFile demo 这个是RandomAccessFile的使用demo".getBytes(StandardCharsets.UTF_8);
            raf.write(bytes);
            raf.writeBoolean(false);
            raf.writeDouble(11.22);
            raf.writeUTF("UTF字符串");
            raf.writeInt(88);

            /*
             * 读，主要要把file pinter指向你需要开始读取的位置，下面的例子是从头开始读取。
             */
            raf.seek(0);

            // 按字节读取，如果某字符占用一个字节，那么可以正常打印，如果某个字符占用超过1个字节，比如中文字符在utf中需3个字节，所以，按照字节打印的时候，会乱码：
            byte[] bs = new byte[1];
            while(raf.read(bs) != -1){
                System.out.println(new String(bs, StandardCharsets.UTF_8));
            }

            // 按照写入书序来读取。
            raf.seek(0);
            byte[] rtn = new byte[bytes.length];
            raf.read(rtn);
            System.out.println(new String(rtn, StandardCharsets.UTF_8));
            System.out.println(raf.readBoolean());
            System.out.println(raf.readDouble());
            System.out.println(raf.readUTF());
            System.out.println(raf.readInt());

            /*
             * append，读完之后，file pointer来到末尾，可以继续写，即追加append。
             */

            // 也可以不需要这行代码，因为file pointer已经在尾部了
            raf.seek(raf.length());
            raf.writeUTF("\n这个是追加的内容This is append content");

            /*
             * TODO insert，在任意位置插入内容
             */


        }
    }
}
