package com.ljc.eventloop;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试ScheduledExecutorService在高负荷下是否能够正常工作
 * 1）线程池只有一个线程。
 * 2）有10个任务，每个任务休眠2秒，每个任务都是每10秒执行一次。
 * 
 * 实验证明，当负载太高，会造成定时任务无法正常按时执行。
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1, r -> {
            return new Thread(r, "worker");
        });

        for (int i = 0; i < 10; i++) {
            service.scheduleAtFixedRate(new TwoSecTask(i), 0, 10, TimeUnit.SECONDS);
        }

        TimeUnit.MINUTES.sleep(1);
    }
}
