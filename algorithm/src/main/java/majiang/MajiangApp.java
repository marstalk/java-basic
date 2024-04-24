package majiang;

import java.util.Scanner;

/**
 * 教训1：一定要提前设计好API，不要一上来就写代码，一旦产生返工后果很严重。工程化
 * 教训2：能不能设计好一个数据结构（优先队列）：
 *      queue.size(), queue.poll()返回的是无关性最高的牌（比如定缺），牌面很少的牌。根据牌面计算出赢的概率最小的牌等（已经被碰或者杠过的牌或者已经出了很多的牌），边张等等。
 * 教训3：每张牌都有ID，如何设计一个ID，使得ID能够包含很多信息，比如可以根据ID计算出几万几同几条，type1，2，3 做百分位，value1~9做十分位，个位表示1~4。
 * 教训4：当前洗牌算法不够随机。Collections.shuffle(list)，Collections.frequency(list, target)计算元素个数。
 * 教训5：怎么判定一手牌是否胡牌，几翻？
 * 教训6：如何持久化当前牌局？
 * 教训7：如何联网？
 */
public class MajiangApp {
    public static Scanner sc = new Scanner(System.in);
    // sc.nextLine();

    static int lastWinner = 2; // 上一局的赢家
    public static void main(String[] args) {
        Majiang majiang = new Majiang();
        majiang.xiPai();
        majiang.print();

        int playerCnt = 4;
        ShouPai[] shouPais = new ShouPai[playerCnt];
        for (int i = 0; i < playerCnt; i++) {
            shouPais[i] = new ShouPai(i);
        }

        // 设置人工玩家
        shouPais[2].autoPlay = false;

        // 不用掷骰子，总是从head开始摸牌。
        // 1 起手，每个人拿12张。从上一个winner开始拿
        int index = lastWinner;
        while(majiang.restCnt > (Majiang.total - playerCnt * 12)){
            index = index % 4;
            ShouPai shouPai = shouPais[index];
            for(int i = 0; i < 4; i++){
                shouPai.qishou(majiang.next());
            }
            index ++;
        }

        // 打印手牌情况
        for (int i = 0; i < playerCnt; i++) {
            shouPais[i].print();
        }

        // 2 定缺
        System.out.println("-------------------");
        System.out.println("---  开始定缺  -----");
        System.out.println("-------------------");
        index = lastWinner;
        for (int i = 0; i < playerCnt; i++) {
            System.out.println(">>>>>> 下家 >>>>>>>");
            index = index % 4;
            shouPais[index].print();
            shouPais[index].dingque();
            index++;
        }


        System.out.println("-------------------");
        System.out.println("---  开始摸牌  -----");
        System.out.println("-------------------");
        // 2 摸牌
        index = lastWinner;
        int restPlayer = playerCnt;
        while(majiang.restCnt > 0 && restPlayer > 1){
            System.out.println(">>>>>> 下家 >>>>>>>");
            index = index % 4;
            ShouPai shouPai = shouPais[index++];
            if(shouPai.isHu){
                continue;
            }
            shouPai.mo(majiang.next()); // 摸一张牌
            // shouPai.checkHu();
            Pai pai = shouPai.chu();


            // TODO restPlay--;
        }
    }
}


