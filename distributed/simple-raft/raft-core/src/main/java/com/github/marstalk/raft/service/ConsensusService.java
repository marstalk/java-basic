package com.github.marstalk.raft.service;

import com.github.marstalk.raft.proto.RaftProto;

/**
 * 集群内部服务。
 */
public interface ConsensusService {

    /**
     * 预选举
     *
     * @param request
     * @return
     */
    RaftProto.VoteResponse preRequestVote(RaftProto.VoteRequest request);

    /**
     * 选举
     *
     * @param request
     * @return
     */
    RaftProto.VoteResponse requestVote(RaftProto.VoteRequest request);

    /**
     * 心跳和日志复制
     *
     * @param request
     * @return
     */
    RaftProto.AppendEntriesResponse appendEntries(RaftProto.AppendEntriesRequest request);
}
