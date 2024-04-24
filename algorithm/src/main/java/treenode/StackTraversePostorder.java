package treenode;

import java.util.Stack;

public class StackTraversePostorder {
    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{1,2,3,4,5,6,7});
        
        StackTraversePostorder stackTraverse = new StackTraversePostorder();

        stackTraverse.postorder(root); // 4,5,2,6,7,3,1
        System.out.println();
    }

    public void postorder(TreeNode root){
        if(root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Stack<TreeNode> reverse = new Stack<>();
        TreeNode cur;
        while(!stack.isEmpty()){
            cur = stack.pop();
            reverse.push(cur);
            if(cur.left != null) stack.push(cur.left);
            if(cur.right != null) stack.push(cur.right);
        }

        while(!reverse.isEmpty()){
            cur = reverse.pop();
            System.out.print(cur.val + " ");
        }
    }
}
