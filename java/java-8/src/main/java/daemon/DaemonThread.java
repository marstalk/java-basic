package daemon;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DaemonThread {


    public static void main(String[] args) {
        System.out.println("main thread daemon=" + Thread.currentThread().isDaemon());

        Thread keepAliveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true){
                    try {
                        System.out.println(i++);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        keepAliveThread.setDaemon(true);
        keepAliveThread.start();
        System.out.println("custom thread daemon=" + keepAliveThread.isDaemon());
    }
}
