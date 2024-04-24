package com.github.marstalk.raft.service.impl;

import com.github.marstalk.raft.RaftNode;
import com.github.marstalk.raft.proto.RaftProto;
import com.github.marstalk.raft.service.ConsensusService;

public class ConsensusServiceImpl implements ConsensusService {
    private RaftNode thisNode;

    public ConsensusServiceImpl(RaftNode thisNode) {
        this.thisNode = thisNode;
    }

    @Override
    public RaftProto.VoteResponse preRequestVote(RaftProto.VoteRequest request) {
        thisNode.lock().lock();
        try {
            return handlePreRequestVote(request);
        } finally {
            thisNode.lock().unlock();
        }
    }

    @Override
    public RaftProto.VoteResponse requestVote(RaftProto.VoteRequest request) {
        thisNode.lock().lock();
        try {
            return handleRequestVote(request);
        } finally {
            thisNode.lock().unlock();
        }
    }

    @Override
    public RaftProto.AppendEntriesResponse appendEntries(RaftProto.AppendEntriesRequest request) {
        thisNode.lock().lock();
        try {
            return handleAppendEntries(request);
        } finally {
            thisNode.lock().unlock();
        }
    }

    private RaftProto.VoteResponse handlePreRequestVote(RaftProto.VoteRequest request) {
        // if requester
        return null;
    }

    private RaftProto.VoteResponse handleRequestVote(RaftProto.VoteRequest request) {
        return null;
    }

    private RaftProto.AppendEntriesResponse handleAppendEntries(RaftProto.AppendEntriesRequest request) {
        return null;
    }
}
