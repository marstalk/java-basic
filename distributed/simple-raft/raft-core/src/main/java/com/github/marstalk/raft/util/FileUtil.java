package com.github.marstalk.raft.util;

import com.google.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.CRC32;

public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static void main(String[] args) throws IOException {
        /*
         * /Users/liujiacheng/gitee/odemo
         * /Users/liujiacheng/gitee/odemo/.
         */
        System.out.println(new File(".").getCanonicalPath());
        System.out.println(new File(".").getAbsoluteFile());
    }

    public static List<String> listSortedFilesInDirectory(String root, String dir) throws IOException {
        List<String> rtn = new ArrayList<>();
        File directory = new File(dir);
        if (!directory.exists()) {
            log.warn("{} does not exist", dir);
            return rtn;
        }

        File[] files = directory.listFiles();
        if (null == files) {
            return rtn;
        }

        if (!root.endsWith("/")){
            root = root + "/";
        }

        for (File f : files) {
            if (f.isDirectory()) {
                rtn.addAll(listSortedFilesInDirectory(root, f.getCanonicalPath()));
            }else{
                rtn.add(f.getCanonicalPath().substring(root.length()));
            }
        }

        Collections.sort(rtn);
        return rtn;
    }

    public static RandomAccessFile openFile(String dir, String fileName, String mode) {
        String pathname = dir + File.separator + fileName;
        try {
            return new RandomAccessFile(new File(pathname), mode);
        } catch (FileNotFoundException e) {
            log.error("{} not found", pathname);
            throw new RuntimeException(e);
        }
    }

    public static void closeFile(RandomAccessFile randomAccessFile) {
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            log.error("close file error {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * long 8字节保存CRC32
     * int 4字节保存消息的长度
     */
    public static <T extends Message> void writeProto2File(RandomAccessFile randomAccessFile, T message) {
        byte[] byteArray = message.toByteArray();
        long crc32 = getCRC32(byteArray);
        int length = byteArray.length;
        try {
            randomAccessFile.writeLong(crc32);
            randomAccessFile.writeInt(length);
            randomAccessFile.write(byteArray);
        } catch (IOException e) {
            log.error("write error with {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static <T extends Message> T readProtoFromFile(RandomAccessFile randomAccessFile, Class<T> clazz) {
        try {
            long crc32 = randomAccessFile.readLong();
            int dataLength = randomAccessFile.readInt();
            int metaLength = (Long.SIZE + Integer.SIZE) / Byte.SIZE;
            long fileLength = randomAccessFile.length();
            if (fileLength - metaLength < dataLength) {
                log.error("expected length {} but found {}", dataLength, fileLength - metaLength);
                return null;
            }

            byte[] data = new byte[dataLength];
            int read = randomAccessFile.read(data);
            if (read != dataLength) {
                log.error("read expected length {} but found {}", dataLength, read);
                return null;
            }

            long crc32Cal = getCRC32(data);
            if (crc32Cal != crc32) {
                log.error("crc32 checksum failed, expected={}, actual={}", crc32, crc32Cal);
                return null;
            }

            Method parseFrom = clazz.getMethod("parseFrom", byte[].class);
            return (T) parseFrom.invoke(clazz, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDirectoryRecursive(String dir) throws IOException {
        File tobeDeletedDirectory = new File(dir);
        File[] files = tobeDeletedDirectory.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                deleteDirectoryRecursive(f.getCanonicalPath());
            }
            // delete empty directory and file.
            boolean delete = f.delete();
            System.out.println(f.getCanonicalPath() + " deleted=" + delete);
        }
    }

    /**
     * 字节数组转为CRC（long），用于数据完整性校验。
     */
    private static long getCRC32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    public static void deleteFile(String dirName, String fileName) throws IOException {
        File file = new File(dirName + File.separator + fileName);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            return;
        }
        boolean delete = file.delete();
        log.info("{} delete={}", file.getCanonicalPath(), delete);
    }

}
