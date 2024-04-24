package dp;

import java.util.Arrays;

/**
 * 1. dp[amount] represents the min change of amount
 * 2. dp[amount] = 1 + min(dp[amount - coin1], dp[amount - coin2] ... )
 */
public class EECoinChangeDpTable {
    public static void main(String[] args) {
        EECoinChangeDpTable coinChange = new EECoinChangeDpTable();
        System.out.println(coinChange.coinChange(new int[] { 1, 2, 5 }, 11));;
    }

    private int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int curAmount = 0; curAmount <= amount; curAmount++) {
            for (int coin : coins) {
                if (curAmount - coin < 0) {
                    continue;
                }
                dp[curAmount] = Math.min(dp[curAmount], dp[curAmount - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
