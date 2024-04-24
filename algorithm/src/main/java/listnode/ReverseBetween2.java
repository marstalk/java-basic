package listnode;

public class ReverseBetween2 {
    public static void main(String[] args) {
        ReverseBetween2 reverseBetween = new ReverseBetween2();

        reverseBetween.reverseBetween(ListNode.byArray(new int[] { 1, 2, 3, 4, 5 }), 2, 4).print();

        reverseBetween.reverseBetween(ListNode.byArray(new int[] { 3, 5 }), 1, 2).print();

        reverseBetween.reverseBetween(ListNode.byArray(new int[] { 1,2,3 }), 1, 2).print();
    }

    /**
     * 方法二。
     * 
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null || left >= right) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode pre = null;

        for(int i = 0; i < left; i++){
            pre = cur;
            cur = cur.next;
        }
        //cur is left
        for(int i = left; i <= right; i++){
            ListNode next = cur.next;
            pre.next = cur;
            cur.next = next;
        }
       
        return dummy.next;
    }
}
