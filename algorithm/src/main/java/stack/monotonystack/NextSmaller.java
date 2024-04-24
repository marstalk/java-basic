package stack.monotonystack;

import java.util.Arrays;
import java.util.Stack;

public class NextSmaller {
    public static void main(String[] args) {
        NextSmaller nextSmaller = new NextSmaller();
        int[] ints = nextSmaller.nextSmaller(new int[]{4, 5, 1, 3, 2});
        System.out.println(Arrays.toString(ints));
    }
    public int[] nextSmaller(int[] nums){
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = nums.length-1; i > -1; i--){
            while(!stack.isEmpty() && stack.peek() >= nums[i]){
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }
}
