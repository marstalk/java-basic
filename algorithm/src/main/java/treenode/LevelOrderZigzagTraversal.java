package treenode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of
 * its nodes' values. (i.e., from left to right, then right to left for the next
 * level and alternate between).
 * </p>
 * [3,9,20,null,null,15,7]
 * >>
 * [[3],[20,9],[15,7]]
 */
public class LevelOrderZigzagTraversal {
    public static void main(String[] args) {
        LevelOrderZigzagTraversal traversal = new LevelOrderZigzagTraversal();

        System.out.println("----method1----");
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 3, 9, 20, -1, -1, 15, 7 });
        List<List<Integer>> res = traversal.zigzagLevelOrder(root);
        traversal.print(res); // [[3],[20,9],[15,7]]

        System.out.println("------");

        root = TreeNode.toTreeWithBFSArray(new int[] { 1, 2, 3, 4, -1, -1, 5 });
        res = traversal.zigzagLevelOrder(root);
        traversal.print(res); // [[1],[3,2],[4,5]]
    }

    /**
     * 使用两个双端队列。
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean fromLeft = true;

        while (!queue.isEmpty()) {
            Deque<Integer> deque = new LinkedList<>();
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                size--;

                if (fromLeft) {
                    deque.offerLast(cur.val);
                } else {
                    deque.offerFirst(cur.val);
                }

                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
            ArrayList<Integer> list = new ArrayList<>();
            while (!deque.isEmpty()) {
                list.add(deque.poll());
            }
            res.add(list);

            fromLeft = !fromLeft;//
        }
        return res;
    }

    private void print(List<List<Integer>> res) {
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
