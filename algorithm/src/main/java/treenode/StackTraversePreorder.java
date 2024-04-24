package treenode;

import java.util.Stack;

/**
 * 使用stack完成二叉树的前序、中序、后序遍历。
 */
public class StackTraversePreorder {
    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{1,2,3,4,5,6,7});
        
        StackTraversePreorder stackTraverse = new StackTraversePreorder();
        stackTraverse.preorder(root); // 1,2,4,5,3,6,7
    }

    /**
     * 前序遍历。
     * @param root
     */
    public void preorder(TreeNode root){
        if(root == null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 根节点入栈，只压一个就可以开始处理了。
        while(!stack.isEmpty()){ // 开始遍历
            TreeNode cur = stack.pop(); 
            System.out.print(cur.val + " ");
            if(cur.right != null) stack.push(cur.right);// 注意，这里先放右，再放左，那么打印的顺序就是先左再右。
            if(cur.left != null) stack.push(cur.left);
        }
        System.out.println();
    }
}
