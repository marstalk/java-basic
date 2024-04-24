package dp;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * <p>
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 */
public class CCLongestPalindromeSubSeq3DpArray2 {
    public static void main(String[] args) {
        CCLongestPalindromeSubSeq3DpArray2 caLongestPalindromeSubSeq1Dp = new CCLongestPalindromeSubSeq3DpArray2();
        System.out.println(caLongestPalindromeSubSeq1Dp.longestPalindromeSubSeq("bbbab"));//4
        System.out.println(caLongestPalindromeSubSeq1Dp.longestPalindromeSubSeq("cbbd"));// 2


    }

    public int longestPalindromeSubSeq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // init dp with diagonal: substring of s[i,i] contains on character, to the longest palindrome is 1.
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // loop from top to bottom: the top right part the dp[][], and exclude the diagonal.
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                int y = j + i;
                if (s.charAt(i) == s.charAt(y)) {
                    // the two new added two character happens to construct palindrome.
                    // so the longest palindrome is 2 plus substring of s[i+1, j-1];
                    dp[i][y] = dp[i + 1][y - 1] + 2;
                } else {
                    // if two new added character can't construct the palindrome, so the answer will the bigger one:
                    // longest palindrome of s[i+1, j] and s[i, j-1]
                    dp[i][y] = Math.max(dp[i + 1][y], dp[i][y - 1]);
                }
            }
        }

        // return longest palindrome of the substring of s[0, n-1] aka s
        return dp[0][n - 1];
    }

}
