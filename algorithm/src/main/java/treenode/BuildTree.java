package treenode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder
 * traversal of a binary tree and inorder is the inorder traversal of the same
 * tree, construct and return the binary tree.
 */
public class BuildTree {
    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();
        TreeNode root = buildTree.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        TreeNode.printBFS(root);
    }

    /**
     * build Tree with preorder and inorder.
     * 
     * @param preorder
     * @param inorder
     * @return TreeNode
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null)
            return null;
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length -1, inorder, 0, inorder.length -1, inorderMap);
    }

    /**
     * 
     * @param preorder
     * @param preStart
     *            close
     * @param preEnd
     *            close
     * @param inorder
     * @param inStart
     * @param inEnd
     * @return
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        // 0,1,2,3,4,5
        // 0,1,2,3,4,5
        int x = inorderMap.get(root.val);
        int leftSize = x - inStart; // key point

        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, x - 1, inorderMap);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, x + 1, inEnd, inorderMap);
        return root;
    }
}
