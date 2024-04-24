package dp;

import java.util.HashMap;
import java.util.Map;

public class EECoinChange {

    public static void main(String[] args) {
        EECoinChange coinChange = new EECoinChange();
        System.out.println(coinChange.coinChange(new int[] { 1, 2, 5 },11));
        System.out.println(coinChange.coinChange(new int[] { 2 }, 3));
        System.out.println(coinChange.coinChange(new int[] { 1, 2147483647}, 2));
        System.out.println(coinChange.coinChange(new int[] { 186, 419, 83, 408 }, 6249));
        System.out.println(coinChange.coinChange(new int[] { 411,412,413,414,415,416,417,418,419,420,421,422}, 9864));
    }

    public int coinChange(int[] coins, int amount) {
        return dp(coins, amount);
    }

    Map<Integer, Integer> mem = new HashMap<>();

    private int dp(int[] coins, int amount) {
        // base
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subProblem = 0;
            int key = amount - coins[i];
            if (mem.containsKey(key)) {
                subProblem = mem.get(key);
            } else {
                subProblem = dp(coins, key);
                mem.put(key, subProblem);
            }

            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, subProblem + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
