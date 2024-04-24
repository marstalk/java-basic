package bfs;

import java.util.LinkedList;
import java.util.Queue;

import treenode.TreeNode;

public class MinDepth {

    public static void main(String[] args) {
        MinDepth minDepth = new MinDepth();
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 3, 9, 20, -1, -1, 15, 7 });
        System.out.println(minDepth.minDepth(root));
    }

    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;
        while (!queue.isEmpty()) {
            // 注意📢📢区别：for(int i = 0; i < queue.size(); i++)，因为在遍历的过程中，queue的size是变化的！！！
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left() == null && node.right() == null) {
                    return level;
                }

                if (node.left() != null) {
                    queue.offer(node.left());
                }
                if (node.right() != null) {
                    queue.offer(node.right());
                }
            }
            level++;
        }
        return level;
    }
}
