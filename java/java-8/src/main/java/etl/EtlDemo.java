package etl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class EtlDemo {
    public static void main(String[] args) {
        Output output = new Output();
        Output output2 = new Output();
        Input input = new Input();
        input.outputList = List.of(output, output2);

        new Thread(input).start();
        new Thread(output, "output1").start();
        new Thread(output2, "output2").start();
    }
}

class Input implements Runnable{
    List<Output> outputList = new ArrayList<>();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            outputList.get(i % outputList.size()).offer(i);
        }
    }
}

class Output implements Runnable{
    BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);
    public void offer(int i){
        try {
            blockingQueue.offer(i, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer poll(){
        try {
            return blockingQueue.poll(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while(true){
            export();
        }
    }

    private void export() {
        long begin = System.currentTimeMillis();
        int batchSize = 5;
        List<Integer> batch = new ArrayList<>();
        while(System.currentTimeMillis() - begin < 3000 && batch.size() < batchSize){
            batch.add(poll());
        }
        System.out.println(Thread.currentThread().getName() + " " + batch.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
