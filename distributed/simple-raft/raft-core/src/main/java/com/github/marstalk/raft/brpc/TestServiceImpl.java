package com.github.marstalk.raft.brpc;

import com.github.marstalk.raft.proto.RaftProto;
import com.google.protobuf.ByteString;

public class TestServiceImpl implements TestService {
    @Override
    public RaftProto.LogEntry getLogEntry(RaftProto.LogEntry logEntry) {
        return RaftProto.LogEntry
                .newBuilder()
                .setData(ByteString.copyFromUtf8("this is new response"))
                .setTerm(logEntry.getTerm())
                .setIndex(logEntry.getIndex())
                .build();
    }
}
