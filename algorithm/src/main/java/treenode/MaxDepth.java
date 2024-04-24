package treenode;

/**
 * Easy
 * Given the root of a binary tree, return its maximum depth.
 * </p>
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 * 
 */
public class MaxDepth {
    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();
        System.out.println("-----");
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{3,9,20,-1,-1,15,7});
        System.out.println(maxDepth.maxDepth(root));
    }
    
    public int maxDepth(TreeNode root){
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
