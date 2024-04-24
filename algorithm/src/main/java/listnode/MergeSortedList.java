package listnode;

/**
 * 合并有序链表
 */
public class MergeSortedList {
    public static void main(String[] args) {
        ListNode headA = ListNode.byArray(new int[]{1,2,5,6,9,});
        ListNode headB = ListNode.byArray(new int[]{3,4,6,7,13,20});

        MergeSortedList mergeSortedList = new MergeSortedList();
        ListNode res = mergeSortedList.merge(headA, headB);
        res.print();
    }

    public ListNode merge(ListNode headA, ListNode headB){
        if(headA == null) return headB;
        if(headB == null) return headA;

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while(headA != null && headB != null){
            if(headA.val <= headB.val){
                p.next = headA;
                headA = headA.next;
            }else{
                p.next = headB;
                headB = headB.next;
            }
            p = p.next;
        }

        if(headA == null){
            p.next = headB;
        }else{
            p.next = headA;
        }

        return dummy.next;
    }
}
