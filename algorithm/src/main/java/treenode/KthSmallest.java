package treenode;

/**
 * Given the root of a binary search tree, and an integer k, return the kth
 * smallest value (1-indexed) of all the values of the nodes in the tree.
 * 
 * Follow up: If the BST is modified often (i.e., we can do insert and delete
 * operations) and you need to find the kth smallest frequently, how would you
 * optimize?
 */
public class KthSmallest {
    public static void main(String[] args) {
        KthSmallest smallest = new KthSmallest();

        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 5, 3, 6, 2, 4, -1, -1, 1 });
        System.out.println(smallest.kthSmallest(root, 3)); // 3

    }

    int res = 0;
    int cnt = 0;

    /**
     * 前序遍历当然是顺其自然的办法，但是它的时间复杂度是O(N)。logN的算法？
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        res = 0;
        dfs(root, k);
        return res;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null)
            return;
        dfs(root.left, k);

        if (++cnt == k) {
            res = root.val;
            return;
        }
        dfs(root.right, k);
    }
}
