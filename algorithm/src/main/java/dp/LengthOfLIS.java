package dp;

import java.util.Arrays;

/**
 * 最长递增子序列，
 * 子串是必须连续的。
 * 子序列是非必连续的。
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        System.out.println(lengthOfLIS.lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 })); //4
        System.out.println(lengthOfLIS.lengthOfLIS(new int[] { 0, 1, 0, 3, 2, 3 })); //4
    }

    /**
     * dp[i] 表示以nums[i]结尾的最大的子串的长度，初始化为1（长度至少为1）
     * 那么 dp(i) = 1 when i = 0
     * dp(i) = max(
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i : dp) {
            res = i > res ? i : res;
        }
        
        return res;
    }
}
