package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 有向图环的检测。
 * https://leetcode.cn/problems/course-schedule/description/
 * 课程表，course schedule
 *
 * TODO 拓扑排序。
 *
 */
public class BBCycleCheckerV3TopologyOrder {
    public static void main(String[] args) {
        BBCycleCheckerV3TopologyOrder bbCycleChecker = new BBCycleCheckerV3TopologyOrder();
        System.out.println(bbCycleChecker.canFinish(2, new int[][]{{1, 0}}));// true

        System.out.println(bbCycleChecker.canFinish(2, new int[][]{{1, 0}, {0, 1}}));// false
    }

    private boolean hasCycle;
    private boolean[] visited;
    private boolean[] onPath;
    private LinkedList<Integer> postorder;

    public int[] canFinish(int numCourse, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return new int[]{};
        hasCycle = false;
        visited = new boolean[numCourse];
        onPath = new boolean[numCourse];
        postorder = new LinkedList<>();
        // build graph by prerequisites
        List<Integer>[] graph = buildGraph(numCourse, prerequisites);
        // with every node as beginner to check.
        for (int i = 0; i < numCourse; i++) {
            traverse(i, graph);
        }

        if (hasCycle){
            return new int[]{};
        }


        int[] rtn = new int[numCourse];
        for(int i = 0; i < numCourse; i++){
            rtn[i] = postorder.removeLast();
        }
        return rtn;
    }

    private void traverse(int node, List<Integer>[] graph) {
        //**SUPER IMPORTANT*** for a new node, we must check onPath first.
        if (onPath[node]) {
            hasCycle = true;
            return;
        }

        if (visited[node]) return;
        visited[node] = true;


        onPath[node] = true;
        for (int neighbor : graph[node]) {
            traverse(neighbor, graph);
        }
        postorder.addLast(node);
        onPath[node] = false;
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
