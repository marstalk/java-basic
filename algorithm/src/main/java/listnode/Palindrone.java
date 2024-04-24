package listnode;

import java.util.Stack;

public class Palindrone {
    public static void main(String[] args) {
        Palindrone palindrone = new Palindrone();

        System.out.println(palindrone.isPalindrone(ListNode.byArray(new int[]{1,2,3,4,3,2,1})));
        System.out.println(palindrone.isPalindrone(ListNode.byArray(new int[]{1,2,3,3,2,1})));
        System.out.println(palindrone.isPalindrone(ListNode.byArray(new int[]{1,2,3,4,4,2,1})));
        System.out.println("---method2----");
        System.out.println(palindrone.isPalindrone2(ListNode.byArray(new int[]{1,2,3,4,3,2,1})));
        System.out.println(palindrone.isPalindrone2(ListNode.byArray(new int[]{1,2,3,3,2,1})));
        System.out.println(palindrone.isPalindrone2(ListNode.byArray(new int[]{1,2,3,4,4,2,1})));
    }

    /**
     * method 2
     * space O(1)
     * time O(N)
     * @param head
     * @return
     */
    public boolean isPalindrone2(ListNode head){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(fast != null && fast.next != null){
            fast  = fast.next.next;
            slow = slow.next;
        }
        slow.next = reverse(slow.next);

        ListNode p1 = head;
        ListNode p2 = slow.next;
        while(p2 != null){
            if(p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }

        slow.next = reverse(slow.next);

        return true;
    }

    public ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = head;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * method 1
     * space O(N)
     * time O(N)
     * @param head
     * @return
     */
    public boolean isPalindrone(ListNode head){
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while(p != null){
            stack.push(p);
            p = p.next;
        }

        p = head;
        while(!stack.isEmpty()){
            ListNode node = stack.pop();
            if(node.val != p.val){
                return false;
            }
            p = p.next;
        }

        return true;
    }
}
