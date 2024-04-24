package treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the bottom-up level order traversal
 * of its nodes' values. (i.e., from left to right, level by level from leaf to
 * root).
 */
public class LevelOrderTraversal2 {
    public static void main(String[] args) {
        LevelOrderTraversal2 traversal = new LevelOrderTraversal2();

        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 3, 9, 20, -1, -1, 15, 7 });
        List<List<Integer>> res = traversal.levelOrderBottom(root);
        traversal.print(res);// [[15,7],[9,20],[3]]
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode n = queue.poll();
                size--;
                list.add(n.val);

                if (n.left != null)
                    queue.offer(n.left);
                if (n.right != null)
                    queue.offer(n.right);
            }
            res.addFirst(list);
        }

        return res;
    }

    private void print(List<List<Integer>> res) {
        for(List<Integer> list : res){
            for(int i: list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
