package treenode;

/**
 * singly linked node
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode byArray(int[] arr){
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for(int i : arr){
            ListNode cur = new ListNode(i);
            p.next = cur;
            p = p.next;
        }
        return dummy.next;
    }

    public static void print(ListNode head){
        ListNode node = head;
        while(node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println("");
    }
}
