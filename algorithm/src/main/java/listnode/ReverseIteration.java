package listnode;

public class ReverseIteration {
    public static void main(String[] args) {
        ReverseIteration reverse = new ReverseIteration();
        ListNode reversed = reverse.reverseByIteration(ListNode.byArray(new int[]{1,2,3,4,5,6}));
        reversed.print();
    }

    public ListNode reverseByIteration(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;

        while(cur != null){
            next = cur.next;
            cur.next = pre;
            
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
