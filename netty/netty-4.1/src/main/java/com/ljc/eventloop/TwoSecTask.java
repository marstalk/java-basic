package com.ljc.eventloop;

import java.util.concurrent.TimeUnit;

/**
 * Runnable with 2 second thread sleep
 */
public class TwoSecTask implements Runnable {
    private int id;
    private long timestamps;

    public TwoSecTask(int id) {
        this.id = id;
        this.timestamps = System.currentTimeMillis();
    }

    @Override
    public void run() {
        System.out.println(id + " begin after " + (System.currentTimeMillis() - timestamps) / 1000);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
