package treenode;

public class PathSum {
    public static void main(String[] args) {
        PathSum pathSum = new PathSum();

        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, -1, 1 });
        System.out.println(pathSum.pathSum(root, 22)); // true

        root = TreeNode.toTreeWithBFSArray(new int[] { 1, 2, 3 });
        System.out.println(pathSum.pathSum(root, 5));// false
    }

    /**
     * 自上而下的递减，可以通过参数来传递不断变化的targetSum。使用前序遍历
     * @param root
     * @param targetSum
     * @return
     */
    public boolean pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null)
            return true;
        return pathSum(root.left, targetSum) || pathSum(root.right, targetSum);
    }
}
