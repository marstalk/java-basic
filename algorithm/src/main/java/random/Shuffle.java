package random;
import java.util.Random;

/**
 * 洗牌算法，有十个数，一个一个的取，使得每个数被取到的概率是一样的，即1/10 [基本思路类似选择排序，只不过选择的并不是最小值，而是等概率随机选]
 * 有一个数组，放着10个数。
 * 一个随机数random(0,10)，在10个数中，等概率（1/10）获得任一个数x，x的概率是 1/10
 * 在剩下的9个数中，随机random(0,9)，等概率（1/9）获得任一个数y，y的概率其实是9/10 * 1/9 = 1/10
 * 在剩下的8个数中，随机random(0,8)，等概率（1/8）获得任一个数z，z的概率其实是9/10 * 8/9 * 1/8 = 1/10
 * 以此类推。
 * 
 * 随机算法在用在游戏洗牌，负载均衡（比如eureka中对endpoint进行洗牌），
 */
public class Shuffle {
    /**
     * 从代码层面来解释的话，应该是这样子的：
     * n个数全排列，有多少种可能：是不是n * (n-1) * n(-2) ... 1 = n!
     * 为什么下面的代码，使用随机数的方式，实现了n! ？
     * 对于index=0的位置来说，在[0~n)之间随机选择一个数，并放置在index=0位置上，是不是有n种可能？
     * 对于index=1的位置来说，在[1~n)之间随机选择一个数，并放置在index=1位置上，是不是有n-1种可能？
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 
                                4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 
                                7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9 };
        
        Random rnd = new Random(); 
        int length = arr.length;
        for(int i = 0; i < length; i++){
            int j = i + rnd.nextInt(length - 1);
            
            //swap, 
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        for(int i: arr){
            System.out.print(i + " ") ;
        }
    }
}