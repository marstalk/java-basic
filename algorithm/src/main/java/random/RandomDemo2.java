package random;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class RandomDemo2 {
    public static void main(String[] args) {
        Random random = new Random(1);
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < 20000; i++) {
            // 在[0, 5)区间内等概率随机返回一个数。
            int val = random.nextInt(5);
            count.put(val, count.getOrDefault(val, 0) + 1);
        }
        for (Entry<Integer, Integer> entry : count.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        
        // 1%的概率打印日志：
        System.out.println("1%");
        for (int i = 0; i < 1000; i++) {
            if(i % 100 < 1){
                System.out.println(i);
            }
        }

        // 13%的概率打印日志
        System.out.println("13%");
        for (int i = 0; i < 1000; i++) {
            if(i % 100 < 13){
                System.out.println(i);
            }
        }

        double x = Math.random();
    }
}
