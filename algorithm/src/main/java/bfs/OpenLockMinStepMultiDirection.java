package bfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 双向
 */
class OpenLockMinStepMultiDirection {

    public static void main(String[] args) {
        OpenLockMinStepMultiDirection openLockMinStep = new OpenLockMinStepMultiDirection();
        System.out.println(openLockMinStep.openLock(new String[] { "0201", "0101", "0102", "1212", "2002" }, "0202"));
    }

    public int openLock(String[] deadends, String target) {
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        q1.add("0000");
        // visited.add("0000"); // 不同于普通的BFS，双向BFS的visited标记放在visited.add(cur);。

        q2.add(target);
        // visited.add(target); // 不同于普通的BFS，双向BFS的visited标记放在visited.add(cur);。否则q2.contains(cur)永远返回false

        for (String deadend : deadends) {
            visited.add(deadend);
        }

        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            // TODO 这里可以优化为始终遍历较小的queue

            Set<String> tmp = new HashSet<>();
            
            for (String cur : q1) {
                if (q2.contains(cur)) {
                    return step;
                }

                visited.add(cur);

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        tmp.add(up);
                        // visited.add(up); // 不同于普通的BFS，双向BFS的visited标记放在visited.add(cur);。
                    }

                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        tmp.add(down);
                        // visited.add(down); // 不同于普通的BFS，双向BFS的visited标记放在visited.add(cur);。
                    }
                }
            }
            step++;

            q1 = q2;
            q2 = tmp;
        }

        return -1;
    }

    String plusOne(String s, int j) {
        char[] cs = s.toCharArray();
        if (cs[j] == '9') {
            cs[j] = '0';
        } else {
            cs[j] += 1;
        }
        return new String(cs);
    }

    String minusOne(String s, int j) {
        char[] cs = s.toCharArray();
        if (cs[j] == '0') {
            cs[j] = '9';
        } else {
            cs[j] -= 1;
        }
        return new String(cs);
    }

}