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
public class CCLongestPalindromeSubSeq1Dp {
    public static void main(String[] args) {
        CCLongestPalindromeSubSeq1Dp caLongestPalindromeSubSeq1Dp = new CCLongestPalindromeSubSeq1Dp();
        System.out.println(caLongestPalindromeSubSeq1Dp.longestPalindromeSubSeq("bbbab"));//4
        System.out.println(caLongestPalindromeSubSeq1Dp.longestPalindromeSubSeq("cbbd"));// 2
    }

    /**
     * 有重叠子问题。
     */
    public int longestPalindromeSubSeq(String s) {
        return dp(s, 0, s.length() - 1);
    }

    private int dp(String s, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }

        // 如果s[i] == s[j]，那么就等于没有这两个元素的子集的解+2，
        if (s.charAt(i) == s.charAt(j)) {
            return dp(s, i + 1, j - 1) + 2;
        }
        // 走到这里，说明s[i] != s[j]，那么就从三种情况中选择一个最大的作为解。

        // [i, j-1]，s[i]出现在最长回文中或者不出现在回文中：
        int x = dp(s, i, j - 1);
        // [i+1, j]，s[j]出现或者不出现在回文中：
        int y = dp(s, i + 1, j);
        // [i+1, j-1]，s[i], s[j]都没有出现在回文中。
        int z = dp(s, i + 1, j - 1);
        // 取其最大值即可。
        return Math.max(Math.max(x, y), z);

    }

}
