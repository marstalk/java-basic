package random;

import java.util.*;

/**
 * 加权随机。
 */
public class WeightRandom {
    public static void main(String[] args) {
        List<Integer> weights = new ArrayList<>();
        weights.add(2);
        weights.add(3);
        weights.add(5);

        WeightRandom weightRandom = new WeightRandom();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int random = weightRandom.random(weights);
            map.put(random, map.getOrDefault(random, 0) + 1);
        }

        System.out.println(map);
    }


    public int random(List<Integer> weights) {
        if (weights.isEmpty()) {
            return -1;
        }

        if (weights.size() == 1) {
            return weights.get(0);
        }

        List<Integer> weightTmp = new ArrayList<>();
        weightTmp.add(0);

        int weight = 0;
        for (Integer integer : weights) {
            weight += integer;
            weightTmp.add(weight);
        }
        Random random = new Random();
        // 随机数区间[0, weight)
        int target = random.nextInt(weight);

        int index = 0;
        for (int i = weightTmp.size() - 1; i >= 0; i--) {
            if (target >= weightTmp.get(i)) {
                index = i;
                break;
            }
        }

        return weights.get(index);
    }
}
