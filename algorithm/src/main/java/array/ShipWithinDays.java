package array;

/**
 * 1. 题目求运载能力，那么假设运载能力为x，取值范围是[1, sum(weights)]，一次可以装载全部的货物。
 * 2. 函数的返回值是days，且days是随着x的增大而减少。
 */
public class ShipWithinDays {

    public static void main(String[] args) {
        ShipWithinDays shipWithinDays = new ShipWithinDays();
        System.out.println(shipWithinDays.shipWithinDays(new int[]{3,2,2,4,1,4}, 3)); // 6
        System.out.println(shipWithinDays.shipWithinDays(new int[]{1,2,3,1,1}, 4)); // 3
    }

    public int shipWithinDays(int[] weights, int days) {
        int minCapability = 0;
        int maxCapability = 1;
        // days的取值范围是[1, weights.length]，即[一次装完，每天装一个]
        for (int i = 0; i < weights.length; i++) {
            // 货船最大的容量是无限大，但其实没有必要，因为只需要一次装载完所有的货物即可。
            maxCapability += weights[i];
            // 如果货船有部分物品装不下，那么days就是无限大，所以货船至少能装下最大的哪个货物。
            minCapability = Math.max(minCapability, weights[i]);
        }

        // 左闭右开
        while (minCapability < maxCapability) {
            int mid = minCapability + (maxCapability - minCapability) / 2;
            if (days >= daysByWeight(weights, mid)) {
                maxCapability = mid;
            } else {
                minCapability = mid + 1;
            }
        }
        return minCapability;
    }

    /**
     * 根据能力计算出所需的天数。
     * 
     * @param weights
     * @param capability
     * @return
     */
    private int daysByWeight(int[] weights, int capability) {
        int days = 0;
        for (int i = 0; i < weights.length;) {
            // 每个循环是一天的运输，船的容量是初始容量。
            int leftCapability = capability;
            while (i < weights.length && leftCapability >= weights[i]) {
                // 如果接下来的货物能装得下，那么则装上，容量减小。
                leftCapability = leftCapability - weights[i];
                // 货物已经装下了，所以i++
                i++;
            }
            // 天数+1
            days++;
        }
        return days;
    }
}
