package treenode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder
 * traversal of a binary tree and postorder is the postorder traversal of the
 * same tree, construct and return the binary tree.
 * 
 */
public class BuildTree2 {
    public static void main(String[] args) {
        BuildTree2 buildTree2 = new BuildTree2();
        TreeNode root = buildTree2.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        TreeNode.printBFS(root);
    }

    /**
     * 9,3,15,20,7 </br>
     * 9,15,7,20,3 </br>
     * >> 3,9,20,null,null,15,7
     * @param inorder
     * @param postorder
     * @return
     */
    private Map<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] inorder, int[] postorder){
        if(postorder == null){
            return null;
        }
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return doBuild(inorder, 0, inorder.length -1, postorder, 0, postorder.length -1);
    }

    private TreeNode doBuild(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
        if(postStart > postEnd){
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        //0,1,2,3,4,5
        //0,1,2,3,4,5
        int x = inorderMap.get(root.val);
        int leftSize = x - inStart;
        System.out.println("leftSize: " + leftSize);

        root.left = doBuild(inorder, inStart, x -1 , postorder, postStart, postStart + leftSize - 1);
        root.right = doBuild(inorder, x +1, inEnd, postorder, postStart + leftSize, postEnd -1);

        return root;
    }


}
