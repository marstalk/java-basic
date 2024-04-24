package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 */
public class SubsetWithDuplicates {
    public static void main(String[] args) {
        SubsetWithDuplicates subsetWithDuplicates = new SubsetWithDuplicates();
        System.out.println(subsetWithDuplicates.subsets(new int[] { 1, 2, 2 ,3}));
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        LinkedList<Integer> path = new LinkedList<>();
        backtrace(nums, 0, path);
        return res;
    }

    private void backtrace(int[] nums, int start, LinkedList<Integer> path) {
        res.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            backtrace(nums, i + 1, path);
            path.removeLast();
        }
    }
}
