package listnode;

import java.util.PriorityQueue;

public class MergeKSortedList {
    public static void main(String[] args) {
        ListNode head1 = ListNode.byArray(new int[]{1,3,5,7,9});
        ListNode head2 = ListNode.byArray(new int[]{2,4,6,8,10});
        ListNode head3 = ListNode.byArray(new int[]{1,4,5,7,8});
        MergeKSortedList mergeKSortedList = new MergeKSortedList();
        mergeKSortedList.merge(new ListNode[]{head1, head2, head3}).print();
    }

    public ListNode merge(ListNode[] list){
        if(list == null || list.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(list.length, (a, b) -> a.val - b.val);
        for(ListNode head: list){
            queue.add(head);
        }

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while(!queue.isEmpty()){
            ListNode cur = queue.poll();
            if(cur.next != null){
                queue.add(cur.next);
            }
            p.next = cur;
            p = p.next;
        }

        return dummy.next;
    }
}
