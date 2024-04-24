package stack;

import java.util.Stack;

/**
 * 在jiemi的基础上增加对数字的支持。
 * H2G[3|B[2|CA]]F -> HGBCACABCACABCACAF
 */
public class Jiemi2 {
    public static void main(String[] args) {
        Jiemi2 jiemi = new Jiemi2();
        System.out.println(jiemi.jiemi("H2G2[3|B[11|P]]FDV"));
    }

    public String jiemi(String org) {
        if (org == null) return null;
        StringBuilder res = new StringBuilder();
        Stack<Object> stack = new Stack<>();
        for (char ch : org.toCharArray()) {
            if (ch == '[') {
                stack.push(ch);
            } else if (ch == '|') {
                int num = 0;
                int moveLeft = 0;
                while (!stack.isEmpty() && stack.peek() instanceof Character && (Character) stack.peek() != '[') {
                    num = (int) (Integer.parseInt(String.valueOf(stack.pop())) * Math.pow(10, moveLeft++) + num);
                }
                System.out.println(num);
                stack.push(num);
                stack.push(ch);
            } else if (ch == ']') {
                StringBuilder subStr = new StringBuilder();
                // stack != null && (栈顶是String || 栈顶不是|)
                while (!stack.isEmpty() && ((stack.peek() instanceof String) || (stack.peek() instanceof Character && (Character) stack.peek() != '|'))) {
                    subStr.insert(0, stack.pop().toString());
                }
                // 去掉栈顶 |
                stack.pop();
                int cnt = (int) stack.pop();
                // 去掉栈顶 [
                stack.pop();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cnt; i++) {
                    sb.append(subStr);
                }
                stack.push(sb.toString());
            } else {
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }
}
