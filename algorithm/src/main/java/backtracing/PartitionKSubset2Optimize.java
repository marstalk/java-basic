package backtracing;

import java.util.HashMap;
import java.util.Map;

/**
 * optimize PartitionKSubset2.java by remove duplicate calculation.
 * 
 */
public class PartitionKSubset2Optimize {

    public static void main(String[] args) {
        PartitionKSubset2Optimize partitionKSubset = new PartitionKSubset2Optimize();
        // System.out.println(partitionKSubset.canPartitionKSubsets(new int[] { 4, 3, 2, 3, 5, 2, 1 }, 4));
        // System.out.println(partitionKSubset.canPartitionKSubsets(new int[] { 1, 2, 3, 4 }, 3));
        // System.out.println(partitionKSubset
        //         .canPartitionKSubsets(new int[] { 3, 3, 10, 2, 6, 5, 10, 6, 8, 3, 2, 1, 6, 10, 7, 2 }, 6));
        System.out.println(partitionKSubset.canPartitionKSubsets(new int[]{2,2,2,2,3,4,5}, 4));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(k > nums.length){
            return false;
        }

        int sum = 0;
        for (int v : nums) {
            sum += v;
        }

        if (sum % k != 0) {
            // remainder not zero, return false, quick fail
            return false;
        }

        // target is the bucket target.
        int target = sum / k;
        int used = 0;
        return backtrace(k, 0, nums, 0, used, target);
    }

    private Map<Integer, Boolean> mem = new HashMap<>();

    private boolean backtrace(int k, int bucket, int[] nums, int start, int used, int target) {
        // base
        if (k == 0) {
            // all the bucket is loaded, and meanwhile, every bucket equals to target, and
            // all numbers are used, because target * k = sum(numbers)
            return true;
        }

        // bucket full then next bucket
        if (bucket == target) {
            boolean rtn = backtrace(k - 1, 0, nums,0, used, target);
            // memorize the choose and the result.
            mem.put(used, rtn);
            return rtn;
        }

        if(mem.containsKey(used)){
            return mem.get(used);
        }

        // current bucket, put some numbers into it
        for (int i = start; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) {
                // the number is used, skip
                continue;
            }

            if (bucket + nums[i] > target) {
                // current is bigger than expected, skip
                continue;
            }

            // here, means this number can be put into this bucket.
            bucket += nums[i];
            used |= 1 << i;
            if (backtrace(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            bucket -= nums[i];
            used ^= 1 << i;
        }

        return false;
    }
}
