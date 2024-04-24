package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 有向图环的检测。
 * https://leetcode.cn/problems/course-schedule/description/
 * 课程表，course schedule
 */
public class BBCycleCheckerV5TopologyBfs {
    public static void main(String[] args) {
        BBCycleCheckerV5TopologyBfs bfs = new BBCycleCheckerV5TopologyBfs();
        System.out.println(bfs.canFinish(2, new int[][]{{1, 0}}));// true

        System.out.println(bfs.canFinish(2, new int[][]{{1, 0}, {0, 1}}));// false
    }


    public boolean canFinish(int numCourse, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourse, prerequisites);
        int[] inDegree = new int[numCourse];
        for(int[] pre : prerequisites){
            int to = pre[0];
            inDegree[to] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int node = 0; node < numCourse; node++){
            if (inDegree[node] == 0){
                queue.offer(node);
            }
        }

        int cnt = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            cnt++;

            for(int neighbor: graph[node]){
                inDegree[neighbor]--;
                if(inDegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }

        // cnt == numCourse means no cycle or can finish
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
