package ratelimiter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 有个排序容器，记录每个请求的时间戳，给每个时间值设置按需的TTL。
 */
public class SlidingWindowLogRateLimiter {
    private LinkedList<Long> logs;
    private int rate;
    private int windowSize;

    public SlidingWindowLogRateLimiter(int rate, int windowSize) {
        this.rate = rate;
        this.windowSize = windowSize;
        this.logs = new LinkedList<>();
    }

    public boolean tryConsume() {
        long now = Instant.now().toEpochMilli();
        Long headTimestamp = logs.peek();
        while (headTimestamp != null && now - headTimestamp > windowSize) {
            logs.poll();
            headTimestamp = logs.peek();
        }

        if (logs.size() >= rate) {
            return false;
        }
        logs.offer(now);
        return true;

    }

    public static void main(String[] args) throws InterruptedException {
        // 2/s
        SlidingWindowLogRateLimiter slidingWindowLogRateLimiter = new SlidingWindowLogRateLimiter(4, 1000);
        for (int i = 0; i < 10000; i++) {
            //1000/20 = 50
            TimeUnit.MILLISECONDS.sleep(20);
            if (slidingWindowLogRateLimiter.tryConsume()) {
                System.out.println(LocalDateTime.now());
            }
        }
    }
}
