package com.github.marstalk.raft;

import java.util.Map;

public class ClusterConfig {
    private Map<String, Server> configNodes;
    private long electionTimeoutBaseMills;

    public Map<String, Server> configNodes() {
        return configNodes;
    }

    public void setConfigNodes(Map<String, Server> configNodes) {
        this.configNodes = configNodes;
    }

    public long electionTimeoutBaseMills() {
        return electionTimeoutBaseMills;
    }

    public void setElectionTimeoutBaseMills(long electionTimeoutBaseMills) {
        this.electionTimeoutBaseMills = electionTimeoutBaseMills;
    }
}
