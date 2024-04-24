package backtracing;

public class PartitionKSubset2 {
    public static void main(String[] args) {
        PartitionKSubset2 partitionKSubset = new PartitionKSubset2();
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

        if (sum % k != 0) {
            // remainder not zero, return false, quick fail
            return false;
        }

        // target is the bucket target.
        int target = sum / k;
        return backtrace(k, nums, new boolean[nums.length], 0, 0, target);
    }

    private boolean backtrace(int k, int[] nums, boolean[] used, int start, int bucket, int target) {
        // base
        if (k == 0) {
            // all the bucket is loaded, and meanwhile, every bucket equals to target, and
            // all numbers are used, because target * k = sum(numbers)
            return true;
        }

        // bucket full then next bucket
        if (bucket == target) {
            return backtrace(k - 1, nums, used, 0, 0, target);
        }

        // current bucket, put some numbers into it
        for (int i = start; i < nums.length; i++) {
            if (used[i]) {
                // the number is used, skip
                continue;
            }

            if (bucket + nums[i] > target) {
                // current is bigger than expected, skip
                continue;
            }

            // here, means this number can be put into this bucket.
            bucket += nums[i];
            used[i] = true;
            if (backtrace(k, nums, used, i + 1, bucket, target)) {
                return true;
            }
            bucket -= nums[i];
            used[i] = false;
        }

        return false;
    }

}
