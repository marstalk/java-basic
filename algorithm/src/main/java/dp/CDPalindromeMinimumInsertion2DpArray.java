package dp;

/**
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 * <p>
 * 请你返回让 s 成为回文串的 最少操作次数 。
 * <p>
 * 「回文串」是正读和反读都相同的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "zzazz"
 * 输出：0
 * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
 * 示例 2：
 * 输入：s = "mbadm"
 * 输出：2
 * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
 * 示例 3：
 * 输入：s = "leetcode"
 * 输出：5
 * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
 */
public class CDPalindromeMinimumInsertion2DpArray {
    public static void main(String[] args) {
        CDPalindromeMinimumInsertion2DpArray cbPalindromeMinimumInsertion1 = new CDPalindromeMinimumInsertion2DpArray();
        System.out.println(cbPalindromeMinimumInsertion1.minInsertions("zzazz")); // 0
        System.out.println(cbPalindromeMinimumInsertion1.minInsertions("mbadm")); // 2
        System.out.println(cbPalindromeMinimumInsertion1.minInsertions("leetcode")); // 5
    }

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // init diagonal with value of 0, which means that substring of s[i,i] is palindrome already, and need zero step to become a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        // from bottoms up, loop the rop right part of dp array. in order to find dp[0, n-1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int res;
                if (s.charAt(i) == s.charAt(j)) {
                    res = dp[i + 1][j - 1];
                } else {
                    //
                    int a = dp[i + 1][j - 1] + 2;
                    int b = dp[i][j - 1] + 1;
                    int c = dp[i + 1][j] + 1;
                    res = Math.min(a, Math.min(b, c));
                }
                dp[i][j] = res;
            }
        }

        return dp[0][n - 1];
    }
}
