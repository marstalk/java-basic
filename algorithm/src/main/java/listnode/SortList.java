package listnode;

import java.util.LinkedList;
import java.util.Queue;

public class SortList {
    public static void main(String[] args) {
        SortList sortList = new SortList();
        sortList.insertionSort(ListNode.byArray(new int[]{16,3,8,2,9,1,8,55,33,55,11,04})).print();
        
    }

    /**
     * quick sort
     * @param head
     * @return
     */
    public ListNode quickSort(ListNode head){
        //TODO
        return null;
    }
    
    /**
     * insertion sort，O(N^2)
     * @param head
     * @return
     */
    public ListNode insertionSort(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        Queue<ListNode> queue = new LinkedList<>();
        ListNode cur = head;
        while(cur != null){
            queue.offer(cur);
            cur = cur.next;
        }

        ListNode last = dummy;
        while(!queue.isEmpty()){
            cur = queue.poll();
            if(cur.val < last.val){
                last = dummy;
            }
            while(last.next != null && last.next.val < cur.val){
                last = last.next;
            }
            cur.next = last.next;
            last.next = cur;
            last = cur;
        }

        return dummy.next;
    }
}
