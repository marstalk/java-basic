package dp;

import java.util.Arrays;

/**
 * insert, replace, delete S1 to S2 with minimum steps.
 * rad -> apple
 */
public class GGMinEditLength2Memo {

    public static void main(String[] args) {
        GGMinEditLength2Memo minEditLength = new GGMinEditLength2Memo();
        System.out.println(minEditLength.minSteps("rad", "apple")); // 5
        System.out.println(minEditLength.minSteps("horse", "roe")); //3
        System.out.println(minEditLength.minSteps("intention", "execution")); //5
        System.out.println(minEditLength.minSteps("dinitrophenylhydrazine", "benzalphenylhydrazone"));
    }

    private int[][] memo;

    public int minSteps(String s1, String s2) {
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        memo = new int[i + 1][j + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s1, i, s2, j);
    }

    private int dp(String s1, int i, String s2, int j) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            int min = dp(s1, i - 1, s2, j - 1);
            memo[i][j] = min;
            return min;
        }

        int min = min(
                dp(s1, i, s2, j - 1) + 1,
                dp(s1, i - 1, s2, j - 1) + 1,
                dp(s1, i - 1, s2, j) + 1
        );
        memo[i][j] = min;
        return min;
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
