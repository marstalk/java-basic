package treenode;

/**
 * You are given an integer array nums with no duplicates. A maximum binary tree
 * can be built recursively from nums using the following algorithm:
 * 
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the
 * maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of
 * the maximum value.
 * Return the maximum binary tree built from nums.
 */
public class MaximumBinaryTree {
    public static void main(String[] args) {
        MaximumBinaryTree maximumBinaryTree = new MaximumBinaryTree();
        TreeNode root = maximumBinaryTree.constructMaixmumBinaryTree(new int[]{3,2,1,6,0,5});
        TreeNode.printBFS(root);
    }

    public TreeNode constructMaixmumBinaryTree(int[] nums){
        return build(nums, 0, nums.length);
    }

    /**
     * 
     * @param nums
     * @param start 左闭
     * @param end 右开
     * @return
     */
    private TreeNode build(int[] nums, int start, int end){
        if (start >= end){
            return null;
        }
        int maxVal = Integer.MIN_VALUE;
        int j = -1;
        for(int i = start; i < end; i ++){
            if(nums[i] > maxVal){
                maxVal = nums[i];
                j = i;
            }
        }

        TreeNode node = new TreeNode(maxVal);
        node.left = build(nums, start, j);
        node.right = build(nums, j + 1, end);

        return node;
    }
}
