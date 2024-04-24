package com.github.marstalk.raft.brpc;

import com.github.marstalk.raft.proto.RaftProto;

public interface TestService {
    RaftProto.LogEntry getLogEntry(RaftProto.LogEntry logEntry);
}
