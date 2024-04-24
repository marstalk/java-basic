package com.github.marstalk.raft.service;

import com.baidu.brpc.client.RpcCallback;
import com.github.marstalk.raft.proto.RaftProto;

import java.util.concurrent.Future;

public interface ConsensusServiceAsync extends ConsensusService {
    Future<RaftProto.VoteResponse> preVoteAsync(RaftProto.VoteRequest request, RpcCallback<RaftProto.VoteResponse> callback);

    Future<RaftProto.VoteResponse> voteAsync(RaftProto.VoteRequest request, RpcCallback<RaftProto.VoteResponse> callback);

    Future<RaftProto.AppendEntriesRequest> appendEntriesAsync(RaftProto.AppendEntriesRequest request, RpcCallback<RaftProto.AppendEntriesResponse> callback);

}
