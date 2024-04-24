package ratelimiter;

import java.time.Instant;
import java.time.LocalTime;

/**
 * token buket algorithm
 * 一开始我以为需要一个线程按照一定的时间间隔，将令牌放到桶中，但实际上这个实现方式非常的浪费线程。
 * 但实际上可以使用业务线程来完成这个事情：
 * 1. 令牌桶初始化的时候，将令牌装满桶，并记住本次装载时间。
 * 2. 当业务来获取令牌的时候，触发一次装填：
 * 2.1 当前时间和上一次装载时间比较，得到elapse
 * 2.2 elapse * rate，得到这段时间应该装填的令牌数，但是不能超过令牌桶的容量。
 * 3. 装填完毕之后，再根据当前令牌数决策是否pass。
 * <p>
 * 这就是代码思维。
 */
public class TokenBuketRateLimiter {
    private final double rate;
    private final int capacity;
    private double token;
    private Instant lastFill;
    public TokenBuketRateLimiter(double rate, int capacity) {
        this.rate = rate;
        this.capacity = capacity;
        this.token = capacity;
        this.lastFill = Instant.now();
    }

    public synchronized boolean tryConsume() {
        refill();
        if (token >= 1) {
            token--;
            return true;
        }
        return false;
    }
    private void refill() {
        Instant now = Instant.now();
        double elapsed = (double) (now.toEpochMilli() - lastFill.toEpochMilli()) / 1000.0;
        double tokensToAdd = elapsed * rate;
        token = Math.min(token + tokensToAdd, capacity);
        // Don't forget to update lastFill
        lastFill = now;
    }
    public static void main(String[] args) {
        System.out.println((134394324 - 11111) / 1000.0);

        TokenBuketRateLimiter tokenBuketRateLimiter = new TokenBuketRateLimiter(2, 2);
        while (true) {
            boolean pass = tokenBuketRateLimiter.tryConsume();
            if (pass) {
                System.out.println(LocalTime.now());
            }
        }
    }
}

