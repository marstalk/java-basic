package treenode;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/discussion/
 * A path in a binary tree is a sequence of nodes where each pair of adjacent
 * nodes in the sequence has an edge connecting them. A node can only appear in
 * the sequence at most once. Note that the path does not need to pass through
 * the root.
 * 
 * The path sum of a path is the sum of the node's values in the path.
 * 
 * Given the root of a binary tree, return the maximum path sum of any non-empty
 * path.
 * 
 * 
 */
public class MaxPathSum {
    public static void main(String[] args) {
        MaxPathSum maxPathSum = new MaxPathSum();
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { -10, 9, 20, -1, -1, 15, 7 });
        System.out.println(maxPathSum.maxPathSum(root));// 42

        root = TreeNode.toTreeWithBFSArray(new int[] { 1, 2, 3 });
        System.out.println(maxPathSum.maxPathSum(root));// 6

        root = TreeNode.toTreeWithBFSArray(new int[] { -3 });
        System.out.println(maxPathSum.maxPathSum(root));// 6
    }

    private int max2;

    public int maxPathSum(TreeNode root) {
        max2 = Integer.MIN_VALUE;
        int max = dfs(root);
        return Math.max(max, max2);
    }

    /**
     * 第一种类型：
     * - root, left
     * - root, right
     * - root
     * </p>
     * 第二种类型：
     * -root, left, right
     * - left
     * - right
     * 
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int leftMax = dfs(root.left);
        int rightMax = dfs(root.right);

        int max = Integer.MIN_VALUE;
        max = Math.max(max, root.val + leftMax);
        max = Math.max(max, root.val + rightMax);
        max = Math.max(max, root.val);

        max2 = Math.max(max2, root.val + leftMax + rightMax);

        // 一定要注意判断leftMax != 0，不然无法计算[-3]这种情况
        if(leftMax !=0) max2 = Math.max(max2, leftMax);
        if(rightMax !=0) max2 = Math.max(max2, rightMax);

        return max;
    }
}
