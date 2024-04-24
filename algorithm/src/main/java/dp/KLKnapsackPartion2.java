package dp;

/**
 * 数组分组，使得两个组之和相等。
 */
public class KLKnapsackPartion2 {
    public static void main(String[] args) {
        KLKnapsackPartion2 klKnapsack2 = new KLKnapsackPartion2();
        System.out.println(klKnapsack2.canPartition(new int[]{1, 5, 11, 5}));// true;
        System.out.println(klKnapsack2.canPartition(new int[]{1, 3, 2, 5})); // false
        System.out.println(klKnapsack2.canPartition(new int[]{2, 3, 2, 5})); // false
        System.out.println(klKnapsack2.canPartition(new int[]{1, 1})); // true
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) return false;

        int cap = sum / 2;
        boolean[] dp = new boolean[cap + 1];
        dp[0] = true; // when j(knapsack) is empty, then return true, means full.
        for (int i = 1; i < nums.length; i++) {
            for (int j = cap; j >= 0; j--) {
                if (j >= nums[i]) {
                    // can put into knapsack
                    dp[j] = dp[j] | dp[j - nums[i]];
                } else {
                    // can't put into knapsack
                    // dp[j] = dp[j] is equivalent to dp[i][j] = dp[i-1][j]
                }
            }
        }
        return dp[cap];
    }
}
