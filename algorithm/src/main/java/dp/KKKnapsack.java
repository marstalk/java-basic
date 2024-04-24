package dp;

public class KKKnapsack {
    public static void main(String[] args) {
        KKKnapsack kkPackageIssue = new KKKnapsack();
        System.out.println(kkPackageIssue.maxValue(3, 4, new int[]{2, 1, 3}, new int[]{4, 2, 3}));//6
    }

    public int maxValue(int n, int w, int[] wt, int[] val) {
        return dp(n - 1, w, wt, val);
    }

    private int dp(int i, int j, int[] wt, int[] val) {
        if (i == -1) return 0;
        if (j == 0) return 0;

        // if item i exceeds the remaining capability of the backpack. so can't put it into package.
        if (wt[i] > j) {
            return dp(i - 1, j, wt, val);
        }

        return Math.max(
                // the value of item i is selected, then use left capability to select left.
                val[i] + dp(i - 1, j - wt[i], wt, val),
                // the value of item i is not selected. the use current capability to select left.
                dp(i - 1, j, wt, val));
    }
}
