package graph;

import java.util.Arrays;
import java.util.Comparator;

/**
 * n个城市，cityId分别是0...n-1, 以及两个城市联通的成本：[cityId_1, cityId_2, cost]，返回连通所有城市的最低成本。
 * 1. 要避免环的出现，因为一旦有环，则存在某一个边是重复建设，那么它的成本肯定不是最低的。
 * 2. 所有的边都连上之后，也不一定能够联通所有城市，这种情况下直接返回-1。
 * 3. 从最小的边开始连起来。
 */
public class CCUnionFindV4MinimumCost {
    public static void main(String[] args) {

    }

    public int minimumCost(int n, int[][] connections) {
        // 1. 根据建设成本connection[i][2]递增排序
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));

        UnionFind2 unionFind2 = new UnionFind2(n);

        // 2. 遍历所有的边
        int totalWeight = 0;
        for(int[] connection: connections){
            int a = connection[0];
            int b = connection[1];
            int weight = connection[2];
            if(unionFind2.connect(a, b)){
                continue;
            }

            unionFind2.union(a, b);
            totalWeight += weight;
        }

        // 3. 如果连通分量是1（即所有的城市节点都连接起来了）
        return unionFind2.count() == 1 ? totalWeight : -1;
    }
}

class UnionFind2 {
    private int count;
    private final int[] parent;

    public UnionFind2(int count) {
        this.count = count;
        parent = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
        }
    }

    public void union(int a, int b) {
        if (a == b) return;

        int rootA = findRoot(a);
        int rootB = findRoot(b);
        if (rootA == rootB) return;

        parent[rootA] = rootB;
        count--;
    }

    public boolean connect(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    public int count(){
        return this.count;
    }

    /**
     * find and set root
     */
    private int findRoot(int a) {
        if (parent[a] != a) {
            parent[a] = findRoot(parent[a]);
        }
        return parent[a];
    }
}
