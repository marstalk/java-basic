package treenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * Implement the BSTIterator class that represents an iterator over the in-order
 * traversal of a binary search tree (BST):
 * 
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class.
 * The root of the BST is given as part of the constructor. The pointer should
 * be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to
 * the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the
 * pointer.
 * Notice that by initializing the pointer to a non-existent smallest number,
 * the first call to next() will return the smallest element in the BST.
 * 
 * You may assume that next() calls will always be valid. That is, there will be
 * at least a next number in the in-order traversal when next() is called.
 * 
 * 
 * 你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h
 * 是树的高度。
 */
public class BstIterator {

    Queue<Integer> queue;
    /*
     * time complexity: O(N)
     * space complexity: O(N)
     */
    public BstIterator(TreeNode root) {
        queue = new LinkedList<>();
        inorder(root, queue);
    }

    private void inorder(TreeNode root, Queue<Integer> queue){
        if(root == null) return;
        
        inorder(root.left, queue);
        queue.offer(root.val);
        inorder(root.right, queue);
    }

    /*
     * O(1)
     */
    public int next() {
        return queue.poll();
    }

    /*
     * O(1)
     */
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
