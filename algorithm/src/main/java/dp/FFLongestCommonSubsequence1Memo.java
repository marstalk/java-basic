package dp;

import java.util.Arrays;

/**
 * 最长公共子序列
 */
public class FFLongestCommonSubsequence1Memo {
    public static void main(String[] args) {
        FFLongestCommonSubsequence1Memo longestCommonSubsequence = new FFLongestCommonSubsequence1Memo();
        System.out.println(longestCommonSubsequence.longestCommonSubsequence("zabcde", "acez"));
    }

    private int[][] memo;

    public int longestCommonSubsequence(String s1, String s2) {
        memo = new int[s1.length()][s2.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s1, s1.length() - 1, s2, s2.length() - 1);
    }

    private int dp(String s1, int i, String s2, int j) {
        if (i == -1) return 0;
        if (j == -1) return 0;

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res;
        if (s1.charAt(i) == s2.charAt(j)) {
            // s1[i] s2[j]位置上的字符相同，那么一定会出现在子序列中，所以等于dp(i-1, j-1)+1,
            // dp(i-1, j-1)，解释一下，它表示0~i，0~j之间，最长公共子序列。
            res = dp(s1, i - 1, s2, j - 1) + 1;
        } else {
            // 走到这里，说明，有三种可能，
            // 1. s1[i]出现在公共子序列中：dp(i, j-1)
            // 2. s2[j]出现在公共子序列中：dp(i-1, j)
            // 3. 上述两个都没有出现在公共子序列中：dp(i-1, j-1)
            // 上述三种情况，我们取其最大值即可。
            res = max(dp(s1, i - 1, s2, j),
                    dp(s1, i, s2, j - 1),
                    dp(s1, i - 1, s2, j - 1));
        }

        memo[i][j] = res;
        return res;

    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}
