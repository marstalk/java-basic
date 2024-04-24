package backtracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 括号生成
 */
public class GenerateParentheis {
    public static void main(String[] args) {
        GenerateParentheis generateParentheis = new GenerateParentheis();
        System.out.println(generateParentheis.generateParentheis(3));
    }

    private List<String> res = new ArrayList<>();
    private Map<Character, Integer> cntMap = new HashMap<>(2);
    private static final char left = '(';
    private static final char right = ')';

    public List<String> generateParentheis(int k) {
        cntMap.put(left, 0);
        cntMap.put(right, 0);
        LinkedList<Character> path = new LinkedList<>();
        backtrace(k, path);
        return res;
    }

    private void backtrace(int k, LinkedList<Character> path) {
        // base
        if (path.size() == k * 2) {
            StringBuilder sb = new StringBuilder();
            for (char string : path) {
                sb.append(string);
            }
            res.add(sb.toString());
        }

        // loop choice
        for (char p : cntMap.keySet()) {
            int leftCnt = cntMap.get(left);
            if (p == left && leftCnt >= k) {
                continue;
            }
            int rightCnt = cntMap.get(right);
            if (p == right && (rightCnt >= k || rightCnt >= leftCnt)) {
                continue;
            }

            path.addLast(p);
            cntMap.put(p, cntMap.get(p) + 1);
            backtrace(k, path);
            path.removeLast();
            cntMap.put(p, cntMap.get(p) - 1);
        }
    }
}
