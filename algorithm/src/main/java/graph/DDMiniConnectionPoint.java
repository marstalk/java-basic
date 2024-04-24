package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.cn/problems/min-cost-to-connect-all-points/description/
 * connections[i]表示点i。
 * connections[i][0]点i的x坐标。
 * connections[i][1]点i的y坐标。
 */
public class DDMiniConnectionPoint {

    public int minimumConnectionPoint(int[][] connections) {
        List<int[]> edges = new ArrayList<>();

        // 1. 构造出所有的边，及其曼哈顿距离 |Xi - Xj| + |Yi - Yj|
        for (int i = 0; i < connections.length; i++) {
            for (int j = i + 1; j < connections.length; j++) {
                int ix = connections[i][0];
                int iy = connections[i][1];
                int jx = connections[j][0];
                int jy = connections[j][1];
                edges.add(new int[]{i, j, Math.abs(ix - jx) + Math.abs(iy - jy)});
            }
        }

        // 2. 排序，从小到大，保证第3步中的【生成树】是【最小生成树】
        edges.sort(Comparator.comparingInt(a -> a[2]));

        // 3. 通过并查集找到生成树。
        UnionFind unionFind = new UnionFind(edges.size());
        int total = 0;
        for(int i = 0; i < edges.size(); i++){
            int a = edges.get(i)[0];
            int b = edges.get(i)[1];
            int w = edges.get(i)[2];
            if(unionFind.connected(a, b)) continue;
            unionFind.union(a, b);
            total+=w;
        }

        return total;
    }

    static class UnionFind {
        private int count;
        private final int[] parent;

        public UnionFind(int count) {
            this.count = count;
            this.parent = new int[count];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public void union(int i, int y) {
            if (i == y) return;

            int rootI = findRoot(i);
            int rootY = findRoot(y);
            if (rootI == rootY) return;

            parent[rootI] = rootY;
            count--;
        }

        public boolean connected(int i, int y) {
            return findRoot(i) == findRoot(y);
        }

        public int count() {
            return this.count();
        }

        private int findRoot(int i) {
            if (parent[i] != i) {
                parent[i] = findRoot(parent[i]);
            }
            return parent[i];
        }
    }
}
