package backtracing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组全排列，无重复、无复选。
 */
public class Permute {
    
    public static void main(String[] args) {
        Permute permute = new Permute();
        List<List<Integer>> rtn = permute.permute(new int[]{1,2,3});
        for (List<Integer> list : rtn) {
            System.out.println(list);
        }
    }

    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rtn = new ArrayList<>();
        backtrace(nums, new LinkedList<Integer>(), new boolean[nums.length], rtn);
        return rtn;
    }

    private void backtrace(int[] nums, LinkedList<Integer> path, boolean[] used, List<List<Integer>> rtn){
        // base
        if (path.size() == nums.length){
            rtn.add(new LinkedList<>(path));
        }

        // for + used[i]判断 == 遍历所有备选项，
        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }

            // 前序遍历，将选择项加入path
            used[i] = true;
            path.addLast(nums[i]);

            backtrace(nums, path, used, rtn);

            // 后序遍历，将选择项去除
            used[i] = false;
            // remove的参数是下标，应该使用removeLast
            // path.remove(nums[i]);
            path.removeLast();
        }
    }
}
