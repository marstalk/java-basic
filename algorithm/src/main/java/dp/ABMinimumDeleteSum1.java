package dp;

import java.util.Arrays;

/**
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 * <p>
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 */
public class ABMinimumDeleteSum1 {

    public static void main(String[] args) {
        ABMinimumDeleteSum1 abMinimumDeleteSum1 = new ABMinimumDeleteSum1();
        System.out.println(abMinimumDeleteSum1.minimumDeleteSum("sea", "eat"));//231
        System.out.println(abMinimumDeleteSum1.minimumDeleteSum("delete", "leet"));//403
    }

    private int[][] memo;

    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s1, m - 1, s2, n - 1);
    }

    private int dp(String s1, int i, String s2, int j) {
        int res = 0;
        if (i == -1) {
            for (int x = 0; x <= j; x++) {
                res += s2.charAt(x);
            }
            return res;
        }

        if (j == -1) {
            for (int y = 0; y <= i; y++) {
                res += s1.charAt(y);
            }
            return res;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            res = dp(s1, i - 1, s2, j - 1);
            return res;
        }

        int a = dp(s1, i - 1, s2, j) + s1.charAt(i);
        int b = dp(s1, i, s2, j - 1) + s2.charAt(j);
        int c = dp(s1, i - 1, s2, j - 1) + s1.charAt(i) + s2.charAt(j);
        res = Math.min(a, Math.min(b, c));
        memo[i][j] = res;
        return res;
    }
}
