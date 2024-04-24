package com.github.marstalk.raft.brpc;

import com.baidu.brpc.client.BrpcProxy;
import com.baidu.brpc.client.RpcClient;
import com.baidu.brpc.client.channel.Endpoint;
import com.github.marstalk.raft.proto.RaftProto;

public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient(new Endpoint("127.0.0.1", 6666));
        TestService testService = BrpcProxy.getProxy(rpcClient, TestService.class);
        RaftProto.LogEntry rtn = testService.getLogEntry(RaftProto.LogEntry.newBuilder().setIndex(1).setTerm(3L).build());
        System.out.println(rtn.getData().toStringUtf8());
        System.out.println(rtn.getIndex());
        System.out.println(rtn.getTerm());
    }
}
