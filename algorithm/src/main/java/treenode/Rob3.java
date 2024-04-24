package treenode;

import java.util.LinkedList;

/**
 * The thief has found himself a new place for his thievery again. There is
 * only one entrance to this area, called root.
 * 
 * Besides the root, each house has one and only one parent house. After a
 * tour, the smart thief realized that all houses in this place form a binary
 * tree. It will automatically contact the police if two directly-linked houses
 * were broken into on the same night.
 * 
 * Given the root of the binary tree, return the maximum amount of money the
 * thief can rob without alerting the police.
 * 
 * 
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 10^4
 * 
 * 
 * 1. root
 * 2. left + right
 */
public class Rob3 {
    public static void main(String[] args) {
        Rob3 rob = new Rob3();

        System.out.println("---rob----");
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 3, 2, 3, -1, 3, -1, 1 });
        System.out.println(rob.rob(root)); // 7

        root = TreeNode.toTreeWithBFSArray(new int[] { 3, 4, 5, 1, 3, -1, 1 });
        System.out.println(rob.rob(root)); // 9

        root = TreeNode.toTreeWithBFSArray(new int[] { 4, 1, -1, 2, -1, 3 });
        System.out.println(rob.rob(root)); // 7

        System.out.println("----rob2----");
        root = TreeNode.toTreeWithBFSArray(new int[] { 3, 2, 3, -1, 3, -1, 1 });
        System.out.println(rob.rob2(root)); // 7

        root = TreeNode.toTreeWithBFSArray(new int[] { 3, 4, 5, 1, 3, -1, 1 });
        System.out.println(rob.rob2(root)); // 9

        root = TreeNode.toTreeWithBFSArray(new int[] { 4, 1, -1, 2, -1, 3 });
        System.out.println(rob.rob2(root)); // 7
    }

    private int maxSum;
    /**
     * 尝试使用回溯的方式。
     * 
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        backtrace(root, new LinkedList<>(), false);
        return maxSum;
    }
    private void backtrace(TreeNode node, LinkedList<Integer> path, boolean preSelected) {
        if (node == null) {
            int tmpSum = 0;
            for (int i : path) {
                tmpSum += i;
            }
            maxSum = Math.max(maxSum, tmpSum);
            return;
        }

        if(!preSelected) {
            path.addLast(node.val);
            backtrace(node.left, path, true);
            backtrace(node.right, path, true);
            path.removeLast();
        }

        backtrace(node.left, path, false);
        backtrace(node.right, path, false);
    }

    /**
     * failed，
     * 
     * 不相连，那就隔层偷。可能隔一层或者隔多层。这个方法中都是隔一层来取，显然没有覆盖隔多层的情况。
     * 
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        return Math.max(doRob(root, true), doRob(root, false));
    }

    private int doRob(TreeNode root, boolean robCurrent) {
        if (root == null)
            return 0;

        int left = doRob(root.left, !robCurrent);
        int right = doRob(root.right, !robCurrent);

        if (robCurrent) {
            return root.val + left + right;
        } else {
            return left + right;
        }
    }
}
