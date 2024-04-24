package listnode;

public class RemoveNthFromEnd {
    public static void main(String[] args) {
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();

        ListNode res = removeNthFromEnd.removeNthFromEnd(ListNode.byArray(new int[]{1,2,3,4,5,6}), 3);
        res.print();

        res = removeNthFromEnd.removeNthFromEnd(ListNode.byArray(new int[]{1}), 1);
        if(res != null) res.print();
    }
    
    public ListNode removeNthFromEnd(ListNode head, int k){
        if(head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = findNthFromEnd(dummy, k + 1);
        if(pre == null) return null;
        pre.next = pre.next.next;
        return dummy.next;
    }

    private ListNode findNthFromEnd(ListNode head, int k){
        int i = 0;
        ListNode fast = head;
        for(; i < k && fast != null; i++){
            fast = fast.next;
        }
        if(i < k) return null;

        ListNode slow = head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
