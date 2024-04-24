package listnode;

public class Palindrone2 {
    public static void main(String[] args) {
        Palindrone2 palindrone = new Palindrone2();
        System.out.println(palindrone.isPalindrone(ListNode.byArray(new int[]{1,2,3,4,3,2,1}))); // true
        System.out.println(palindrone.isPalindrone(ListNode.byArray(new int[]{1,2,3,3,2,1}))); // true
        System.out.println(palindrone.isPalindrone(ListNode.byArray(new int[]{1,2,3,4,9,2,2})));// false
    }

    /**
     * space O(1)
     * time O(N)
     * 
     * 其实这个方法在并发情况下有很大问题的，因为更改了原本的数据结构。
     * @param head
     * @return
     */
    public boolean isPalindrone(ListNode head){
        System.out.println("------");
        // 1. 快慢指针找到中间节点
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // 保存mid节点，用于后面恢复链表。
        ListNode mid = slow;
        
        // 2. 以slow.next为head，对链表进行反转
        ListNode cur = reverse(slow.next);
        System.out.print("reversed: ");
        cur.print();

        // 3. 判断是否回文。
        ListNode p1 = head;
        ListNode p2 = cur;
        boolean isPalindrone = true;
        while(p2 != null){
            if(p1.val != p2.val){
                isPalindrone = false; // 不能直接return false，因为还需要还原链表。
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. 还原链表
        ListNode next = reverse(cur);
        mid.next = next;

        System.out.print("back: ");
        head.print();

        return isPalindrone;
    }

    private ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while(cur.next != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }   
        cur.next = pre;

        return cur;
    }
}
