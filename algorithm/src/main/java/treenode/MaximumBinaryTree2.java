package treenode;

/**
 * A maximum tree is a tree where every node has a value greater than any other
 * value in its subtree.
 * 
 * You are given the root of a maximum binary tree and an integer val.
 * 
 * Just as in the previous problem, the given tree was constructed from a list a
 * (root = Construct(a)) recursively with the following Construct(a) routine:
 * 
 * If a is empty, return null.
 * Otherwise, let a[i] be the largest element of a. Create a root node with the
 * value a[i].
 * The left child of root will be Construct([a[0], a[1], ..., a[i - 1]]).
 * The right child of root will be Construct([a[i + 1], a[i + 2], ...,
 * a[a.length - 1]]).
 * Return root.
 * Note that we were not given a directly, only a root node root = Construct(a).
 * 
 * Suppose b is a copy of a with the value val appended to it. It is guaranteed
 * that b has unique values.
 * 
 * Return Construct(b).
 */
public class MaximumBinaryTree2 {
    public static void main(String[] args) {
        MaximumBinaryTree2 maximumBinaryTree2 = new MaximumBinaryTree2();

        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 4, 1, 3, -1, -1, 2 });
        TreeNode.printBFS(root);
        TreeNode res = maximumBinaryTree2.insertIntoMaxTree(root, 5);
        TreeNode.printBFS(res); // [5,4,null,1,3,null,null,2]

        System.out.println("-----");
        root = TreeNode.toTreeWithBFSArray(new int[] {5,2,4,-1,1 });
        TreeNode.printBFS(root);
        res = maximumBinaryTree2.insertIntoMaxTree(root, 3);
        TreeNode.printBFS(res); // [5,2,4,null,1,null,3]
    }

    /**
     * if val is greater than root.val, then val will be new root, and old root will
     * the left child.</br>
     * 
     * if val is less than root.val, the val will insert into right sub tree.
     * 
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val > root.val) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        } else {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }
    }
}
