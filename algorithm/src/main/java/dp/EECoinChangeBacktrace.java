package dp;

import java.util.Arrays;

/**
 * 使用回溯进行 组合（可重复选），效率较低。
 */
public class EECoinChangeBacktrace {
    public static void main(String[] args) {
        EECoinChangeBacktrace coinChangeBacktrace = new EECoinChangeBacktrace();
        // System.out.println(coinChangeBacktrace.coinChange(new int[] { 1, 2, 5 },
        // 11));
        // System.out.println(coinChangeBacktrace.coinChange(new int[] { 2 }, 3));
        // System.out.println(coinChangeBacktrace.coinChange(new int[] { 1, 2147483647
        // }, 2));
        //System.out.println(coinChangeBacktrace.coinChange(new int[] { 186, 419, 83, 408 }, 6249));
        System.out.println(coinChangeBacktrace.coinChange(new int[] { 411,412,413,414,415,416,417,418,419,420,421,422}, 9864));
    }

    public int coinChange(int[] coins, int amount) {
        // 倒叙排序coins
        Arrays.sort(coins);
        desc(coins);
        backtrace(coins, amount, 0, 0, 0);
        return res;
    }

    private int res = -1;

    private void backtrace(int[] coins, int amount, int current, int cnt, int start) {
        // base
        if (current > amount) {
            return;
        }

        if (current == amount) {
            res = cnt < res ? cnt : res;
            return;
        }

        for (int i = start; i < coins.length; i++) {
            if (current + coins[i] > amount) {
                continue;
            }

            cnt++;
            current += coins[i];
            backtrace(coins, amount, current, cnt, i);
            cnt--;
            current -= coins[i];
        }
    }

    private void desc(int[] coins) {
        int l = 0;
        int r = coins.length - 1;
        while (l < r) {
            int i = coins[l];
            coins[l] = coins[r];
            coins[r] = i;
            l++;
            r--;
        }
    }
}
