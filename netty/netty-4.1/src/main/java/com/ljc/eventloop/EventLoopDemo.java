package com.ljc.eventloop;

import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * 我们在ScheduledExecutorService中感受到了在高负载的情况下，定时任务无法按照我们设定的频次执行。<br>
 * 
 * TODO，验证
 */
public class EventLoopDemo {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new EmbeddedChannel();

        for (int i = 0; i < 10; i++) {
            EventLoop eventloop = channel.eventLoop();
            eventloop.scheduleAtFixedRate(new TwoSecTask(i), 0, 10, TimeUnit.SECONDS);
        }

        TimeUnit.MINUTES.sleep(10);

    }
}
