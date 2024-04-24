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
public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.cal("1+2")); //3
        System.out.println(calculator.cal(" 2-1 + 2 ")); //3
        System.out.println(calculator.cal("(1+(4+5+2)-3)+(6+8)")); //23
    }

    public int cal(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Stack<String> stack = new Stack<>();
        Stack<String> helper = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;

            if (c == ')') {
                // encounter ), do calculate
                String x = stack.pop();
                while (!x.equals("(")) {
                    //将(之前的所有数和操作
                    helper.push(String.valueOf(x));
                    x = stack.pop();
                }
                int res = calStack(helper);
                stack.push(String.valueOf(res));
            } else {
                // number, +, -, ( push to stack
                stack.push(String.valueOf(c));
            }
        }
        while(!stack.isEmpty()){
            helper.push(stack.pop());
        }
        return calStack(helper);
    }

    private int calStack(Stack<String> stack) {
        if (stack == null || stack.isEmpty())
            return 0;
        if (stack.size() == 1)
            return Integer.valueOf(stack.pop());

        int res = 0;
        res += Integer.valueOf(stack.pop());
        while (!stack.isEmpty()) {
            String operator = stack.pop();
            int b = Integer.valueOf(stack.pop());
            res = doCal(res, b, operator);
        }
        return res;
    }

    private int doCal(int a, int b, String operator) {
        if ("+".equals(operator))
            return a + b;
        if ("-".equals(operator))
            return a - b;
        return 0;
    }
}
