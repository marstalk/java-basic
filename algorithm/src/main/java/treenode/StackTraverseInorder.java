package treenode;

import java.util.Stack;

/**
 * 使用stack完成二叉树的中序遍历。
 */
public class StackTraverseInorder {
    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{1,2,3,4,5,6,7});
        
        StackTraverseInorder stackTraverse = new StackTraverseInorder();
        stackTraverse.inorder(root); // 4,2,5,1,6,3,7
    }

    /**
     * 中序遍历
     * 
     * 如果有左子树，那么全部先压入栈顶，压完左子树之后再压右子树。
     * @param root
     */
    public void inorder(TreeNode root){
        if(root == null) return;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            
            while(cur != null){ // 有可能为null，判空。
                stack.push(cur); // 入栈模拟的是【方法调用开始】
                cur = cur.left; // 将当前子树的左节点全部压入栈中。跟递归一样，要将左子节点全部压入栈中再处理。
            }

            if(!stack.isEmpty()){
                cur = stack.pop(); // 出栈模拟的是【方法结束调用】，想想递归的方式，是travers(node.left)调用结束之后再print(cur)
                System.out.print(cur.val + " "); // 中序
                cur = cur.right; // 有可能null，用同样的方式处理右子树。
            }
        }
        System.out.println();
    }
}
