package array;

/**
 * 前缀和
 */
public class NumArray {
    private int[] preSum;

    public NumArray(int[] nums) {
        // version2 ，将preSum的长度增加1，preSum[i]表示0~(i-1)项的和
        preSum = new int[nums.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }
    
    /**
     * 左闭右闭区间。
     * @param left
     * @param right
     * @return
     */
    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }


    public static void main(String[] args) {
        NumArrayBadVersion numArray = new NumArrayBadVersion(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}
