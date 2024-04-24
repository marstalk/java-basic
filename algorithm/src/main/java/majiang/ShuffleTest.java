package majiang;

import java.util.HashMap;
import java.util.Map;

public class ShuffleTest {
    private static Map<Pai, Integer> countMap = new HashMap<>();

    public static void main(String[] args) {
        Pai yiwan = new Pai(0, 1);
        //蒙特卡洛验证xiPai2
        for (int i = 0; i < 100000; i++) {
            Majiang majiang = new Majiang();
            majiang.xiPai2();

            countMap.put(majiang.pais[0], countMap.getOrDefault(majiang.pais[0], 0) + 1);
        }

        for(Pai pai: countMap.keySet()){
            System.out.println(pai + " " + countMap.get(pai));
        }
    }

    private static int findPosition(Pai[] pais, Pai pai) {
        for (int i = 0; i < pais.length; i++) {
            if(pais[i].equals(pai)){
                return i;
            }
        }
        return 0;
    }
}
