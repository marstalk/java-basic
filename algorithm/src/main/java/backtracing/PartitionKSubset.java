package backtracing;

/**
 * 
 * 1，遍历所有的球[1,2,3,4,65,7,8,4,2]，要求k个子集，每个子集的和相等。目标和=sum/k
 * 2，判断这个球能不能放在桶里：如果这个桶里的和已经>=目标和，则不能再放到这个桶里了。
 * 3，这里是以球的视角，即每个球都要放置在某个桶里。
 */
public class PartitionKSubset {

    public static void main(String[] args) {
        PartitionKSubset partitionKSubset = new PartitionKSubset();
        System.out.println(partitionKSubset.canPartitionKSubsets(new int[] { 4, 3, 2, 3, 5, 2, 1 }, 4));
        System.out.println(partitionKSubset.canPartitionKSubsets(new int[] { 1, 2, 3, 4 }, 3));
        System.out.println(partitionKSubset
                .canPartitionKSubsets(new int[] { 3, 3, 10, 2, 6, 5, 10, 6, 8, 3, 2, 1, 6, 10, 7, 2 }, 6));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 基本条件判断
        if (sum % k != 0) {
            return false;
        }

        // 划分的子集合的和要求是target
        int target = sum / k;

        // 初始化桶里一个球都没有，所有桶里的和是0
        int[] bucket = new int[k];
        return backtrace(nums, 0, bucket, target);
    }

    private boolean backtrace(int[] nums, int ball, int[] bucket, int target) {
        if (ball == nums.length) {
            // 球放完，判断当前的放球方式是不是符合：每个子集的和等于目标和。
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] + nums[ball] > target) {
                continue;
            }

            bucket[i] = bucket[i] + nums[ball];
            if (backtrace(nums, ball + 1, bucket, target)) {
                return true;
            }
            bucket[i] = bucket[i] - nums[ball];
        }
        // 走到这里，说明，对于某一个数字来说，没有一个桶可以容纳这个数（每个桶里的和+这个数>target），也就是说已经无法做到题目要求的了，可以返回false。

        return false;
    }
}
