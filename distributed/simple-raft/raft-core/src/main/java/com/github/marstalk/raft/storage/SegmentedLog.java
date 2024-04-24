package com.github.marstalk.raft.storage;

import com.github.marstalk.raft.proto.RaftProto;
import com.github.marstalk.raft.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.TreeMap;

public class SegmentedLog {
    private static Logger log = LoggerFactory.getLogger(SegmentedLog.class);
    private final TreeMap<Long, Segment> segmentTreeMap = new TreeMap<>();
    private RaftProto.Metadata metaData;
    private long segmentMaxSize;
    private final String rootDir;
    private final String segmentDir;

    // TODO 记录整个segmentedLog保存的字节大小，在appendEntries和truncate的时候对应的增加和减少。
    private volatile long totalSize;

    public SegmentedLog(long segmentMaxSize, String rootDir) throws IOException {
        this.segmentMaxSize = segmentMaxSize;
        this.rootDir = rootDir;

        /**
         * 1. dir to place segmented log file,
         * 2. maximum log size for a file.
         * 3. root, root/log, root/log/data，
         * 4. create directory if not exists.
         * 5. read meta data of existed segment file, then load data.
         * 6. read meta data??( term
         */

        segmentDir = rootDir + File.separator + "data";

        createIfNotExists(segmentDir);

        loadSegmentsIfExist();
        loadOrCreateMetaData();
    }

    /**
     * Get log entry by index.
     */
    public RaftProto.LogEntry getLogEntry(long index) {
        if (segmentTreeMap.isEmpty()) {
            return null;
        }
        //TODO
        return null;
    }

    /**
     * 更新元数据信息
     */
    public void updateMetaData(Integer currentTerm, Integer voteFor, Long firstLogIndex, Long commitIndex) {
        RaftProto.Metadata.Builder builder = RaftProto.Metadata.newBuilder(this.metaData);
        if (currentTerm != null) {
            builder.setCurrentTerm(currentTerm);
        }
        if (voteFor != null) {
            builder.setVoteFor(voteFor);
        }
        if (firstLogIndex != null) {
            builder.setFirstLogIndex(firstLogIndex);
        }
        if (commitIndex != null) {
            builder.setLastCommitIndex(commitIndex);
        }
        this.metaData = builder.build();
        // TODO，这里有个疑惑，metadata没有持久化，能用吗？this.metaData = builder.build()和下方的写文件，如何做到一致性？

        File file = new File(rootDir + File.separator + "metadata");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            FileUtil.writeProto2File(randomAccessFile, this.metaData);
            log.info("updated metadata: currentTerm={}, voteFor={}, firstLogIndex={}, commitIndex={}", currentTerm, voteFor, firstLogIndex, commitIndex);
        } catch (IOException e) {
            log.error("update metadata error: msg={}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private void loadOrCreateMetaData() {
        File file = new File(rootDir + File.separator + "metadata");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            this.metaData = FileUtil.readProtoFromFile(randomAccessFile, RaftProto.Metadata.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (this.metaData == null && !this.segmentTreeMap.isEmpty()) {
            // meta data is empty, suggests that this node is a brand-new node. and it shouldn't contain any log entry.
            throw new RuntimeException("meta data is empty, but found log entries, please check dir " + rootDir);
        }

        if (this.metaData == null) {
            // TODO why the initial first log index is 1 not 0 ?
            this.metaData = RaftProto.Metadata.newBuilder().setFirstLogIndex(1).build();
        }
    }

    public RaftProto.Metadata getMetaData() {
        return this.metaData;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    private void loadSegmentsIfExist() throws IOException {
        List<String> nameList = FileUtil.listSortedFilesInDirectory(segmentDir, segmentDir);
        nameList.forEach(fileName -> {
            try {
                Segment segment = loadSegment(segmentDir, fileName);
                this.segmentTreeMap.put(segment.startIndex(), segment);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * aa-bb.seg
     * parent/cc-dd.seg
     */
    private Segment loadSegment(String logDataDir, String fileName) throws IOException {
        Segment segment = new Segment();
        segment.setFileName(fileName);
        String shortFileName = fileName;
        if (fileName.contains(File.separator)) {
            shortFileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
        }

        shortFileName = shortFileName.substring(shortFileName.indexOf("."));
        String[] split = shortFileName.split("-");
        long startIndex;
        long endIndex = 0;
        RandomAccessFile randomAccessFile;
        boolean writable;
        if (shortFileName.startsWith("open")) {
            startIndex = Long.parseLong(split[1]);
            randomAccessFile = FileUtil.openFile(logDataDir, fileName, "rw");
            writable = true;
        } else {
            startIndex = Long.parseLong(split[0]);
            endIndex = Long.parseLong(split[1]);
            randomAccessFile = FileUtil.openFile(logDataDir, fileName, "r");
            writable = false;
        }
        segment.setStartIndex(startIndex);
        segment.setEndIndex(endIndex);
        segment.setRandomAccessFile(randomAccessFile);
        segment.setSize(randomAccessFile.length());
        segment.setWritable(writable);

        loadLogEntry(segment);
        return segment;
    }

    private void loadLogEntry(Segment segment) {
        long size = segment.size();
        long offset = 0;

        // try to load all log entries into memory.
        // after reading the filePointer of randomAccessFile goes to
        // 1. for close segment, the end of file.
        // 2. for open segment, the position to write a log.
        try (RandomAccessFile randomAccessFile = segment.randomAccessFile()) {
            while (offset < size) {
                RaftProto.LogEntry logEntry = FileUtil.readProtoFromFile(randomAccessFile, RaftProto.LogEntry.class);
                if (logEntry == null) {
                    throw new RuntimeException("read segment log failed");
                }
                Segment.Record record = new Segment.Record(offset, logEntry);
                segment.entries().add(record);
                try {
                    offset = randomAccessFile.getFilePointer();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // set startIndex and endIndex for this segment
        List<Segment.Record> entries = segment.entries();
        if (entries.size() > 0) {
            segment.setStartIndex(entries.get(0).logEntry.getIndex());
            segment.setEndIndex(entries.get(entries.size() - 1).logEntry.getIndex());
        }
    }

    private void createIfNotExists(String dir) {
        File logDirFile = new File(dir);
        if (!logDirFile.exists()) {
            logDirFile.mkdir();
        }
    }


}
