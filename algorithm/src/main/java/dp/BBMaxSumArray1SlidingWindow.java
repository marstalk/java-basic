package dp;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 */
public class BBMaxSumArray1SlidingWindow {
    public static void main(String[] args) {
        BBMaxSumArray1SlidingWindow bbMaxSumArray1SlidingWindow = new BBMaxSumArray1SlidingWindow();
        System.out.println(bbMaxSumArray1SlidingWindow.maxSumArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        System.out.println(bbMaxSumArray1SlidingWindow.maxSumArray(new int[]{1})); // 1
        System.out.println(bbMaxSumArray1SlidingWindow.maxSumArray(new int[]{5, 4, -1, 7, 8})); // 23
        System.out.println(bbMaxSumArray1SlidingWindow.maxSumArray(new int[]{-1, -2, -3})); // -1
    }

    public int maxSumArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.length) {
            windowSum += nums[right];
            right++;

            res = Math.max(windowSum, res);

            while (windowSum < 0) {
                windowSum -= nums[left];
                left++;

                //为什么这里不需要更新res？
                //因为窗口肯定是以正数开头，如果把头部的正数移出窗口了，那么windowSum肯定比res小，所以不需要更新。
            }
        }
        return res;
    }
}
