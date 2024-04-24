package com.github.marstalk.raft;

import com.baidu.brpc.client.RpcClient;
import com.baidu.brpc.client.channel.Endpoint;
import com.github.marstalk.raft.service.ConsensusServiceAsync;

/**
 * 组成集群的节点信息
 */
public class Peer {
    /**
     * 记录着本节点的节点 TODO
     */
    private int nextIndex;
    private int matchIndex;

    private int id;
    private String host;
    private int port;

    private RpcClient client;
    private ConsensusServiceAsync consensusServiceAsync;
    private volatile boolean voteGranted;//多个线程查询此变量，需要volatile来修饰，使得变更之后对其他线程立马可见。

    public Peer(int id, String host, int port) {
        this.id = id;
        this.host = host;
        this.port = port;

        //
        client = new RpcClient(new Endpoint(host, port));
        this.consensusServiceAsync = RpcClient.getProxy(client, ConsensusServiceAsync.class);
    }

    public int id() {
        return id;
    }

    public ConsensusServiceAsync consensusServiceAsync() {
        return consensusServiceAsync;
    }

    public boolean voteGranted() {
        return voteGranted;
    }

    public void setVoteGranted(boolean voteGranted) {
        this.voteGranted = voteGranted;
    }
}
