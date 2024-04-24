package com.github.marstalk.raft.util;

import com.github.marstalk.raft.proto.RaftProto;
import com.google.protobuf.ByteString;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtilTest {

    @Test
    public void testWrite2File() throws IOException {
        System.out.println(new File("./data").getAbsolutePath());

        RandomAccessFile writeFile = FileUtil.openFile("./data", "testFile", "rw");

        FileUtil.writeProto2File(writeFile, RaftProto.LogEntry.newBuilder().setIndex(1)
                .setTerm(9)
                .setType(RaftProto.EntryType.ENTRY_TYPE_DATA)
                .setData(ByteString.copyFromUtf8("welcome to Proto"))
                .build());

        FileUtil.writeProto2File(writeFile, RaftProto.LogEntry.newBuilder()
                .setTerm(10L)
                .setData(ByteString.copyFromUtf8("this is niuhongling"))
                .build());
        FileUtil.closeFile(writeFile);


        RandomAccessFile readFile = FileUtil.openFile("./data", "testFile", "r");
        RaftProto.LogEntry logEntry = FileUtil.readProtoFromFile(readFile, RaftProto.LogEntry.class);
        System.out.println(logEntry.toString());

        RaftProto.LogEntry logEntry2 = FileUtil.readProtoFromFile(readFile, RaftProto.LogEntry.class);
        System.out.println(logEntry2.toString());
        FileUtil.closeFile(readFile);

        FileUtil.deleteFile("./data", "testFile");
    }

    @Test
    public void testListSortedFilesInDirectory1() throws IOException {
        // before method
        File directory = new File("./data/log/data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // test
        String dir = directory.getCanonicalPath();
        File file3 = new File(dir + File.separator + "open-40.seg");
        file3.createNewFile();
        File file1 = new File(dir + File.separator + "1-9.seg");
        file1.createNewFile();
        File file2 = new File(dir + File.separator + "10-29.seg");
        file2.createNewFile();
        File subDirectory = new File(dir + File.separator + "archive");
        subDirectory.mkdirs();
        File file4 = new File(subDirectory + File.separator + "30-39.seg");
        file4.createNewFile();

        FileUtil.listSortedFilesInDirectory(dir, dir).forEach(System.out::println);

        // after method
        FileUtil.deleteDirectoryRecursive(new File("./data").getCanonicalPath());
    }
}
