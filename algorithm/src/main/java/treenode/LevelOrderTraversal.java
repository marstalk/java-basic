package treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the level order traversal of its
 * nodes' values. (i.e., from left to right, level by level).
 * 
 * [3,9,20,null,null,15,7]
 * >>
 * [[3],[9,20],[15,7]]
 */
public class LevelOrderTraversal {
    public static void main(String[] args) {
        LevelOrderTraversal traversal = new LevelOrderTraversal();

        System.out.println("----method1----");
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 3, 9, 20, -1, -1, 15, 7 });
        List<List<Integer>> res = traversal.levelOrder(root);
        traversal.print(res);

        System.out.println("----method2----");
        root = TreeNode.toTreeWithBFSArray(new int[] { 3, 9, 20, -1, -1, 15, 7 });
        res = traversal.levelOrder2(root);
        traversal.print(res);
    }
    

    /**
     * method 2, dfs，这个performance要好一些。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root){
        dfs(root, 0);
        return res;
    }
    List<List<Integer>> res = new ArrayList<>();
    private void dfs(TreeNode root, int idx){
        if(root == null) return;

        if(res.size() < idx + 1){ // 不能使用size.get(idex) == null
            res.add(new ArrayList<>());
        }

        res.get(idx).add(root.val);
        dfs(root.left, idx + 1);
        dfs(root.right, idx + 1);
    }

    /**
     * method 1, level-order
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode t = queue.poll();
                size--;
                list.add(t.val);
                if (t.left != null)
                    queue.offer(t.left);
                if (t.right != null)
                    queue.offer(t.right);
            }
            res.add(list);
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
