package treenode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 * 
 * A leaf is a node with no children.
 */
public class BinaryTreePath {
    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 1, 2, 3, -1, 5 });
        BinaryTreePath binaryTreePath = new BinaryTreePath();
        for (String str : binaryTreePath.binaryTreePath(root)) {
            System.out.println(str);
        }
    }

    public List<String> binaryTreePath(TreeNode root) {
        LinkedList<String> res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs(root, path, res);
        return res;
    }

    private void dfs(TreeNode root, LinkedList<Integer> path, LinkedList<String> res) {
        if (root == null)
            return;

        path.addLast(root.val);
        if (root.left == null && root.right == null) {
            res.add(toString(path));
        }

        dfs(root.left, path, res);
        dfs(root.right, path, res);

        path.removeLast();
    }

    private String toString(LinkedList<Integer> path) {
        if (path == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i : path) {
            sb.append(i).append("->");
        }
        sb.delete(sb.lastIndexOf("-"), sb.length());
        return sb.toString();
    }
}
