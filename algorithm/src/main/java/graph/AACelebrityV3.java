package graph;

import java.util.LinkedList;

/**
 * 名流问题，在n个节点的图中。
 * 1. 名流0出度
 * 2. 名流n-1入度，即所有的人都认识它，
 * 3. 可以进一步推断出，整个图中只有一个名流。因为假设有2个命令，那么每个命令的入度只有n-2，并不满足条件。
 *
 * 提供know(int a, int b)函数使用：
 * 1. 如果a认识b，返回true。
 * 2. 如果a不认识b，返回false。
 * 其实相当于邻接矩阵graph[a][b] = true，表示a认识b。
 *
 * in fact, for every compare, we could find out someone is not celebrity.
 * if know(a, b) is true, then a must not be a celebrity, then exclude a
 * if know(b, a) is true, then b must not be a celebrity, then exclude b
 * if there is a candidate stack, I pop the top two candidate to calculate, then there must be at least on will be excluded
 */
public class AACelebrityV3 {
    private final int[][] graph;

    public AACelebrityV3(int[][] graph) {
        this.graph = graph;
    }

    private boolean know(int candidate, int other){
        return graph[candidate][other] == 1;
    }

    public static void main(String[] args) {
        AACelebrityV3 aaCelebrityV1 = new AACelebrityV3(new int[][]{{1,1,1,0}, {1,1,1,1},{0,0,1,0},{0,0,1,1}});
        System.out.println(aaCelebrityV1.findCelebrity(4)); //2

        aaCelebrityV1 = new AACelebrityV3(new int[][]{{1,1,1,0}, {1,1,1,1},{0,1,1,0},{0,0,1,1}});
        System.out.println(aaCelebrityV1.findCelebrity(4)); //-1
    }
    /**
     * n is the number of the group of people. so the people id ranges from 0~n-1
     * @param n
     * @return
     */
    public int findCelebrity(int n){
        int candidate = 0;
        for(int other = 1; other < n; other++){
            // can we exclude other or candidate?
            if(know(candidate, other) || !know(other, candidate)){
                candidate = other;
            }
        }

        for(int other = 0; other < n; other++){
            if(candidate == other) continue;
            if (know(candidate, other) || !know(other, candidate)){
                return -1;
            }
        }
        return candidate;
    }


}
