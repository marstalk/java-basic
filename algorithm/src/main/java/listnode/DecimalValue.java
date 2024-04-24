package listnode;

import java.util.Stack;

public class DecimalValue {
    public static void main(String[] args) {
        DecimalValue decimalValue = new DecimalValue();

        System.out.println(decimalValue.getDecimalValue(ListNode.byArray(new int[]{1,0,0,1,0,0,1,1,1,0,0,0,0,0,0}))); // 18880
        System.out.println(decimalValue.getDecimalValue(ListNode.byArray(new int[]{1,0,1}))); // 5

        System.out.println(decimalValue.calWithStack(ListNode.byArray(new int[]{1,0,0,1,0,0,1,1,1,0,0,0,0,0,0}))); // 18880
        System.out.println(decimalValue.calWithStack(ListNode.byArray(new int[]{1,0,1}))); // 5

        System.out.println(decimalValue.calWithMove(ListNode.byArray(new int[]{1,0,0,1,0,0,1,1,1,0,0,0,0,0,0}))); // 18880
        System.out.println(decimalValue.calWithMove(ListNode.byArray(new int[]{1,0,1}))); // 5
    }

    /**
     * 方法三
     * @param head
     * @return
     */
    public int calWithMove(ListNode head){
        if(head == null) return 0;
        int res = 0;
        while(head != null){
            res <<= 1 ;
            res |= head.val;
            head = head.next;
        }
        return res;
    }

    /**
     * 方法二
     * @param head
     * @return
     */
    public int calWithStack(ListNode head){
        if(head == null) return 0;
        if(head.next == null) return head.val;

        Stack<ListNode> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.next;
        }
        int cnt = 0;
        int res = 0;
        ListNode cur;
        while(!stack.isEmpty()){
            cur = stack.pop();
            res += cur.val * (1 << cnt++);
        }
        return res;
    }

    /**
     * 方法一
     */
    private int cnt;
    public int getDecimalValue(ListNode head){
        cnt = 0;
        return cal(head);
    }

    public int cal(ListNode head){
        if(head == null) return 0;
        int res = getDecimalValue(head.next);
        return res + head.val * (1 << cnt++);
    }
}
