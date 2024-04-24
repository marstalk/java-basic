package treenode;

/**
 * You are given the root of a binary search tree (BST), where the values of
 * exactly two nodes of the tree were swapped by mistake. Recover the tree
 * without changing its structure.
 */
public class RecoverTree {
    public static void main(String[] args) {
        RecoverTree recoverTree = new RecoverTree();

        System.out.println("-----");
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 1, 3, -1, -1, 2  });
        TreeNode.printInorder(root);
        recoverTree.recoverTree(root);
        TreeNode.printInorder(root);

        System.out.println("-----");
        root = TreeNode.toTreeWithBFSArray(new int[] { 3, 1, 4, -1, -1, 2});
        TreeNode.printInorder(root);
        recoverTree.recoverTree(root);
        TreeNode.printInorder(root);
    }

    private TreeNode n1, n2, pre;

    public void recoverTree(TreeNode root) {
        n1=null;
        n2=null;
        pre=null;
        inorder(root);
        int i = n1.val;
        n1.val = n2.val;
        n2.val = i;
    }

    /**
     * 
     * @param cur
     */
    private void inorder(TreeNode cur) {
        if (cur == null) {
            return;
        }

        inorder(cur.left);

        if (pre != null && pre.val > cur.val) { // 有pre（即第二个元素）才开始比较，n1始终使用pre, n2始终使用cur
            // pre.val > cur.val 违法了单调递增的性质。所以，pre或者cur肯定是有问题的。
            if (n1 == null) {
                // 因为n1==null，所以，pre肯定有问题。
                n1 = pre;
            }
            // n2也在不断在调整，直到遍历到最后一个元素。
            n2 = cur;
        }
        // pre 不断往前走。
        pre = cur;

        inorder(cur.right);
    }
}
