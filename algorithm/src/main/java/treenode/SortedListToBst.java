package treenode;

/**
 * Given the head of a singly linked list where elements are sorted in ascending
 * order, convert it to a height-balanced binary search tree.
 * </p>
 * -10,-3,0,5,9
 * >
 * [0,-3,9,-10,null,5] or other which represents the shown height balanced BST.
 */
public class SortedListToBst {

    public static void main(String[] args) {
        SortedListToBst sortedListToBst = new SortedListToBst();
        ListNode head = ListNode.byArray(new int[] { -10, -3, 0, 5, 9 });
        ListNode.print(head);
        TreeNode root = sortedListToBst.sortedListToBST(head);
        TreeNode.printBFS(root);
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);

        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = head;
        while (fast != null && fast.next != null) { // 既然fast可能等于null，那就需要判断。
            fast = fast.next.next; // fast == null也是没有问题的，关键是slow为中间节点。
            pre = slow;
            slow = slow.next;
        }
        pre.next = null; // 关键在断链。

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
 


}
