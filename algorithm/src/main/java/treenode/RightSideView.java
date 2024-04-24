package treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, 
 * imagine yourself standing on the right side of it, 
 * return the values of nodes you can see ordered from the top to bottom.
 */
public class RightSideView {
    public static void main(String[] args) {
        RightSideView rightSideView = new RightSideView();

        System.out.println("----method1----");
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{1,2,3,-1,5,-1,4});
        List<Integer> res = rightSideView.rightSideView(root);
        res.stream().forEach(System.out::println);// 1,3,4

        System.out.println("----method2-----");
        root = TreeNode.toTreeWithBFSArray(new int[]{1,2,3,-1,5,-1,4});
        res = rightSideView.rightSideView2(root);
        res.stream().forEach(System.out::println);// 1,3,4

        
        TreeNode node2 = TreeNode.toTreeWithBFSArray(new int[]{1,2,3,4});
        List<Integer> res2 = rightSideView.rightSideView2(node2);
        res2.stream().forEach(System.out::println);// 1,3,4

        System.out.println("----method3----");
        List<Integer> res3 = rightSideView.rightSideView3(root);
        res3.stream().forEach(System.out::println);// 1,3,4

        
        List<Integer> res4 = rightSideView.rightSideView3(node2);
        res4.stream().forEach(System.out::println);// 1,3,4
    }

    /**
     * paas, with double while loop, 
     * @param root
     * @return
     */
    private List<Integer> rightSideView3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;
        while(!queue.isEmpty()){
            int cnt = queue.size();
            while(cnt > 0){
                TreeNode node = queue.poll();
                i = node.val;
                cnt--;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(i);
        }
        return res;
    }

    /**
     * paas, with two queue, we can easily find the last element of each level.
     * @param root
     * @return
     */
    private List<Integer> rightSideView2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(root);
        while(!q1.isEmpty()){
            TreeNode node = q1.poll();
            
            if(node.left != null){
                q2.offer(node.left);
            }
            if(node.right != null){
                q2.offer(node.right);
            }
            if(q1.isEmpty()){
                System.out.println(node.val);
                Queue<TreeNode> tmp = q1;
                q1 = q2;
                q2 = tmp;
            }
        }
        return res;
    }
    
    /**
     * bad! this algorithm can not cover all the cases, failed
     * @param root
     * @return
     */
    private List<Integer> rightSideView(TreeNode root){
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }

    private void traverse(TreeNode node, List<Integer> res){
        if(node == null){
            return;
        }
        res.add(node.val);
        traverse(node.right, res);
        return ;
    }
}
