package com.github.marstalk.raft.storage;

import com.github.marstalk.raft.proto.RaftProto;

import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;


public class Segment {
    public static class Record {
        long offset;
        RaftProto.LogEntry logEntry;

        public Record(long offset, RaftProto.LogEntry logEntry) {
            this.offset = offset;
            this.logEntry = logEntry;
        }
    }

    private String fileName;
    private List<Record> entries = new LinkedList<>();
    private long startIndex;
    private long endIndex;
    private long size;
    private boolean writable;
    private RandomAccessFile randomAccessFile;

    public String fileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Record> entries() {
        return entries;
    }

    public void setEntries(List<Record> entries) {
        this.entries = entries;
    }

    public long startIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long endIndex() {
        return endIndex;
    }

    public void setEndIndex(long endIndex) {
        this.endIndex = endIndex;
    }

    public long size() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean writable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public RandomAccessFile randomAccessFile() {
        return randomAccessFile;
    }

    public void setRandomAccessFile(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }
}
