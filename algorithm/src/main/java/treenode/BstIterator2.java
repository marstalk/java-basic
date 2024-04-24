package treenode;

import java.util.Stack;

/**
 * 优化BstIterator的空间复杂度，从O(N)降到O(h),h是数的高度。使用栈结构。
 * next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存,
 * 
 * 这个跟
 */
public class BstIterator2 {
    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 7, 3, 15, -1, -1, 9, 20 });
        BstIterator2 iterator = new BstIterator2(root);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // 3, 7, 9, 15, 20
        }
    }

    private Stack<TreeNode> stack;

    /*
     * stack中保存了h个元素。空间复杂度，从O(N)降到O(h)
     */
    public BstIterator2(TreeNode root) {
        stack = new Stack<>();
        pushStack(root);
    }

    private void pushStack(TreeNode root) {
        if (root == null)
            return;
        stack.push(root);
        pushStack(root.left);
    }

    /*
     * 
     */
    public int next() {
        TreeNode node = stack.pop();
        pushStack(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}