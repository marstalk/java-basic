package treenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary search tree, and an integer k, return the kth
 * smallest value (1-indexed) of all the values of the nodes in the tree.
 * 
 * Follow up: If the BST is modified often (i.e., we can do insert and delete
 * operations) and you need to find the kth smallest frequently, how would you
 * optimize?
 */
public class KthSmallestOptimize {
    public static void main(String[] args) {
        KthSmallestOptimize smallest = new KthSmallestOptimize();

        NTreeNode root = NTreeNode.toTreeWithBFSArray(new int[] { 5, 3, 6, 2, 4, -1, -1, 1 });

        System.out.println(root.toString());

    }


    public int kthSmallest(NTreeNode root, int k) {
        return 0;
    }

    private void dfs(NTreeNode root, int k) {

    }

    static class NTreeNode{
        int value;
        // size记录以当前节点为根节点的树的节点个数。那么比value小的值
        int size;
        NTreeNode left;
        NTreeNode right;
        public NTreeNode(int value){
            this.value = value;
            this.size = 1;
        }

        public static NTreeNode toTreeWithBFSArray(int[] nums){
            if(nums == null || nums.length == 0) return null;
            NTreeNode root = new NTreeNode(nums[0]);

            Queue<NTreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int i = 1;
            int value;
            while(true){
                NTreeNode current = queue.poll();
                if (current == null) break;

                if(i >= nums.length) break;
                if( (value = nums[i++]) != -1){
                    current.left = new NTreeNode(value);
                    current.size++;
                    queue.offer(current.left);
                }

                if(i >= nums.length) break;
                if((value = nums[i++]) != -1){
                    current.right = new NTreeNode(value);
                    queue.offer(current.right);
                }
            }

            return root;
        }

        @Override
        public String toString() {
            //in-order print
            StringBuilder sb = new StringBuilder();
            traverse(this, sb);
            return sb.toString();
        }

        private void traverse(NTreeNode root, StringBuilder sb){
            if(root == null) return;
            traverse(root.left, sb);
            sb.append(" ").append(root.value).append("(").append(root.size).append(")");
            traverse(root.right, sb);
        }

        public void append(int value){
            
        }
    }
}
