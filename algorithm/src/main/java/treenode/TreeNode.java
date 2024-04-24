package treenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 将层序遍历数组转为树结构，比如[3,9,20,-1,-1,15,7]，其中-1为null
     * 1,-1,0,0,1 > failed
     * @param nums
     * @return
     */
    public static TreeNode toTreeWithBFSArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nums[0]);
        int i = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (true) {
            TreeNode node = q.poll();
            if (i + 1 < nums.length) {
                i++;
                if(nums[i] != -1){
                    node.left = new TreeNode(nums[i]);
                    q.offer(node.left);
                }

                if (i + 1 < nums.length) {
                    i++;
                    if(nums[i] != -1){
                        node.right =  new TreeNode(nums[i]);
                        q.offer(node.right);
                    }
                    
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return root;
    }

    /**
     * 层序打印二叉树
     * @param root
     */
    public static void printBFS(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print("bfs: ");
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node == null){
                System.out.print("null" + ",");
                continue;
            }else{
                System.out.print(node.val + " ");
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        System.out.println();
    }

    public static void printInorder(TreeNode root){
        System.out.print("inorder: ");
        doPrintInorder(root);
        System.out.println("");
    }
    private static void doPrintInorder(TreeNode root){
        if(root == null){
            return;
        }

        doPrintInorder(root.left);
        System.out.print(root.val + ",");
        doPrintInorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 3, 9, 20, -1, -1, 15, 7 });
        TreeNode.printBFS(root);
    }

    public TreeNode left(){
        return left;
    }
    public TreeNode right(){
        return right;
    }
}
