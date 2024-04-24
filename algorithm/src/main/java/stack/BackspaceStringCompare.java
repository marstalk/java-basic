package stack;

import java.util.Stack;

public class BackspaceStringCompare {
    public static void main(String[] args) {
        BackspaceStringCompare backspaceStringCompare = new BackspaceStringCompare();
        System.out.println(backspaceStringCompare.backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceStringCompare.backspaceCompare("ab##", "c#d#"));
        System.out.println(backspaceStringCompare.backspaceCompare("a#c", "b"));

        System.out.println("----method2----");
        System.out.println(backspaceStringCompare.backspaceCompare2("ab#c", "ad#c"));
        System.out.println(backspaceStringCompare.backspaceCompare2("ab##", "c#d#"));
        System.out.println(backspaceStringCompare.backspaceCompare2("a#c", "b"));
    }

    /**
     * method 2 , SO (1),
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare2(String s, String t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null)
            return false;

        int i = s.length() - 1;
        int j = t.length() - 1;
        int sCnt = 0;
        int tCnt = 0;
        while (i > -1 || j > -1) {
            //只要有一个没有遍历完，就一直循环。
            while (i > -1) {
                if (s.charAt(i) == '#') {
                    sCnt++;
                } else {
                    if (sCnt > 0) {
                        sCnt--;
                    } else {
                        // 要么遍历完，要么找到了【合法的字符】。
                        break;
                    }
                }
                i--;
            }

            while (j > -1) {
                if (t.charAt(j) == '#') {
                    tCnt++;
                } else {
                    if (tCnt > 0) {
                        tCnt--;
                    } else {
                        break;
                    }
                }
                j--;
            }

            if(i == -1 && j == -1) return true;// 两个同时遍历完。
            if(i == -1 || j == -1) return false;// 只有一个遍历完
            if(s.charAt(i) != t.charAt(j)) return false; //两个都没有遍历完，但是字符不相同
            i--;
            j--;
        }

        return true;
    }

    /**
     * 方法一， space complexity O(n+m), time complexity O(n+m)
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null)
            return false;

        Stack<Character> s1 = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#' && !s1.isEmpty()) {
                s1.pop();
            } else {
                s1.push(c);
            }
        }

        Stack<Character> s2 = new Stack<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c == '#' && !s2.isEmpty()) {
                s2.pop();
            } else {
                s2.push(c);
            }
        }

        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (s1.pop() != s2.pop())
                return false;
        }

        return s1.isEmpty() && s2.isEmpty();
    }
}
