package listnode;

import java.util.concurrent.atomic.AtomicInteger;

public class ReverseRecursive {
    
    public static void main(String[] args) {
        ListNode.headA.print();
        ListNode newListNode = simpleReverse(ListNode.headA);
        newListNode.print();

        reverseK(newListNode, 19).print();;
    }

    static AtomicInteger a = new AtomicInteger(-1);
    static Object lock = new Object();
    int get(){
        int r = a.incrementAndGet();
        if(r < 0){
            synchronized(lock){
                r = a.incrementAndGet();
                if(r < 0){
                    a = new AtomicInteger(-1);
                    r =  a.incrementAndGet();
                }
            }
        }
        return r;
    }


    /**
     * Simple reverse all the link
     * @param head
     * @return
     */
    private static ListNode simpleReverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode tmp = simpleReverse(head.next);

        head.next.next = head;
        head.next = null;

        return tmp;
    }

    /**
     * reverse k
     */
    private static ListNode successor = null;
    private static ListNode reverseK(ListNode head, int k){
        if(k == 1 || head.next == null){
            successor = head.next;
            return head;
        }

        ListNode last = reverseK(head.next, k - 1);

        head.next.next = head;
        head.next = successor;

        return last;
    }
}
