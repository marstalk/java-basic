package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthese {
    public static void main(String[] args) {
        ValidParenthese validParenthese = new ValidParenthese();

        System.out.println(validParenthese.isValid("()[]{}"));
        System.out.println(validParenthese.isValid("(]"));
    }
    
    public boolean isValid(String s){
        if(s == null || s.length() == 0) return true;

        Map<Character, Character> chars = new HashMap<>();
        chars.put( ')', '(');
        chars.put( ']', '[');
        chars.put( '}', '{');

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i< s.length(); i++){
            char c = s.charAt(i);
            if(chars.containsValue(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty()) return false;
                char c2 = stack.pop();
                if(chars.get(c) != c2) return false;
            }
        }

        return stack.isEmpty();
    }
}
