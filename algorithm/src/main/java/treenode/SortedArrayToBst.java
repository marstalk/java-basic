package treenode;

/**
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 * </p>
 * -10,-3,0,5,9
 * >
 * 0,-3,9,-10,null,5 or 0,-10,5,null,-3,null,9
 */
public class SortedArrayToBst {

    public static void main(String[] args) {
        SortedArrayToBst sortedArrayToBst = new SortedArrayToBst();
        TreeNode root = sortedArrayToBst.sortedArrayToBST(new int[]{-10,-3,0,5,9});
        TreeNode.printBFS(root);
    }

    public TreeNode sortedArrayToBST(int[] arr){
        return doBuild(arr, 0, arr.length -1);
    }

    private TreeNode doBuild(int[] arr, int left, int right){
        if(arr == null || arr.length == 0 || left > right) return null;
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = doBuild(arr, left, mid - 1);
        root.right = doBuild(arr, mid + 1, right);
        return root;
    }
}
