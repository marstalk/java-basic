package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenLockMinStep {
    public static void main(String[] args) {
        // deadends = ["0201","0101","0102","1212","2002"], target = "0202"
        OpenLockMinStep openLockMinStep = new OpenLockMinStep();
        System.out.println(openLockMinStep.openLock(new String[] { "0201", "0101", "0102", "1212", "2002" }, "0202"));
    }

    public int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer("0000");
        visited.add("0000");

        for (String deadend : deadends) {
            if (deadend.equals("0000")) {
                return -1;
            }
            visited.add(deadend);
        }

        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String s = q.poll();
                if (s.equals(target)) {
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String plusOne = plusOne(s, j);
                    if (!visited.contains(plusOne)) {
                        q.offer(plusOne);
                        visited.add(plusOne);
                    }

                    String minusOne = minusOne(s, j);
                    if (!visited.contains(minusOne)) {
                        q.offer(minusOne);
                        visited.add(minusOne);
                    }
                }
            }
            step++;
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
