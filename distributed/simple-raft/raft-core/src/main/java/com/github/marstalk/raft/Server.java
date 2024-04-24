package com.github.marstalk.raft;

public class Server {
    private int id;
    private String host;
    private int port;

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String host() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int port() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
