package treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * 
 * Each root-to-leaf path in the tree represents a number.
 * 
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so
 * that the answer will fit in a 32-bit integer.
 * 
 * A leaf node is a node with no children.
 */
public class SumNumbers {
    public static void main(String[] args) {
        SumNumbers sumNumbers = new SumNumbers();

        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 4, 9, 0, 5, 1 });
        System.out.println(sumNumbers.sumNumbers(root)); // 1026

        root = TreeNode.toTreeWithBFSArray(new int[] { 4, 9, 0, 5, 1 });
        System.out.println(sumNumbers.sumNumbers2(root)); // 1026
    }

    private int sum;
    /**
     * performance is great.
     * @param root
     * @return
     */
    public int sumNumbers2(TreeNode root){
        dfs2(root, 0);
        return sum;
    }
    private void dfs2(TreeNode root, int curSum){
        if(root == null) return;

        curSum = curSum * 10 + root.val;
        if(root.left == null && root.right == null){
            sum += curSum;
        }
        dfs2(root.left, curSum);
        dfs2(root.right, curSum);
    }


    private List<List<Integer>> res;
    /**
     * method-1，performance is bad
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs(root, path);
        int sum = 0;
        for (List<Integer> list : res) {
            StringBuilder sb = new StringBuilder();
            for (int i : list) {
                sb.append(i);
            }
            sum += Integer.valueOf(sb.toString());
        }
        return sum;
    }

    /**
     * dfs
     * @param root
     * @param path
     */
    private void dfs(TreeNode root, LinkedList<Integer> path) {
        if (root == null)
            return;

        path.addLast(root.val);
        if (root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }
        dfs(root.left, path);
        dfs(root.right, path);

        path.removeLast();
    }
}
