package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {

    public static void main(String[] args) {
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        System.out.println(slidingPuzzle.solve(new int[][] { { 4, 1, 2 }, { 5, 0, 3 } }));
        System.out.println(slidingPuzzle.solve(new int[][] { { 1, 2, 3 }, { 5, 4, 0 } }));
        System.out.println(slidingPuzzle.solve(new int[][] { { 1, 2, 3 }, { 4, 0, 5 } }));
    }

    public int solve(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int col : row) {
                sb.append(col);
            }
        }
        String start = sb.toString();

        int[][] neighbors = new int[][] { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals("123450")) {
                    return step;
                }

                int idx = 0;
                for (; cur.charAt(idx) != '0'; idx++) {
                }

                for (int adjIdx : neighbors[idx]) {
                    String adj = swap(cur, idx, adjIdx);
                    if (!visited.contains(adj)) {
                        queue.offer(adj);
                        visited.add(adj);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private String swap(String cur, int i, int j) {
        char[] chars = cur.toCharArray();
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
        return new String(chars);
    }

}
