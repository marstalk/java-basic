package backtracing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 可重复选
 */
public class CombinationSumWithMultiChoice {
    public static void main(String[] args) {
        CombinationSumWithMultiChoice combinationSumWithMultiChoice = new CombinationSumWithMultiChoice();
        System.out.println(combinationSumWithMultiChoice.combinationSum(new int[] { 2, 3, 6, 7 }, 7));
    }

    private List<List<Integer>> res = new ArrayList<>();
    private LinkedList<Integer> path = new LinkedList<>();
    private int currentSum = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //TODO sort candidates in descend order
        backtrace(candidates, 0, target);
        return res;
    }

    private void backtrace(int[] candidates, int start, int target) {
        if (currentSum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        

        for (int i = start; i < candidates.length; i++) {
            if(currentSum + candidates[i] > target){
                return;
            }
            path.addLast(candidates[i]);
            currentSum += candidates[i];
            backtrace(candidates, i, target); // 注意这里是i还是target。如果是i，那就是前面选过的数不能再选了。如果是target，则全面的数可以继续选，会出现重复结果。
            path.removeLast();
            currentSum -= candidates[i];
        }
    }

}
