package stack;

import java.util.Stack;

/**
 * 整数 x - 表示本回合新获得分数 x
 * "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
 * "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * 请你返回记录中所有得分的总和。
 */
public class BaseballGame {
    public static void main(String[] args) {
        BaseballGame game = new BaseballGame();
        System.out.println(game.calPoints(new String[]{"5","2","C","D","+"}));//30
        System.out.println(game.calPoints(new String[]{"5","-2","4","C","D","9","+","+"}));//27
    }

    public int calPoints(String[] operations){
        if(operations == null) return 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < operations.length; i++) {
            String str = operations[i];
            if("+".equals(str)){
                int size = stack.size();
                stack.push(stack.get(size -1) + stack.get(size -2));
            }else if("D".equals(str)){
                stack.push(stack.peek() << 1);
            }else if("C".equals(str)){
                stack.pop();
            }else{
                stack.push(Integer.valueOf(str));
            }
        }
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}
