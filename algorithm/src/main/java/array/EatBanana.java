package array;

/**
 * 根据前面提到的泛化思想，我们需要找到几个关键因素：自变量x，单调函数f(x)，target分别是什么？
 * 1. 一般来说，题目要求什么，那什么就是自变量，这里求的是速度k，那么自变量x就表示速度。
 * 2. 单调函数f(x)是什么呢？是珂珂吃完所有香蕉需要的时间，这个函数是单调递减的。
 */
public class EatBanana {
    public static void main(String[] args) {
        EatBanana eatBanana = new EatBanana();

        //piles = [30,11,23,4,20], h = 5
        System.out.println(eatBanana.minEatingSpeed(new int[]{30,11,23,4,20}, 5));

        System.out.println(eatBanana.minEatingSpeed(new int[]{3,6,7,11}, 8));
    }

    public int minEatingSpeed(int[] piles, int targetHours) {
        if(null == piles || piles.length == 0){
            return -1;
        }
        // 最小的，有意义的速度应该是1
        int minSpeed = 1;
        // 最大的，有意义的速度应该是piles中的最大值，因为规定了一个小时最多只能吃一堆，速度太快了也白搭。
        int maxSpeed = 1000000000 + 1;

        // 左闭右开
        while(minSpeed < maxSpeed){
            int midSpeed = minSpeed + (maxSpeed - minSpeed) / 2;
            int currentHours = hoursBySpeed(piles, midSpeed);
            if(currentHours == targetHours){
                maxSpeed = midSpeed;
            }else if(currentHours < targetHours){
                maxSpeed = midSpeed;
            }else {
                minSpeed = midSpeed + 1;
            }
        }

        return minSpeed;
    }

    private int hoursBySpeed(int[] piles, int speed){
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            // 按照速度吃香蕉。
            hours += piles[i] / speed;

            // 剩下的香蕉不需要1个小时吃完，但是也算作一个小时。因为” 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  “
            if(piles[i] % speed > 0){
                hours++;
            }
        }
        return hours;
    }

}
