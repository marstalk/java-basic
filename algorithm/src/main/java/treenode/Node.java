package treenode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * doubly linked node
 */
public class Node {
    public int val;
    public Node next;
    public Node prev;
    public Node child;

    public Node(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        Node head = Node.byArray3(new int[] { 1, 2, 3, 4, 5, 6, -1, -1, -1, 7, 8, 9, 10, -1, -1, 11, 12 });
        Node.print(head);
    }

    /**
     * pass all test case
     * @param arr
     * @return
     */
    public static Node byArray3(int[] arr) {
        if (arr == null) {
            return null;
        }

        Node dummy = new Node(-1);
        Node p = dummy;

        boolean nextLevel = false;
        for (int val : arr) {
            if (val != -1) {
                Node node = new Node(val);
                if (nextLevel) {
                    p.child = node;
                    p = p.child;
                    nextLevel = false;
                } else if (!nextLevel) {
                    p.next = node;
                    p.next.prev = p;
                    p = p.next;
                }
            } else if (val == -1) {
                p = p.prev;
                nextLevel = true;
            }
        }

        return dummy.next;
    }

    /**
     * Failed
     * 
     * @param arr
     * @return
     */
    public Node byArray2(int[] arr) {
        return buildSub(arr, 0, arr.length, 0);
    }

    /**
     * 1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12
     * 左闭右开
     * 
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private Node buildSub(int[] arr, int start, int end, int lastLevelStart) {
        if (start >= end) {
            return null;
        }
        int nullCnt = 0;
        for (int i = start; i < end; i++) {
            int val = arr[start];
            if (val == -1) {
                nullCnt++;
            }
        }
        int nextStart = start + 1;
        int nextEnd = end;
        int childStart = start + 1;
        int childEnd = start + 1;
        lastLevelStart = 0;// TODO
        if (nullCnt == start - lastLevelStart) {

        }
        // 确定右树和子树的边界。

        Node node = new Node(arr[start]);
        Node next = buildSub(arr, nextStart, nextEnd, lastLevelStart);
        if (next != null) {
            node.next = next;
            node.next.prev = node;
        }

        node.child = buildSub(arr, childStart, childEnd, lastLevelStart);

        return node;
    }

    /**
     * Failed
     * 1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12
     * >>>>
     * 1,....2,...3,..4, 5,6,null,
     * ...........|
     * null,null, 7,..8, 9,10,null,
     * ...............|
     * ...........ull,11,12
     * 
     * @param arrays:
     *            -1 denotes null
     */
    public Node byArray(int[] arr) {
        if (arr == null)
            return null;

        Node dummy = new Node(-1);
        Node cur = dummy;
        boolean nextLevel = false;
        for (int i = 0; i < arr.length; i++) {
            if (nextLevel) {
                while (cur.prev != null) {
                    cur = cur.prev;
                }
            }
            int val = arr[i];
            if (val != -1) {
                Node node = new Node(val);
                cur.next = node;
                cur.next.prev = cur;
                cur = cur.next;
            }
            if (nextLevel && val != -1) {
                Node node = new Node(val);
                cur.child = node;
                cur = cur.child;
                nextLevel = false;
            } else if (nextLevel && val == -1) {
                cur = cur.next;
            } else if (val == -1) {
                cur.next = null;
                nextLevel = true;
            }
        }

        return dummy.next;
    }

    public static void print(Node head) {
        if (head == null) {
            System.out.println("null");
        }

        Map<Node, Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            while (node != null) {
                if (map.containsKey(node)) {
                    System.out.print(map.get(node) + ">");
                }
                System.out.print(node.val + " ");
                if (node.child != null) {
                    queue.offer(node.child);
                    map.put(node.child, node.val);
                }
                node = node.next;
            }
            System.out.println("");
        }
    }
}
