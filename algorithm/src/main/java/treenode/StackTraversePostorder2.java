package treenode;

import java.util.LinkedList;
import java.util.Stack;

public class StackTraversePostorder2 {
    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{1,2,3,4,5,6,7});
        
        StackTraversePostorder2 stackTraverse = new StackTraversePostorder2();

        stackTraverse.postorder(root); // 4,5,2,6,7,3,1
        System.out.println();
    }

    /**
     * 双端队列实现的方式
     * @param root
     */
    public void postorder(TreeNode root){
        if(root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        LinkedList<TreeNode> reverse = new LinkedList<>();

        TreeNode cur;
        while(!stack.isEmpty()){
            cur = stack.pop(); // 根-右-左
            reverse.addFirst(cur); // 借助双端队列deque完成倒排即 左-右-根。

            if(cur.left != null) stack.push(cur.left);
            if(cur.right != null) stack.push(cur.right);
        }

        for(TreeNode node: reverse){
            System.out.print(node.val + " ");
        }

        System.out.println();
        
    }
}
