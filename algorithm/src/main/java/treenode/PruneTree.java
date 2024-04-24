package treenode;

/**
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 * 
 * 备注：节点 node 的子树为 node 本身，以及所有 node 的后代。
 */
public class PruneTree {
    public static void main(String[] args) {
        PruneTree pruneTree = new PruneTree();
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 1, -1, 0, 0, 1 });
        TreeNode.printBFS(root);
        TreeNode res = pruneTree.pruneTree(root);
        TreeNode.printBFS(res); // 1,null,0,null,1

        System.out.println("-----");
        root = TreeNode.toTreeWithBFSArray(new int[] { 1, 0, 1, 0, 0, 0, 1 });
        TreeNode.printBFS(root);
        res = pruneTree.pruneTree(root);
        TreeNode.printBFS(res); // 1,null,1,null,1
    }

    public TreeNode pruneTree(TreeNode node) {
        if (node == null)
            return null;

        node.left = pruneTree(node.left);
        node.right = pruneTree(node.right);

        if (node.val == 0 && node.left == null && node.right == null)
            return null;

        return node;
    }
}
