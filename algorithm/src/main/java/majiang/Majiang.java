package majiang;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Majiang {
    Pai head;
    Pai tail;
    int restCnt;
    static int total = 3 * 9 * 4; // 108

    /**
     * 单向环有序麻将。
     */
    Pai[] pais = new Pai[3 * 9 * 4];

    public Majiang(){
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int j2 = 0; j2 < 4; j2++) {
                    Pai p = new Pai(i, j);
                    pais[x++] = p;
                }
            }
        }

        // 构成一个单向环，用于洗牌
        int i = 0;
        while(i < total - 1){
             pais[i].next = pais[i + 1];
             i++;
        }
        pais[3 * 9 * 4 - 1].next = pais[0];

        for (int j = 0; j < total; j++) {
            Pai current = pais[j];
            //System.out.println(current.val + current.type + " " + current.isUsed() + " -> " + current.next.val + current.next.type);
        }

    }



    public void xiPai2(){
        //给定一个数组，使用洗牌算法随机打散。
        Random random = new Random();
        int n = pais.length;
        for(int i = 0; i < n; i++){
            int selected = i + random.nextInt(n - i);
            //swap
            Pai x = pais[i];
            pais[i] = pais[selected];
            pais[selected] = x;
        }
    }

    /**
     * 洗牌，有点儿意思。打散
     */
    public void xiPai(){
        this.head = null;
        this.tail = null;
        Random random = new Random();
        head = new Pai(0, -1);
        Pai p = head;
        for (int i = 0; i < 3 * 9 * 4; i++) {
            int next = random.nextInt(3 * 9 * 4);
            Pai hit = pais[next];// 这张牌有可能已经被洗过了，
            while(hit.isUsed()){ // 顺时针往下找尚未使用的麻将
                //System.out.println(hit.val + hit.type + "已经使用过，往下走"); // TODO 优化，不够随机
                hit = hit.next;
            }
            //System.out.println(hit.val + hit.type + "标记为 已使用");
            hit.setUsed(true); //标记为使用过。

            Pai pai = new Pai(hit.typeInt, hit.val); // 创建新的麻将，避免破坏pais麻将环
            tail = pai;

            p.next = pai;
            p.next.pre = p;

            p = p.next;
            restCnt++;
        }

        head = head.next;
    }

    public void print(){
        if(head == null){
            System.out.println("尚未洗牌");
            return;
        }
        System.out.print("剩余" + restCnt + "张牌：");
        Pai p = head;
        while(p != null){
            System.out.print(p.val + "" + p.type + " > " );

            p = p.next;
        }
        System.out.println(" ");
        System.out.println(" ");
    }

    public Pai next(){
        Pai pai = head;
        head = head.next;
        // 摸牌之后，断链
        pai.next = null;
        pai.pre = null;

        restCnt--;
        return pai;
    }
    
}
