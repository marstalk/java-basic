package com.github.marstalk.raft;

import com.baidu.brpc.client.RpcCallback;
import com.github.marstalk.raft.proto.RaftProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaftNode {
    public enum NodeState {
        LEADER,
        FOLLOWER,
        CANDIDATE,
        PRE_CANDIDATE;
    }

    private static Logger log = LoggerFactory.getLogger(RaftNode.class);
    // persistent state
    private long currentTerm;
    private int voteFor;

    // volatile state on all server
    private int commitIndex;
    private int lastApplied;

    //volatile state on leader
    private Map<String, Peer> peerMap = new ConcurrentHashMap<>();
    private Lock lock = new ReentrantLock();
    //
    private ClusterConfig clusterConfig;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private ThreadPoolExecutor threadPoolExecutor;
    private ScheduledFuture<Void> electionSchedulerFuture;
    private Peer localNode;
    private NodeState nodeState;

    public RaftNode(Server thisServer, ClusterConfig config) {
        this.localNode = new Peer(thisServer.id(), thisServer.host(), thisServer.port());

        for (Map.Entry<String, Server> entry : config.configNodes().entrySet()) {
            Server server = entry.getValue();
            peerMap.put(entry.getKey(), new Peer(server.id(), server.host(), server.port()));
        }

        this.nodeState = NodeState.FOLLOWER;

        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
                2,
                r -> new Thread(r, "scheduler"));
        this.threadPoolExecutor = new ThreadPoolExecutor(
                5,
                5,
                1,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(1000),
                r -> new Thread(r, "worker"));
    }

    public void start() {
        resetElectionTimer();
    }

    private void resetElectionTimer() {
        if (electionSchedulerFuture != null && !electionSchedulerFuture.isDone()) {
            electionSchedulerFuture.cancel(true);
        }
        scheduledThreadPoolExecutor.schedule(this::startPreVote, getElectionTimeout(), TimeUnit.MILLISECONDS);
    }

    private long getElectionTimeout() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        long electionTimeout = clusterConfig.electionTimeoutBaseMills() + current.nextInt(150);
        log.debug("{} election timeout {}", localNode.id(), electionTimeout);
        return electionTimeout;
    }

    private void startPreVote() {
        this.nodeState = NodeState.PRE_CANDIDATE;
        for (Peer peer : peerMap.values()) {
            if (peer.id() == (localNode.id())) {
                continue;
            }

            threadPoolExecutor.submit(() -> preVote(peer));
        }
        resetElectionTimer();
    }

    private void preVote(Peer peer) {
        RaftProto.VoteRequest request = RaftProto.VoteRequest.newBuilder().setServerId(localNode.id())
                .setTerm(this.currentTerm + 1)
                //TODO
                .setLastLogTerm(1)
                .setLastLogIndex(1).build();
        peer.consensusServiceAsync().preVoteAsync(request, new PVoteCallback(peer, request));
    }

    static class PVoteCallback implements RpcCallback<RaftProto.VoteResponse> {
        private Peer peer;
        private RaftProto.VoteRequest request;

        public PVoteCallback(Peer peer, RaftProto.VoteRequest request) {
            this.peer = peer;
            this.request = request;
        }

        @Override
        public void success(RaftProto.VoteResponse response) {
            //如果获得半数以上选票，则进行真正的
        }

        @Override
        public void fail(Throwable throwable) {

        }
    }

    public long currentTerm() {
        return currentTerm;
    }

    public int voteFor() {
        return voteFor;
    }

    public int commitIndex() {
        return commitIndex;
    }

    public int lastApplied() {
        return lastApplied;
    }

    public Map<String, Peer> peerMap() {
        return peerMap;
    }

    public Lock lock() {
        return lock;
    }
}
