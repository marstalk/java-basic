package stack.monotonystack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a stock price array [33, 34, 14, 12, 16]
 * Return how many days of price goes up for every element in array. like
 * Return [1, 0, 2, 1, 0]
 */
public class StockPrice {
    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        int[] ints = stockPrice.nextGreater(new int[]{33, 34, 14, 12, 16});
        System.out.println(Arrays.toString(ints));
    }

    public int[] nextGreater(int[] price) {
        int[] res = new int[price.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = price.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }
}
