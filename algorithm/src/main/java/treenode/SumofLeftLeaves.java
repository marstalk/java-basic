package treenode;

public class SumofLeftLeaves {
    public static void main(String[] args) {
        SumofLeftLeaves sum = new SumofLeftLeaves();
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 3, 9, 20, -1, -1, 15, 7 });
        System.out.println(sum.sumOfLeftLeaves(root)); // 24

        System.out.println("----");
        System.out.println(sum.sumOfLeftLeaves2(root)); // 24
    }


    /**
     * 自下而上。
     */
    public int sumOfLeftLeaves2(TreeNode root){
        return buttomsUp(root, false);
    }

    private int buttomsUp(TreeNode root, boolean isLeft){
        if(root == null) return 0;
        int left = buttomsUp(root.left, true);
        int right = buttomsUp(root.right, false);

        if(isLeft && root.left == null && root.right == null){
            return root.val;
        }

        return left + right;
    }


    int sum = 0;
    /**
     * 自上而下。
     */
    public int sumOfLeftLeaves(TreeNode root) {
        sum = 0;
        traverse(root.left, true);
        traverse(root.right, false);
        return sum;
    }

    private void traverse(TreeNode root, boolean cal) {
        if (root == null)
            return;

        if (cal && root.left == null && root.right == null)
            sum += root.val;

        traverse(root.left, true);
        traverse(root.right, false);
    }
}
