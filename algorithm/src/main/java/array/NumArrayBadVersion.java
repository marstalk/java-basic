package array;

/**
 * 前缀和
 */
public class NumArrayBadVersion {
    private int[] preSum;

    public NumArrayBadVersion(int[] nums) {
        preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = nums[i] + preSum[i - 1];
        }
    }
    
    /**
     * 左闭右闭区间。
     * @param left
     * @param right
     * @return
     */
    public int sumRange(int left, int right) {
        if(left == 0){
            return preSum[right];
        }
        return preSum[right] - preSum[left - 1];
    }


    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}
