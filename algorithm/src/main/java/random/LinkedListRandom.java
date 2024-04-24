package random;

import listnode.ListNode;

import java.util.LinkedList;
import java.util.Random;

/**
 * 给你一个未知长度的单链表，请你设计一个算法，只能遍历一次，随机地返回链表中的一个节点
 *
 * 很典型的水塘抽样算法。
 */
public class LinkedListRandom {
    public static void main(String[] args) {
        LinkedListRandom linkedListRandom = new LinkedListRandom();
        for (int i = 0; i < 1000; i++) {
            System.out.println(linkedListRandom.solve(ListNode.headA));
        }
    }

    public int solve(ListNode head){
        ListNode res = null;
        Random random = new Random();
        int i = 0;
        ListNode p = head;
        while(p != null){
            i++;
            if(0 == random.nextInt(i)){
                res = p;
            }
            p = p.next;
        }

        return res.val;
    }
}
