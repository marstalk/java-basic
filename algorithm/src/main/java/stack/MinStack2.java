package stack;

import java.util.Stack;

/**
 * Time complexity is O(1) for all methods: push(), pop(), top(), getMin()，
 * 
 */
public class MinStack2 {

    public static void main(String[] args) {
        //["MinStack","push","push","push","getMin","pop","top","getMin"] 
        // [[],[-2],[0],[-3],[],[],[],[]]
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // -3
        minStack.pop();
        System.out.println(minStack.top()); // 0
        System.out.println(minStack.getMin()); //-2
    }

    Stack<Integer> stack;
    public MinStack2() {

    }
    
    public void push(int val) {
        if(stack == null){
            stack = new Stack<>();
            stack.push(val);
            stack.push(val);
        }else{
            int min  = stack.peek();
            stack.push(val);
            if(val < min){
                stack.push(val);
            }else{
                stack.push(min);
            }
        }
    }
    
    public void pop() {
        stack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.get(stack.size() - 2);
    }
    
    public int getMin() {
        return stack.peek();
    }
}
