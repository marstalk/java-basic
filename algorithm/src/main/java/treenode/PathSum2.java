package treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 是回溯而不是普通二叉树遍历。
 */
public class PathSum2{
    public static void main(String[] args) {
        PathSum2 pathSum = new PathSum2();
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, 5, 1 });

        List<List<Integer>> res = pathSum.pathSum(root, 22); //[[5,4,11,2],[5,8,4,5]]
        Print.print(res);
    }

    LinkedList<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        LinkedList<Integer> path = new LinkedList<>();
        backtrace(root, targetSum, path);
        return res;
    }

    private void backtrace(TreeNode root, int targetSum, LinkedList<Integer> path) {
        if (root == null)
            return;

        path.addLast(root.val);

        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
            // 不能return，因为还需要执行path.removeLast
        }

        backtrace(root.left, targetSum, path);
        backtrace(root.right, targetSum, path);

        path.removeLast();
    }
}
