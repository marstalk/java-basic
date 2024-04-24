package io;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * 可以直接从URl中获得输入字节流。
 */
public class UrlUtil {
    public static void printUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);

        /**
         * 字节流 -> 字符流 -> 字符串
         */
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        }
    }

    public static void printTo(String urlStr, String filePath) throws IOException {
        URL url = new URL(urlStr);

        File file = new File(filePath);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))){
            String line;
            while((line = br.readLine()) != null){
                bw.write(line);
            }
        }
    }
}
