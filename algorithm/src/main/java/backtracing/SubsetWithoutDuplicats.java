package backtracing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 */
public class SubsetWithoutDuplicats {

    private List<List<Integer>> res = new ArrayList<>();
    private LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrace(nums, 0);
        return res;
    }

    private void backtrace(int[] nums, int start){

        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            track.addLast(nums[i]);
            backtrace(nums, i + 1);
            track.removeLast();
        }
    }

}
