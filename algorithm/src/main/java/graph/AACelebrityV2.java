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
public class AACelebrityV2 {
    private int[][] graph;

    public AACelebrityV2(int[][] graph) {
        this.graph = graph;
    }

    private boolean know(int candidate, int other){
        return graph[candidate][other] == 1;
    }

    public static void main(String[] args) {
        AACelebrityV2 aaCelebrityV1 = new AACelebrityV2(new int[][]{{1,1,1,0}, {1,1,1,1},{0,0,1,0},{0,0,1,1}});
        System.out.println(aaCelebrityV1.findCelebrity(4)); //2

        aaCelebrityV1 = new AACelebrityV2(new int[][]{{1,1,1,0}, {1,1,1,1},{0,1,1,0},{0,0,1,1}});
        System.out.println(aaCelebrityV1.findCelebrity(4)); //-1
    }
    /**
     * n is the number of the group of people. so the people id ranges from 0~n-1
     * time complexity is N,
     * space complexity is N, could this be optimized??
     * @param n
     * @return
     */
    public int findCelebrity(int n){
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < n; i++){
            list.addLast(i);
        }

        while(list.size() >= 2){
            int a = list.removeFirst();
            int b = list.removeFirst();
            // below code has a trap: in the case of non-celebrity, a know b and b know a.
//            if(know(a, b)){
//                list.addFirst(b);
//            }
//            if(know(b, a)){
//                list.addFirst(a);
//            }

            if(know(a, b)){
                list.addFirst(b);
            }else if(know(b, a)){
                list.addFirst(a);
            }
            // a don't know b and b don't know a, // a, b is removed
        }

        if (list.isEmpty()) return -1;

        int candidate = list.removeFirst();
        for(int other = 0; other < n; other++){
            if(candidate == other) continue;
            if(know(candidate, other) || !know(other, candidate)){
                return -1;
            }
        }

        return candidate;
    }


}
