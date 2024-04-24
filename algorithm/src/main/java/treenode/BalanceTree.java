package treenode;

/**
 * Given a binary tree, determine if it is height-balanced
 * 
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class BalanceTree {


    public boolean isBalanced2(TreeNode root){
        dfs(root);
        return res;
    }
    boolean res = true;
    private int dfs(TreeNode root){
        if(root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);

        if(left - right > 1 || right -left > 1){
            res = false;
        }
        return Math.max(left, right) + 1;
    }



    public boolean isBalanced(TreeNode root){
        if(root == null) return true;
        if(Math.abs(depth(root.left) - depth(root.right)) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    private int depth(TreeNode root){
        if(root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
