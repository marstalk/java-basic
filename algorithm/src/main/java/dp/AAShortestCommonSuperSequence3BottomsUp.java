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
public class AAShortestCommonSuperSequence3BottomsUp {
    public static void main(String[] args) {
        AAShortestCommonSuperSequence3BottomsUp hShortestCommonSuperSequence1 = new AAShortestCommonSuperSequence3BottomsUp();
        System.out.println(hShortestCommonSuperSequence1.scss("abac", "cab")); // 5
        System.out.println(hShortestCommonSuperSequence1.scss("aaaaaaaa", "aaaaaaaa")); // 8
    }

    /**
     * dp(i, j)表示s1[0,i),s2[0,j)这个子问题的解，即他们的最短公共超序列。
     */
    public int scss(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        // init
        for (int i = 0; i < m; i++) {
            dp[i][0] = i + 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = j + 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int min;
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    min = dp[i - 1][j - 1] + 1;
                } else {
                    // str1[i]没有出现在超序列中
                    int a = dp[i - 1][j] + 1;
                    int b = dp[i][j - 1] + 1;
                    int c = dp[i - 1][j - 1] + 2;
                    min = Math.min(a, Math.min(b, c));
                }

                dp[i][j] = min;
            }
        }
        return dp[m - 1][n - 1];
    }
}
