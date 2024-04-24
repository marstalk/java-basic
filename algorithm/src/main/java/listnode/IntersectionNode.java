package listnode;

import java.util.HashSet;
import java.util.Set;

public class IntersectionNode {
    public static void main(String[] args) {
        System.out.println(intersectionNode(ListNode.headA, ListNode.headB));

        System.out.println("---");
        System.out.println(intersectionNodeB(ListNode.headA, ListNode.headB));
        System.out.println(intersectionNodeB(ListNode.headA, null));
        
        System.out.println("---");
        System.out.println(intersectionNodeC(ListNode.headA, ListNode.headB));
        System.out.println(intersectionNodeC(ListNode.headA, null));   
    }

    /**
     * 方法3
     * @param headA
     * @param headB
     * @return
     */
    private static ListNode intersectionNodeC(ListNode headA, ListNode headB){
        ListNode p1 = headA;
        ListNode p2 = headB;
        while(p1 != p2){
            if(p1 == null){
                p1 = headB;
            }else{
                p1 = p1.next;
            }

            if(p2 == null){
                p2 = headA;
            }else{
                p2 = p2.next;
            }
        }

        return p1;
    }


    /**
     * 方法2
     * @param headA
     * @param headB
     * @return
     */
    private static ListNode intersectionNodeB(ListNode headA, ListNode headB){
        Set<ListNode> setA = new HashSet<>();
        
        for(ListNode tmp = headA; tmp != null; tmp = tmp.next){
            setA.add(tmp);
        }

        for(ListNode tmp = headB; tmp != null; tmp = tmp.next){
            if(setA.contains(tmp)){
                return tmp;
            }
        }

        return null;
    }


    /**
     * 方法1，计算长度，然后找到相交节点
     * @param headA
     * @param headB
     * @return
     */
    private static ListNode intersectionNode(ListNode headA, ListNode headB){
        int lenA = 0;
        for(ListNode tmp = headA; tmp != null; tmp = tmp.next){
            lenA++;
        }
        System.out.println("listNode A length=" + lenA);

        int lenB = 0;
        for(ListNode tmp = headB; tmp != null; tmp = tmp.next){
            lenB++;
        }
        System.out.println("listNode B length=" + lenB);

        ListNode p1 = headA;
        ListNode p2 = headB;
        if(lenA > lenB){
            for(int i=0; i < lenA - lenB; i++){
                p1 = p1.next;
            }
        }else{
            for(int i = 0; i < lenB - lenA; i++){
                p2 = p2.next;
            }
        }

        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
