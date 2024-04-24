package daemon;

import java.util.concurrent.CountDownLatch;

public class AliveThread {
    private Thread keepAliveThread;
    private CountDownLatch keepAliveLatch = new CountDownLatch(1);

    public AliveThread(){
        this.keepAliveThread = new Thread(() -> {
            try {
                keepAliveLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        this.keepAliveThread.setDaemon(false);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("shutdown hook call");
            keepAliveLatch.countDown();
        }));
    }

    public static void main(String[] args) {
        AliveThread aliveThread = new AliveThread();
        aliveThread.keepAliveThread.start();
    }
}
