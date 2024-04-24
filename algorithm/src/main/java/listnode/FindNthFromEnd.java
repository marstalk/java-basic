package listnode;

public class FindNthFromEnd {
    public static void main(String[] args) {
        FindNthFromEnd findNthFromEnd = new FindNthFromEnd();

        ListNode res = findNthFromEnd.findNthFromEnd(ListNode.byArray(new int[]{1,2,3,4,5}), 7);
        System.out.println(res == null ? "null" : res.val);
    }
    
    public ListNode findNthFromEnd(ListNode head, int k){
        if(head == null) return head;

        ListNode fast = head;
        int i = 0;
        for(; i < k && fast != null; i++){
            fast = fast.next;
        }
        if(i < k) return null; //防止K溢出。

        ListNode slow = head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
