package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 有向图环的检测。
 * https://leetcode.cn/problems/course-schedule/description/
 * 课程表，course schedule
 */
public class BBCycleCheckerV4Bfs {
    public static void main(String[] args) {
        BBCycleCheckerV4Bfs bfs = new BBCycleCheckerV4Bfs();
        System.out.println(bfs.canFinish(2, new int[][]{{1, 0}}));// true

        System.out.println(bfs.canFinish(2, new int[][]{{1, 0}, {0, 1}}));// false
    }


    public boolean canFinish(int numCourse, int[][] prerequisites) {
        // 1. build the graph
        List<Integer>[] graph = buildGraph(numCourse, prerequisites);

        // 2. build in degree array inDegree[i] means the in degree of node i
        int[] inDegree = new int[numCourse];
        for(int[] pre : prerequisites){
            int to = pre[0];
            inDegree[to] += 1;
        }

        // 3. put those with zero of in degree into the queue.
        Queue<Integer> queue = new LinkedList<>();
        for(int node = 0; node < numCourse; node++){
            if (inDegree[node] == 0){
                queue.offer(node);
            }
        }

        // 4. cnt as a helper to count the poll operation,
        int cnt = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            cnt++;

            // 4.1 for every neighbor, minus 1 then offer to the queue if result in degree is zero.
            for(int neighbor: graph[node]){
                inDegree[neighbor]--;
                if(inDegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }

        // 5. cnt == numCourse means no cycle or can finish
        return cnt == numCourse;
    }

    private List<Integer>[] buildGraph(int num, int[][] prerequisites) {
        LinkedList<Integer>[] graph = new LinkedList[num];
        for (int i = 0; i <num; i++){
            graph[i] = new LinkedList<>();
        }
        for (int[] req : prerequisites) {
            int from = req[1];
            int to = req[0];
            graph[from].add(to);
        }
        return graph;
    }
}
