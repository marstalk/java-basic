package backtracing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列、可重复选
 */
public class PermuteWithMultiChoice {

    public static void main(String[] args) {
        PermuteWithMultiChoice permuteWithMultiChoice = new PermuteWithMultiChoice();
        System.out.println(permuteWithMultiChoice.permuteWithMultiChoice(new int[] { 1, 2, 3 }));
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteWithMultiChoice(int[] nums) {
        LinkedList<Integer> path = new LinkedList<>();
        backtrace(nums, path);
        return res;
    }

    private void backtrace(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            path.addLast(nums[i]);
            backtrace(nums, path);
            path.removeLast();
        }
    }
}
