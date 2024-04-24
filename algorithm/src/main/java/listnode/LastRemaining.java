package listnode;

/**
 * 约瑟夫问题。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，
 * 从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 */
public class LastRemaining {
    public static void main(String[] args) {
        LastRemaining lastRemaining = new LastRemaining();
        System.out.println(lastRemaining.lastRemaining(5, 3));//2,0,4,1, remaind: 3
        System.out.println(lastRemaining.lastRemaining(10, 17));// 2
        System.out.println(lastRemaining.lastRemaining(5, 1));// 4
    }

    /**
     * 
     * @param n 0,1,2,3,4....,n
     * @param m 每隔m个
     * @return
     */
    public int lastRemaining(int n, int m){
        if(n == 1) return 1;
        if(m == 1) return n-1;

        //创建循环链表
        Node head = new Node(0);
        Node pre = head;
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(i);
            pre.next = newNode;
            pre = pre.next;
        }
        pre.next = head;
        //head.print();
        
        Node cur = pre; //指向tail
        System.out.println("tail " + cur.val);
        pre = null;
        while(cur.next.val != cur.val){
            int x = 0;
            while(x < m){ // 为什么是2？1）cur本身就是1个了；2）只需要跳到被删的元素的上一个即可。
                x++;
                pre = cur;
                cur = cur.next;
            }
            System.out.println("delete " + cur.val);
            pre.next = pre.next.next;
        }

        return pre.val;

    }

    class Node{
        int val;
        Node next;
        public Node(int i){
            this.val = i;
        }

        void print(){ 
            Node head = this;
            System.out.print(head.val + " " );
            Node cur = head.next;
            while(cur.val != head.val){
                System.out.print(cur.val + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }
    
}
