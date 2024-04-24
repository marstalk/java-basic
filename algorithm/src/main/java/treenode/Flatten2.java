package treenode;

/* 
 You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

 Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.

 Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.
 */
public class Flatten2 {
    public static void main(String[] args) {
        Node head = Node.byArray3(new int[] { 1, 2, 3, 4, 5, 6, -1, -1, -1, 7, 8, 9, 10, -1, -1, 11, 12 });

        Flatten2 flatten2 = new Flatten2();
        Node flatten = flatten2.flatten(head);
        Node.print(flatten);
    }

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        flatten(head.child);
        flatten(head.next);

        Node child = head.child;
        Node next = head.next;
        if (child != null) {
            head.next = child;
            // doubly linked
            head.next.prev = head;
            head.child = null;
            Node p = child;
                while (p.next != null) {
                    p = p.next;
                }
                p.next = next;
                // doubly linked
                if(p.next != null){
                    p.next.prev = p;
                }
        }
        return head;
    }
}
