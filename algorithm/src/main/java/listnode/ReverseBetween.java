package listnode;

public class ReverseBetween {
    public static void main(String[] args) {
        ReverseBetween reverseBetween = new ReverseBetween();

        reverseBetween.reverseBetween(ListNode.byArray(new int[] { 1, 2, 3, 4, 5 }), 2, 4).print();

        reverseBetween.reverseBetween(ListNode.byArray(new int[] { 3, 5 }), 1, 2).print();

        reverseBetween.reverseBetween(ListNode.byArray(new int[] { 1,2,3 }), 1, 2).print();
    }

    /**
     * 方法一
     * 
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left >= right)
            return head;

        int cnt = 1;
        ListNode pre = null;
        ListNode p = head; // 因为p指针一开始就是head，所以cnt=1开始。
        while (p != null) {
            if (cnt == left) break; // p抵达left位置，break
            cnt++;
            pre = p;
            p = p.next;
        }
        if (p == null) return head; // p走到了null节点，直接返回head

        ListNode pre2 = null;
        ListNode next = null;
        ListNode cur = p;
        while (cur != null) {
            next = cur.next;
            cur.next = pre2;
            if (cnt == right) break; // 将right位置的节点反转之后break
            cnt++;
            pre2 = cur;
            cur = next;
        }

        if (pre != null) { // pre != null说明并不是从第一个节点开始反转。
            pre.next = cur;
            p.next = next;
            return head;
        } else {
            p.next = next; // 从第一个节点开始反转。
            return cur;
        }
    }
}
