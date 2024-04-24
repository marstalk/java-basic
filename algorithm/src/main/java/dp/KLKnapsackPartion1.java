package dp;

/**
 * 数组分组，使得两个组之和相等。
 */
public class KLKnapsackPartion1 {
    public static void main(String[] args) {
        KLKnapsackPartion1 klKnapsack2 = new KLKnapsackPartion1();
        System.out.println(klKnapsack2.canPartition(new int[]{1, 5, 11, 5}));// true;
        System.out.println(klKnapsack2.canPartition(new int[]{1, 3, 2, 5})); // false
        System.out.println(klKnapsack2.canPartition(new int[]{2, 3, 2, 5})); // false
        System.out.println(klKnapsack2.canPartition(new int[]{1,1})); // true
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) return false;
        return dp(nums, nums.length - 1, sum / 2);
    }

    private boolean dp(int[] nums, int i, int j) {
        if (j == 0) return true;
        if (i == 0) return false;

        boolean res;
        if (j < nums[i]) {
            res = dp(nums, i - 1, j);
        } else {
            boolean a = dp(nums, i - 1, j - nums[i]);
            boolean b = dp(nums, i - 1, j);
            res = a | b;
        }
        return res;
    }
}
