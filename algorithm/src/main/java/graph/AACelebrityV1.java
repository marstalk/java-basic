package graph;

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
 */
public class AACelebrityV1 {
    private int[][] graph;

    public AACelebrityV1(int[][] graph) {
        this.graph = graph;
    }

    private boolean know(int candidate, int other){
        return graph[candidate][other] == 1;
    }

    public static void main(String[] args) {
        AACelebrityV1 aaCelebrityV1 = new AACelebrityV1(new int[][]{{1,1,1,0}, {1,1,1,1},{0,0,1,0},{0,0,1,1}});
        System.out.println(aaCelebrityV1.findCelebrity(4)); //2


        aaCelebrityV1 = new AACelebrityV1(new int[][]{{1,1,1,0}, {1,1,1,1},{0,1,1,0},{0,0,1,1}});
        System.out.println(aaCelebrityV1.findCelebrity(4)); //-1
    }
    /**
     * n is the number of the group of people. so the people id ranges from 0~n-1
     * the time complexity is N square. can it be optimized to O(N)
     * @param n
     * @return
     */
    public int findCelebrity(int n){
        // the most intuitive method is to loop all the candidate from 0 to n,
        // make sure know(candidate, other) is false and know(other, candidate) true, except know(candidate, candidate).
        for(int candidate = 0; candidate < n; candidate++){
            int other = 0;
            for(; other < n; other++){
                if(candidate == other) continue;
                if(know(candidate,  other) || !know(other, candidate)){
                    break;
                }
            }
            if (other == n){
                return candidate;
            }
        }
        return -1;
    }


}
