package dp;

import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * The answer is guaranteed to fit into a signed 32-bit integer.
 */
public class KMCoinChange2Dp {
    public static void main(String[] args) {
        KMCoinChange2Dp kmCoinChange = new KMCoinChange2Dp();
        System.out.println(kmCoinChange.change(new int[]{1, 2, 5}, 5)); // 4;
    }

    /*
    dp[i][j] represent that:
    dp[0][j]: no coins to select, always return 0;
    dp[i][0]: to make up amount 0, always return 1;
     */
    public int change(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount + 1];

        Arrays.fill(dp[0], 0);// if there is no coins, then zero ways to make up target amount.
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = 1;// to make up 0, there are always to way: select none.
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j < coins[i-1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - coins[i-1]] + dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }

}
