package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum-ii/
 */
public class CombinationSum {
    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8));
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        if (nums == null) {
            return res;
        }

        Arrays.sort(nums);
        reverse(nums);
        backtrace(nums, 0, new LinkedList<>(), 0, target);
        return res;
    }

    private void backtrace(int[] nums, int start, LinkedList<Integer> path, int sum, int target) {
        // base
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        // loop choice
        for (int i = start; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                continue;
            }

            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            sum += nums[i];
            backtrace(nums, i + 1, path, sum, target);
            path.removeLast();
            sum -= nums[i];
        }
    }

    private void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }
}
