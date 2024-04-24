package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 有向图环的检测。
 * https://leetcode.cn/problems/course-schedule/description/
 * 课程表，course schedule
 *
 * 如果有环，找到这个环，（任意的环都可以）
 * 进一步挑战：找到所有的环。
 */
public class BBCycleCheckerV2FindCycle {
    public static void main(String[] args) {
        BBCycleCheckerV2FindCycle bbCycleChecker = new BBCycleCheckerV2FindCycle();
        System.out.println(bbCycleChecker.canFinish(2, new int[][]{{1, 0}}));// true

        System.out.println(bbCycleChecker.canFinish(2, new int[][]{{1, 0}, {0, 1}}));// false
    }

    private boolean hasCycle;
    private boolean[] visited;
    private List<List<Integer>> cycles;
    private LinkedList<Integer> path;
    private int cycleHead;

    public boolean canFinish(int numCourse, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;
        hasCycle = false;
        visited = new boolean[numCourse];
        path = new LinkedList<>();
        cycles = new ArrayList<>();
        // build graph by prerequisites
        List<Integer>[] graph = buildGraph(numCourse, prerequisites);
        // with every node as beginner to check.
        for (int i = 0; i < numCourse; i++) {
            traverse(i, graph);
        }

        if(hasCycle){
            System.out.println("hasCycle");
            for(int node: path){

            }
        }
        return !hasCycle;
    }

    private void traverse(int node, List<Integer>[] graph) {
        //**SUPER IMPORTANT*** for a new node, we must check onPath first.
        if (path.contains(node)) {
            hasCycle = true;
            cycleHead = node;

            return;
        }

        if (visited[node]) return;
        visited[node] = true;


        path.addLast(node);
        for (int neighbor : graph[node]) {
            traverse(neighbor, graph);
        }
        path.removeLast();
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
