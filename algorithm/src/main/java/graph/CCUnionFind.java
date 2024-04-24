package graph;

/**
 * 并集查询，非常经典的图论算法之一。
 * 1. 自反性，初始化中，每个节点的父节点都是自己
 * 2. 对称性，if connected(a, b) is ture, then connected(b, a) must be true.
 * 3. propagation, if connected(a, b) and connected(b, c) is true, then connected(a, c) must be true.
 */
public class CCUnionFind {
    public static void main(String[] args) {
        CCUnionFind unionFind = new CCUnionFind(10);
        System.out.println(unionFind.count);

        System.out.println("connect 0, 2");
        unionFind.union(0, 2);
        System.out.println(unionFind.count);
        System.out.println("0 connect 2?" + unionFind.connected(0, 2));
        System.out.println("0 connect 1?" + unionFind.connected(0, 1));

        System.out.println("connect 1, 2");
        unionFind.union(1, 2);
        System.out.println(unionFind.count);
        System.out.println("0 connect 2?" + unionFind.connected(0, 2));
        System.out.println("0 connect 1?" + unionFind.connected(0, 1));

    }

    private int count;
    private int[] parent;
    public CCUnionFind(int count){
        // the number of nodes in this forest.
        this.count = count;

        // initial the parent array, make sure default parent is itself.
        parent = new int[count];
        for(int node = 0; node < count; node++){
            parent[node] = node;
        }
    }

    /**
     * try to union a and b
     * @param a
     * @param b
     */
    public void union(int a, int b){
        if(a == b) return;

        int rootA = findRoot(a);
        int rootB = findRoot(b);
        if(rootA == rootB) return;

        parent[rootA] = rootB;
        count--;
    }

    /**
     * to check if a and b is connected.
     * @param a
     * @param b
     * @return
     */
    public boolean connected(int a, int b){
        // 1.0 if their root is equal, then they are connected.
        return findRoot(a) == findRoot(b);
    }

    /**
     * 连通分量 number of trees in the forest.
     * @return
     */
    public int count(){
        return count;
    }

    /**
     * find the root of the tree, and make every node's parent is root.
     * @param node
     * @return root node
     */
    private int findRoot(int node){
        if(parent[node] == node) return node;

        // Attention!!!!, use if instead of while.
        if(parent[node] != node){
            // 把当前node的parent设置为root。
            // 这个root怎么找？那就是当前节点的parent的root。就递归上去了。
            // 什么时候结束递归？也就是到达了root节点之后，即parent[node] = node.
            parent[node] = findRoot(parent[node]);
        }
        return parent[node];
    }

}
