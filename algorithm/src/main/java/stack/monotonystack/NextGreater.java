package stack.monotonystack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/next-greater-element-i/discussion/">...</a>
 * input nums1 = [4,1,2], nums2 = [1,3,4,2],
 * output   [-1,3,-1]
 */
public class NextGreater {
    public static void main(String[] args) {
        NextGreater nextGreater = new NextGreater();
        int[] ints = nextGreater.nextGreater(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        System.out.println(Arrays.toString(ints));
    }
    public int[] nextGreater(int[] nums1, int[] nums2) {
        int[] res = nextGreater(nums2);
        Map<Integer, Integer> resMap = new HashMap<>();
        for (int i = 0; i < res.length; i++) {
            resMap.put(nums2[i], res[i]);
        }
        int[] rtn = new int[nums1.length];
        for (int i = 0; i < rtn.length; i++) {
            rtn[i] = resMap.get(nums1[i]);
        }
        return rtn;
    }

    private int[] nextGreater(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }
}
