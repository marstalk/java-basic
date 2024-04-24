package dp;

/**
 * 给你两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为 子序列 的最短字符串。如果答案不止一个，则可以返回满足条件的 任意一个 答案。
 * 如果从字符串 t 中删除一些字符（也可能不删除），可以得到字符串 s ，那么 s 就是 t 的一个子序列。
 * <p>
 * 输入：str1 = "abac", str2 = "cab"
 * 输出："cabac"
 * 解释：
 * str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。
 * str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
 * 最终我们给出的答案是满足上述属性的最短字符串。
 * <p>
 * 输入：str1 = "aaaaaaaa", str2 = "aaaaaaaa"
 * 输出："aaaaaaaa"
 */
public class AAShortestCommonSuperSequence1 {
    public static void main(String[] args) {
        AAShortestCommonSuperSequence1 hShortestCommonSuperSequence1 = new AAShortestCommonSuperSequence1();
        System.out.println(hShortestCommonSuperSequence1.scss("abac", "cab")); // 5
        System.out.println(hShortestCommonSuperSequence1.scss("aaaaaaaa", "aaaaaaaa")); // 8
    }
    public int scss(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        return dp(str1, m - 1, str2, n - 1);
    }

    /**
     * dp(i, j)表示s1[0,i],s2[0,j]这个子问题的解，即他们的最短公共超序列。
     */
    private int dp(String s1, int i, String s2, int j) {
        // base case, if anyone of them is empty, the shortest common super sequence is another one's length;
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        int res;
        // if s1[i] == s2[j], dp(i,j) == dp(i-1, j-1) + 1;
        if (s1.charAt(i) == s2.charAt(j)) {
            res = dp(s1, i - 1, s2, j - 1) + 1;
        } else {
            // if s1[i] not in the super sequence, then dp(i,j) = dp(i-1, j) + 1
            int a = dp(s1, i - 1, s2, j) + 1;
            // if s2[j] not in the super sequence, then dp(i,j) = dp(i, j-1) + 1
            int b = dp(s1, i, s2, j - 1) + 1;
            // if s1[i] and s2[j] are both not in the super sequence, then dp(i,j) = dp(i-1, j-1) + 2
            int c = dp(s1, i - 1, s2, j - 1) + 2;
            // get the minimum of a, b, c
            res = Math.min(a, Math.min(b, c));
        }

        return res;
    }
}
