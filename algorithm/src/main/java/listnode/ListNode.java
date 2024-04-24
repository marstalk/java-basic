package listnode;

public class ListNode {
    /**
     * 4,7,2,5,1,headC
     */
    public static ListNode headA;
    /**
     * 2,9,headC
     */
    public static ListNode headB;

    /**
     * 99
     */
    public static ListNode headC;

    public int val;
    public ListNode next;

    static{
        headC = new ListNode(99);

        ListNode a = new ListNode(1, headC);
        ListNode b = new ListNode(5, a);
        ListNode c = new ListNode(2, b);
        ListNode d = new ListNode(7, c);
        headA = new ListNode (4, d);

        ListNode e = new ListNode(9, headC);
        headB = new ListNode(2, e);
    }

    public ListNode(int val){
        this.val = val;
    }
    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return this.val + "";
    }    

    public void print(){
        for(ListNode tmp = this; tmp != null; tmp = tmp.next){
            System.out.print(tmp.val + " -> ");
        }
        System.out.println("null");
    }

    public static ListNode byArray(int[] array){
        if(array == null || array.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (int value: array) {
            p.next = new ListNode(value);
            p = p.next;
        }
        return dummy.next;
    }
}
