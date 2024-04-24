package dp;

import java.util.Arrays;

public class EECoinChange2 {

    public static void main(String[] args) {
        EECoinChange2 coinChange = new EECoinChange2();
        System.out.println(coinChange.coinChange(new int[] { 1, 2, 5 }, 11));
        System.out.println(coinChange.coinChange(new int[] { 2 }, 3));
        System.out.println(coinChange.coinChange(new int[] { 1, 2147483647 }, 2));
        System.out.println(coinChange.coinChange(new int[] { 186, 419, 83, 408 }, 6249));
        System.out.println(
                coinChange.coinChange(new int[] { 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422 }, 9864));
    }

    private int[] mem;

    public int coinChange(int[] coins, int amount) {
        mem = new int[amount + 1];
        Arrays.fill(mem, -22);
        return dp(coins, amount);
    }

    private int dp(int[] coins, int amount) {
        // base
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (mem[amount] >= -1) {
            return mem[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subProblem = dp(coins, amount - coins[i]);

            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, subProblem + 1);
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        mem[amount] = res;
        return res;
    }
}
