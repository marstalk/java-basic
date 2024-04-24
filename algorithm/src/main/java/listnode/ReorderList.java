package listnode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class ReorderList {
    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        System.out.println("----reorder----");
        ListNode head = ListNode.byArray(new int[]{1,2,3,4,5});
        reorderList.reorder(head);
        head.print();

        head = ListNode.byArray(new int[]{1,2,3,4});
        reorderList.reorder(head);
        head.print();

        System.out.println("----reorder2----");
        head = ListNode.byArray(new int[]{1,2,1,2,3,1});
        reorderList.reorder2(head);
        head.print();

        head = ListNode.byArray(new int[]{1,2,3,4,5});
        reorderList.reorder2(head);
        head.print();

        System.out.println("----reorder3----");
        head = ListNode.byArray(new int[]{1,2,1,2,3,1});
        reorderList.reorder3(head);
        head.print();

        head = ListNode.byArray(new int[]{1,2,3,4,5});
        reorderList.reorder3(head);
        head.print();
    }

    /**
     * 方法三，双端队列
     * @param head
     */
    public void reorder3(ListNode head){
        if(head == null || head.next == null) return;

        ListNode cur = head;
        Deque<ListNode> deque = new LinkedList<>();
        while(cur != null){
            deque.addLast(cur);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(0);
        for(int i = 0; !deque.isEmpty(); i++, dummy = dummy.next, dummy.next = null){
            dummy.next = i % 2 == 0? deque.removeFirst() : deque.removeLast();
        }
    }

    /**
     * 方法二，双端队列，边界更好处理
     * @param head
     */
    public void reorder2(ListNode head){
        if(head == null || head.next == null) return;

        Deque<ListNode> deque = new LinkedList<>();
        while(head != null){
            deque.addLast(head);
            head = head.next;
        }

        boolean removeHead = true;
        ListNode dummy = new ListNode(0);
        while(!deque.isEmpty()){
            dummy.next = removeHead ? deque.removeFirst() : deque.removeLast();
            dummy = dummy.next;
            dummy.next = null;
            removeHead = !removeHead;
        }
    }
    
    /**
     * 方法一，自己思考的。
     * @param head
     */
    public void reorder(ListNode head){
        if(head == null || head.next == null) return ;
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        int cnt = 0;
        while(p != null){
            stack.push(p);
            p = p.next;
            cnt++;
        }

        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        p = head;

        int i = 0;
        while(true){
            if(i++ == cnt) break;
            res.next = p;
            res = res.next;
            p = p.next;
            
            ListNode n = stack.pop();
            if(i++ == cnt) break;
            res.next = n;
            res = res.next;
        }
        res.next = null;
    }
}
