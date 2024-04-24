package majiang;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ShouPai {
    private int id;
    private int paiId;
    int cnt;
    boolean isHu;
    boolean autoPlay = true;
    int dingque = -1;
    Set<Pai> set = new HashSet<>();

    Pai penggang1 = new Pai(0, -1);
    Pai penggang2 = new Pai(0, -1);
    Pai penggang3 = new Pai(0, -1);

    LinkedList<Pai> orderedWang = new LinkedList<>();
    LinkedList<Pai> orderedTong = new LinkedList<>();
    LinkedList<Pai> orderedTiao = new LinkedList<>();

    public ShouPai(int id) {
        this.id = id;
        this.paiId = 1;
    }

    public void qishou(Pai pai) {
        add(pai);
    }

    private void add(Pai pai) {
        pai.next = null;
        pai.pre = null;
        pai.id = paiId++;
        set.add(pai);
        switch (pai.typeInt) {
            case 0:
                insert(orderedWang, pai);
                break;
            case 1:
                insert(orderedTong, pai);
                break;
            case 2:
                insert(orderedTiao, pai);
                break;
        }
    }
    private void insert(LinkedList<Pai> list, Pai pai){
        if(list.size() == 0){
            list.addFirst(pai);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).val > pai.val){
                list.add(i, pai);
                return;
            }
        }

        list.addLast(pai);
    }

    /**
     * 摸牌，包括碰、摸、杠摸
     * 
     * @param pai
     */
    public void mo(Pai pai) {
        add(pai);
        System.out.println(id + "摸牌 >>>>> 摸到：" + pai.toString());
        // 摸牌
        checkHu(pai);
        // print();
    }

    public void dingque() {
        if (autoPlay) {
            // 牌数最少的定缺。
            int wangCnt = orderedWang.size();
            int tongCnt = orderedTong.size();
            int tiaoCnt = orderedTiao.size();
            
            if(wangCnt < tongCnt){
                if(wangCnt < tiaoCnt){
                    dingque = 0;
                }else{
                    dingque = 2;
                }
            }else{
                if(tongCnt < tiaoCnt){
                    dingque = 1;
                }else{
                    dingque = 2;
                }
            }
            System.out.println("定什么缺？0萬，1同，2条：");
            try {
                Thread.currentThread().sleep(800);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("定缺：" + dingque);
            return;
        }

        System.out.print("定什么缺？0萬，1同，2条：");
        Scanner scan = new Scanner(System.in);
        Integer dingque = Integer.valueOf(scan.nextLine());
        System.out.println("定缺：" + dingque);
        this.dingque = dingque;

    }

    /**
     * TODO 想了一天还是没有整明白怎么检查是否胡牌。
     */
    public void checkHu(Pai pai) {
        // 普通

        // 大对子 2翻

        // 清一色 2翻

        // 小对子 或者龙对子

        // 几个杠
    }

    /**
     * 出牌。
     * 
     * @return
     */
    public Pai chu() {
        this.print();
        System.out.print("打哪张牌？输入下标：");
        Scanner scan = new Scanner(System.in);
        String inputString = scan.nextLine();

        int paiId = Integer.valueOf(inputString);
        Pai chuPai = null;
        for (Pai pai : set) {
            if (pai.id == paiId) {
                chuPai = pai;
                orderedTiao.remove(pai);
                orderedWang.remove(pai);
                orderedTong.remove(pai);
            }
        }
        System.out.println("【出牌：" + chuPai.toString() + "】");
        return chuPai;
    }

    /**
     * 胡牌
     */
    public boolean isHu() {
        return this.isHu;
    }

    public void print() {
        System.out.println(id + "手牌 >>>>>：" + "定缺（0萬，1同，2条）" + dingque);
        System.out.print("碰杠：");
        print(penggang1);
        print(penggang2);
        print(penggang3);
        System.out.println();

        System.out.print("其他： ");
        printList(orderedWang);
        printList(orderedTong);
        printList(orderedTiao);
        System.out.println();

        System.out.print("带ID：");
        print2(orderedWang);
        print2(orderedTong);
        print2(orderedTiao);

        System.out.println("");

    }

    private void printList(List<Pai> list) {
        for (Pai pai: list) {
            System.out.print(pai.toStringWithoutId());
        }
        System.out.print(" ");
    }

    private void print(Pai pai) {
        Pai p = pai.next;
        while (p != null) {
            System.out.print(p.toStringWithoutId());
            p = p.next;
        }
        System.out.print(" ");
    }

    private void print2(List<Pai> list) {
        for (Pai pai: list) {
            System.out.print(pai.toString());
        }
        System.out.print(" ");
    }

}
