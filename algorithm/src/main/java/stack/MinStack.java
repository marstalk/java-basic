package stack;

/**
 * Time complexity is O(1) for all methods: push(), pop(), top(), getMin()，
 * 设计一个栈，使得一下方法的时间复杂度都是O(1)。
 */
public class MinStack {
    public static void main(String[] args) {
        //["MinStack","push","push","push","getMin","pop","top","getMin"] 
        // [[],[-2],[0],[-3],[],[],[],[]]
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }



    private Node head;
    public MinStack() {

    }

    /**
     * push into tack, with time complexity is O(1).
     */
    public void push(int val) {
        if(head == null){
            head = new Node(val, val);
        }else{
            head = new Node(val, Math.min(val, head.min), head);
        }
    }

    /**
     * pop out the head, with time complexity is O(1)
     */
    public int pop() {
        if (head == null){
            return -1;
        }
        int rtn = head.val;
        head = head.next;
        return rtn;
    }

    /**
     * get the head of stack with complexity is O(1)
     */
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
}

class Node{
    int val;
    int min;
    Node next;
    
    public Node(int val, int min){
        this.val = val;
        this.min = min;
    }

    public Node(int val, int min, Node next){
        this.val = val;
        this.min = min;
        this.next = next;
    }
}
