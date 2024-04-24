package stack;

import java.util.Stack;

/**
 * HG[3|B[2|CA]]F -> HGBCACABCACABCACAF
 */
public class Jiemi {
    public static void main(String[] args) {
        Jiemi jiemi = new Jiemi();
        System.out.println(jiemi.jiemi("HG[3|B[2|CA]]F"));
    }
    public String jiemi(String org) {
        if (org == null) return null;
        StringBuilder res = new StringBuilder();
        Stack<Object> stack = new Stack<>();
        int num = 0;
        for (char ch : org.toCharArray()) {
            if (ch == '[') {
                stack.push(ch);
            } else if (Character.isDigit(ch)) {
                num = num * 10 + Integer.parseInt(String.valueOf(ch));
            } else if (ch == '|') {
                stack.push(num);
                num = 0;
            } else if (ch == ']') {
                StringBuilder subStr = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() instanceof String) {
                    subStr.insert(0, stack.pop().toString());
                }
                int cnt = (int) stack.pop();
                stack.pop();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cnt; i++) {
                    sb.append(subStr);
                }
                stack.push(sb.toString());
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }
}
