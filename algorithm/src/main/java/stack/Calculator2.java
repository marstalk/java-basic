package stack;

import java.util.Stack;

/**
 * > 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * >注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * 
 * > 1 <= s.length <= 3 * 105
 * > s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * > s 表示一个有效的表达式
 * > '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * > '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * > 输入中不存在两个连续的操作符
 * > 每个数字和运行的计算将适合于一个有符号的 32位 整数
 */
public class Calculator2 {
    public static void main(String[] args) {
        Calculator2 calculator = new Calculator2();
        System.out.println(calculator.cal("1+2")); // 3
        System.out.println(calculator.cal(" 2-1 + 2 ")); // 3
        System.out.println(calculator.cal("(1+(4+5+2)-3)+(6+8)")); // 23
        System.out.println(calculator.cal("2147483647"));
    }

    /**
     * 
     * @param s
     * @return
     */
    public int cal(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int res = 0;
        int sign = 1;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            } else if (ch == ')') {
                res = res * stack.pop() + stack.pop();
            } else if (ch >= '0' && ch <= '9') {
                int cur = ch - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    cur = cur * 10 + (s.charAt(++i) - '0');

                }
                res += sign * cur;
            }
        }

        return res;
    }
}
