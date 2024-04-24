package graph;


/**
 * 以图判树。
 * given a number n represents the node id from 0~n-1,
 * and int[][] edges, like edges={{0,1},{0,2},{0,3},{1,4}}, Attention, the edges has no duplicated edge such as {0,1}{1,0}
 * return if the edges can construct a tree (a tree is a graph without cycle)
 * a tree means:
 * 1. all the nodes is on the tree, 连通分量=1, there is only one tree in the forest.
 * 2. no cycle in the
 */
public class CCUnionFindV3GraphValidTree {

    public boolean validTree(int n, int[][] edges){
        if(edges == null || edges.length == 0) return false;

        UnionFind unionFind = new UnionFind(n);

        for(int[] edge: edges){
            int x = edge[0];
            int y = edge[1];
            // if x, and y are already connected, then cycle happen!!!, return false
            if(unionFind.connected(x, y)){
                return false;
            }
            unionFind.union(x, y);
        }

        return unionFind.count() == 1;
    }

}

class UnionFind {
    private int count;
    private final int[] parent;//parent[i] represent the parent of node i.

    public UnionFind(int count){
        this.parent = new int[count];
        for(int i = 0; i< count; i++){
            parent[i] = 1;
        }
        this.count = count;
    }

    public void union(int a, int b){
        if(a == b) return;
        int rootA = findRoot(a);
        int rootB = findRoot(b);
        if(rootA == rootB) return;

        parent[rootA] = rootB;
        count--;
    }

    public boolean connected(int a, int b){
        return findRoot(a) == findRoot(b);
    }

    public int count(){
        return this.count;
    }

    private int findRoot(int x){
        if(parent[x] !=x){
            parent[x] = findRoot(parent[x]);
        }
        return parent[x];
    }
}
