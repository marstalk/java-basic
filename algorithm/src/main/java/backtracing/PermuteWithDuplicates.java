package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PermuteWithDuplicates {

    public static void main(String[] args) {
        PermuteWithDuplicates permuteWithDuplicates = new PermuteWithDuplicates();
        System.out.println(permuteWithDuplicates.permute(new int[]{1,1,2}));
    }

    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        backtrace(nums, used, path);
        return res;
    }

    private void backtrace(int[] nums, boolean[] used, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // skip duplicates
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;
            backtrace(nums, used, path);
            path.removeLast();
            used[i] = false;
        }
    }
}
