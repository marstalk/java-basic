package ratelimiter;

import java.time.Instant;
import java.time.LocalTime;

/**
 * 相比较TokenBucket，没有那么丝滑。
 */
public class FixedWindowRateLimiter {
    // 窗口大小
    private final long windowDurationMillis;
    // 每个窗口内可以放过去的请求量
    private final int capacity;
    private int counter;
    private Instant windowStart;

    public FixedWindowRateLimiter(int windowDurationMillis, int capacity) {
        this.windowDurationMillis = windowDurationMillis;
        this.capacity = capacity;
        this.counter = 0;
        this.windowStart = Instant.now();
    }

    public synchronized boolean tryConsume() {
        refreshWindow();

        if (counter < capacity) {
            counter++;
            return true;
        }
        return false;
    }

    /**
     * 对于高流量，会高频率的触发refreshWindow方法，但是有时间窗口到了，才会清空count，窗口很规整。而且，流量都集中在窗口头部，导致流量不是很均匀。
     * 对于低流量，窗口不规整，会有缺口。
     * <p>
     * 对于边缘流量而言，有可能超过限流的速度。通过滑动窗口日志算法解决。
     */
    private void refreshWindow() {
        Instant now = Instant.now();
        long elapsed = now.toEpochMilli() - windowStart.toEpochMilli();
        if (elapsed >= windowDurationMillis) {
            counter = 0;
            windowStart = now;
        }
    }

    public static void main(String[] args) {
        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(1000, 2);
        while (true) {
            boolean pass = fixedWindowRateLimiter.tryConsume();
            if (pass) {
                System.out.println(LocalTime.now());
            }
        }
    }

}
