package treenode;

/**
 * 
 * Given the root of a binary tree, flatten the tree into a "linked list":
    The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
    The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class Flatten {
    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{1,2,5,3,4,-1,6});
        TreeNode.printBFS(root);

        Flatten flatten = new Flatten();
        flatten.flatten(root);
        TreeNode.printBFS(root); //[1,null,2,null,3,null,4,null,5,null,6]
    }
    
    private void flatten(TreeNode root){
        if(root == null) return;

        // post-order interation, flatten left and right then flatten root.
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;
        
        TreeNode tmp = root;
        while(tmp.right != null){
            tmp = tmp.right;
        }
        tmp.right = right;
    }
}
