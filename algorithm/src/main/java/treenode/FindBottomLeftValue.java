package treenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the leftmost value in the last row of
 * the tree.
 */
public class FindBottomLeftValue {
    public static void main(String[] args) {

        FindBottomLeftValue findBottomLeftValue = new FindBottomLeftValue();

        System.out.println("---DFS---");
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 1, 2, 3, 4, -1, 5, 6, -1, -1, 7 });
        int res = findBottomLeftValue.findBottomLeftValue(root);
        System.out.println(res);

        System.out.println("---DFS---");
        root = TreeNode.toTreeWithBFSArray(new int[] {2,1,3 });
        res = findBottomLeftValue.findBottomLeftValue(root);
        System.out.println(res);

        System.out.println("---BFS---");
        root = TreeNode.toTreeWithBFSArray(new int[] { 1, 2, 3, 4, -1, 5, 6, -1, -1, 7 });
        res = findBottomLeftValue.findBottomLeftValue2(root);
        System.out.println(res);

        root = TreeNode.toTreeWithBFSArray(new int[] {2,1,3 });
        res = findBottomLeftValue.findBottomLeftValue2(root);
        System.out.println(res);
    }

    /**
     * 使用层序遍历，先右子树，再左子树，最后一个元素就是结果。牛逼！！
     * @param root
     * @return
     */
    public int findBottomLeftValue2(TreeNode root){
        if(root == null){
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            if(node.right != null){
                queue.offer(node.right);
            }
            if(node.left != null){
                queue.offer(node.left);
            }
        }
        return node.val;
    }




    /**
     * 这个方法有点复杂。
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root){
        Res res = doFind(root, 1);
        return res.val;
    }

    private Res doFind(TreeNode root, int depth) {
        if (root == null)
            return null;

        Res leftVal = doFind(root.left, depth + 1);
        Res rightVal = doFind(root.right, depth + 1);


        if(leftVal == null && rightVal == null)
            return new Res(root.val, depth);
        if (leftVal == null)
            return rightVal;
        if (rightVal == null)
            return leftVal;
        if (leftVal.depth >= rightVal.depth)
            return leftVal;

        return rightVal;
    }

    class Res {
        public int val;
        public int depth;
        public Res(int val, int depth){
            this.val = val;
            this.depth = depth;
        }
    }
}
