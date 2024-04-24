package com.github.marstalk.raft.brpc;

import com.baidu.brpc.server.RpcServer;

import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        RpcServer rpcServer = new RpcServer(6666);
        TestService testService = new TestServiceImpl();
        rpcServer.registerService(testService);
        rpcServer.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
