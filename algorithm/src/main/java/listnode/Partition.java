package listnode;

public class Partition {
    public static void main(String[] args) {
        ListNode head = ListNode.byArray(new int[]{1,5,6,7,8,2,3,4, 2, 2, 5, 9, 0});
        Partition partition = new Partition();
        ListNode res = partition.partition(head, 4);
        res.print();

    }

    public ListNode partition(ListNode head, int x){
        if(head == null || head.next  == null) return head;

        ListNode small = new ListNode(0);
        ListNode s = small;
        ListNode big = new ListNode(0);
        ListNode b = big;
        while(head != null){
            if(head.val <= x){
                s.next = head;
                s = s.next;
            }else{
                b.next = head;
                b = b.next;
            }
            ListNode next = head.next;
            head.next = null;
            head = next;
        }
        s.next = big.next;
        return small.next;
    }
}
