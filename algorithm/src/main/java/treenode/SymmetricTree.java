package treenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 */
public class SymmetricTree {
    public static void main(String[] args) {
        SymmetricTree symmetricTree = new SymmetricTree();

        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{1,2,2,3,4,4,3});
        System.out.println(symmetricTree.isSymmetric(root));;//true

        System.out.println("-----");
        root = TreeNode.toTreeWithBFSArray(new int[]{1,2,2,-1,3,-1,3});
        System.out.println(symmetricTree.isSymmetric(root));//false

        System.out.println("-----");
        root = TreeNode.toTreeWithBFSArray(new int[]{1,2,2,2,-1,2});
        System.out.println(symmetricTree.isSymmetric(root));//false

        System.out.println("----method2----");
        root = TreeNode.toTreeWithBFSArray(new int[]{1,2,2,3,4,4,3});
        System.out.println(symmetricTree.isSymmetric2(root));;//true

        System.out.println("-----");
        root = TreeNode.toTreeWithBFSArray(new int[]{1,2,2,-1,3,-1,3});
        System.out.println(symmetricTree.isSymmetric2(root));//false

        System.out.println("-----");
        root = TreeNode.toTreeWithBFSArray(new int[]{1,2,2,2,-1,2});
        System.out.println(symmetricTree.isSymmetric2(root));//false
    }

    public boolean isSymmetric2(TreeNode root){
        if(root == null) return true;
        return isSy(root.left, root.right);
    }
    private boolean isSy(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        return (left.val == right.val) && isSy(left.left, right.right) && isSy(left.right, right.left);
    }

    /**
     * pass, but performance is not that good.
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root){
        if(root == null) return true;
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        traverse(root.left, left, true);
        traverse(root.right, right, false);
        return left.toString().equals(right.toString());
    }

    private void traverse(TreeNode node, StringBuilder sb, boolean leftFirst){
        if(node == null){
            sb.append("null").append(",");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            TreeNode p = queue.poll();
            if(p == null){
                sb.append("null").append(",");
            }else{
                sb.append(p.val).append(",");
                if(leftFirst){
                    queue.offer(p.left);
                    queue.offer(p.right);
                }else{
                    queue.offer(p.right);
                    queue.offer(p.left);
                }
            }
        }
    }
}
