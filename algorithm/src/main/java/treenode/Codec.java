package treenode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 * Clarification: The input/output format is the same as how LeetCode serializes
 * a binary tree. You do not necessarily need to follow this format, so please
 * be creative and come up with different approaches yourself.
 * 
 * 
 */
public class Codec {
    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();

        TreeNode root = TreeNode.toTreeWithBFSArray(new int[] { 1, 2, 3, -1, -1, 4, 5 });
        TreeNode.printBFS(root);
        String code = ser.serialize(root);
        System.out.println("preorder code: " + code);
        TreeNode ans = deser.deserialize(code);
        TreeNode.printBFS(ans);

        System.out.println("-------");
        root = TreeNode.toTreeWithBFSArray(new int[] { 1, 2, 3, -1, -1, 4, -1 });
        TreeNode.printBFS(root);
        code = ser.serialize(root);
        System.out.println("preordder code: " + code);
        ans = deser.deserialize(code);
        TreeNode.printBFS(ans);
    }

    private static final String NULL = "#";
    private static final String SEP = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL);
            sb.append(SEP);
            return;
        }

        sb.append(root.val).append(SEP);

        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null)
            return null;
        LinkedList<String> list = new LinkedList<>(Arrays.asList(data.split(SEP)));
        return buildTree(list);
    }

    private TreeNode buildTree(LinkedList<String> list) {
        if (list == null || list.isEmpty())
            return null;

        String str = list.removeFirst();
        if (str.equals(NULL)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(str));
        root.left = buildTree(list);
        root.right = buildTree(list);

        return root;
    }
}
