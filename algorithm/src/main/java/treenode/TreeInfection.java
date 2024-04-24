package treenode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

/**
 * 
You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.
Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.

Return the number of minutes needed for the entire tree to be infected.

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
Each node has a unique value.
A node with a value of start exists in the tree.

 */
public class TreeInfection {
    private int res;
    private TreeNode startedNode;

    public static void main(String[] args) {
        TreeNode root = TreeNode.toTreeWithBFSArray(new int[]{1,5,3,-1,4,10,6,9,2});
        TreeInfection treeInfection = new TreeInfection();
        System.out.println(treeInfection.amountOfTime(root, 3)); // 4
    }

    /**
     * Given the root of a binary tree, which has unique value.
     * approach: 
     * 1. first traverse the root and store the node's parent node in the HashMap. and find the started node.
     * 2. traverse by DFS from the started node. (it's left node and right node and parent node. use from to avoid duplicated traverse)
     * @param root
     * @param start
     * @return
     */
    public int amountOfTime(TreeNode root, int start){
        res = 0;
        startedNode = null;

        Map<Integer, TreeNode> parentMap = new HashMap<>();
        fillParentMap(root, null, parentMap, start);
        if (startedNode == null) return -1;

        findMaxDistance(startedNode, null, parentMap, 0);
        return res;
    }

    private void findMaxDistance(TreeNode node, TreeNode from, Map<Integer, TreeNode> parentMap, int distance){
        if (node == null) return;
        

        if (distance > res){
            res = distance;
        }

        if (!(from != null && node.left != null && node.left.val == from.val)){
            findMaxDistance(node.left, node, parentMap, distance + 1);
        } 
        
        if (!(from != null && node.right != null && node.right.val == from.val)){
            findMaxDistance(node.right, node, parentMap, distance + 1);
        }
        
        if (!(from != null && parentMap.get(node.val) != null && parentMap.get(node.val).val == from.val)){
            findMaxDistance(parentMap.get(node.val), node, parentMap, distance + 1);
        }
        
    }

    /**
     * Attention: the parent node in the map could be null.
     * @param node
     * @param parent
     * @param parentMap
     */
    private void fillParentMap(TreeNode node, TreeNode parent, Map<Integer, TreeNode> parentMap, int start){
        if (node == null) return;

        if (node.val == start){
            startedNode = node;
        }
        parentMap.put(node.val, parent);
        fillParentMap(node.left, node, parentMap, start);
        fillParentMap(node.right, node, parentMap, start);
    }
}
