package io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static void main(String[] args) throws IOException {
        //按行读取文件并打印
        System.out.println(Paths.get("data").toAbsolutePath());
        BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("data" + File.separator + "io.txt")), StandardCharsets.UTF_8));
        String line = null;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
        br.close();
    }


    /**
     * copy file，新建、覆盖已有文件。
     * @param src
     * @param dest
     */
    public static void copyFile(String src, String dest, boolean append) throws IOException {
        /**
         * 1. 按照字节来拷贝，不涉及到编码字符集。
         * 2. byte[] buffer 需要【注意】，它不会自己清空，需要注意从哪里写，写多少，否则会出现null或者重复。
         * 3. 经过多次拷贝：disk -> core memory -> user memory -> core memory -> disk
         *
         */
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dest, append);

        byte[] buffer = new byte[1024];
        int len;
        while((len = in.read(buffer, 0, buffer.length)) != -1){
            out.write(buffer, 0, len);
        }
        out.flush();

        in.close();
        out.close();
    }

    /**
     * 字节流 -> 面向字节 byte[]
     * 字符流 -> 面向字符 char[]
     * bufferedReader -> 面向一堆字符（行）
     * @param filePath
     */
    public static void printLineByLine(String filePath, Charset charset) throws IOException {
        System.out.println(">>>>>>>>> print file at path = " + filePath);
        File file = new File(filePath);
        if(!file.exists()) return;
        if(!file.isFile()) return;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file, charset))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        }
    }

    public static void printCharByChar(String filePath, Charset charset) throws IOException {
        System.out.println(">>>>>>>>> print file at path = " + filePath);
        char[] buffer = new char[5];
        int length = 0;
        try(FileReader reader = new FileReader(filePath, charset)){
            while((length = reader.read(buffer, 0, buffer.length)) != -1){
                for (int i = 0; i < length; i++) {
                    System.out.print(buffer[i]);
                }
            }
        }
    }
}
