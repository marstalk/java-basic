package dp;

import java.util.Arrays;

/**
 * 最长公共子序列
 */
public class FFLongestCommonSubsequence2BottomsUp {
    public static void main(String[] args) {
        FFLongestCommonSubsequence2BottomsUp longestCommonSubsequence = new FFLongestCommonSubsequence2BottomsUp();
        System.out.println(longestCommonSubsequence.longestCommonSubsequence("zabcde", "acez")); // 3
        System.out.println(longestCommonSubsequence.longestCommonSubsequence("bsbininm", "jmjkbkjkv")); // 1
    }


    public int longestCommonSubsequence(String s1, String s2) {
        //遍历的方式
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                int max = max(
                        dp[i - 1][j],
                        dp[i][j - 1],
                        dp[i - 1][j - 1]);
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    max = 1 + dp[i-1][j-1];
                }
                dp[i][j] = max;
            }
        }
        return dp[s1.length()][s2.length()];
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}
