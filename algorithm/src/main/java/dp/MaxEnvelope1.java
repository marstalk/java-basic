package dp;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class MaxEnvelope1 {
    public static void main(String[] args) {
        MaxEnvelope1 maxEnvelope1 = new MaxEnvelope1();
        maxEnvelope1.maxEnvelop(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
    }


    private int res;
    public int maxEnvelop(int[][] envelops) {
        res = -1;
        boolean[] used = new boolean[envelops.length];
        List<int[]> path;
        for (int i = 0; i < envelops.length; i++) {
            path = new LinkedList<>();
            path.add(envelops[i]);
            used[i] = true;
            backtrace(envelops, path, used);
        }
        return res;
    }

    private void backtrace(int[][] envelops, List<int[]> path, boolean[] used) {
        boolean over = false;
        for(boolean b: used){
            //TODO
        }

    }
}
