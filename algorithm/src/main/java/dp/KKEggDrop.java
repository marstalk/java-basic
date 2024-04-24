package dp;

import java.util.HashMap;
import java.util.Map;

public class KKEggDrop {
    public static void main(String[] args) {
        KKEggDrop eggDrop = new KKEggDrop();
        System.out.println(eggDrop.dp(1, 2)); //2
        System.out.println(eggDrop.dp(2, 6)); //3
        System.out.println(eggDrop.dp(3, 14)); //4
    }

    Map<String, Integer> mem = new HashMap<>();
    public int dp(int k, int n){
        if(k == 1){
            return n;
        }
        if(n == 0){
            return 0;
        }

        if(mem.containsKey(k + "," + n)){
            return mem.get(k + "," + n);
        }

        int res = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++){
            res = Math.min(res, Math.max(
                                    dp(k -1, i -1), // 鸡蛋碎了，鸡蛋-1，在较矮的楼层继续尝试
                                    dp(k, n - i) // 鸡蛋没有碎，在较高的楼层继续测试。
                                ) + 1);
        }
        
        mem.put(k + "," + n, res);
        return res;
    }
}
