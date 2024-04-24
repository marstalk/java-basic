package treenode;

/**
 * One way to serialize a binary tree is to use preorder traversal. When we
 * encounter a non-null node, we record the node's value. If it is a null node,
 * we record using a sentinel value such as '#'.
 * 
 * 
 * For example, the above binary tree can be serialized to the string
 * "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
 * 
 * Given a string of comma-separated values preorder, return true if it is a
 * correct preorder traversal serialization of a binary tree.
 * 
 * It is guaranteed that each comma-separated value in the string must be either
 * an integer or a character '#' representing null pointer.
 * 
 * You may assume that the input format is always valid.
 * 
 * For example, it could never contain two consecutive commas, such as "1,,3".
 * Note: You are not allowed to reconstruct the tree.
 * 
 * 
 * preorder consist of integers in the range [0, 100] and '#' separated by commas ','.
 */
public class CheckSerialization {
    public static void main(String[] args) {
        CheckSerialization checkSerialization = new CheckSerialization();
        
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(checkSerialization.isValidSerialization(preorder)); // true

        preorder = "1,#";
        System.out.println(checkSerialization.isValidSerialization(preorder)); // false

        preorder = "9,#,#,1";
        System.out.println(checkSerialization.isValidSerialization(preorder)); // false
    }

    /**
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder == null)
            return true;
        int cnt = 0;
        for (int i = preorder.length() - 1; i > -1; i--) {
            char c = preorder.charAt(i);
            if (c == ',') {
                // 遇到逗号，不做任何处理。
                continue;
            }

            if (c == '#') {
                // 遇到井号#，cnt++，记录当前#号个数。
                cnt++;
            } else {
                // 遇到数字，有可能是一位数，也可能是两位数，也有可能是三位数。
                while (i > 0 && preorder.charAt(i - 1) != ',') {
                    i--;
                }
                // 没遇到一个数字，就要消去两个cnt，并将数字变成一个cnt，总结下来就是cnt--
                if (cnt >= 2) {
                    cnt--;
                } else {
                    // 不够两个井号#，说明是不合法的结构。
                    return false;
                }
            }
        }

        // 如果合法的话，最后一定会有一个#
        return cnt == 1;
    }
}
