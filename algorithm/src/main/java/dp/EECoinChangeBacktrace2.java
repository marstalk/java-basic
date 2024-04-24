package dp;

/**
 * 使用回溯进行 组合（可重复选），效率较低。
 * 3
2
2
17
24
 */
public class EECoinChangeBacktrace2 {
    public static void main(String[] args) {
        EECoinChangeBacktrace2 coinChangeBacktrace = new EECoinChangeBacktrace2();
        System.out.println(coinChangeBacktrace.coinChange(new int[] { 1, 2, 5 },11));
        System.out.println(coinChangeBacktrace.coinChange(new int[] { 2 }, 3));
        System.out.println(coinChangeBacktrace.coinChange(new int[] { 1, 2147483647}, 2));
        System.out.println(coinChangeBacktrace.coinChange(new int[] { 186, 419, 83,408 }, 6249));
        System.out.println(coinChangeBacktrace.coinChange(new int[] { 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422 }, 9864));
    }

    private int res;
    public int coinChange(int[] coins, int amount) {
        res = Integer.MAX_VALUE;
        if (amount <= 0) {
            return 0;
        }
        backtrace(coins, amount, 0, 0, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 
     * @param coins 当前备选项
     * @param amount 目标路径
     * @param cur 当前路径
     * @param start 当前备选项
     * @param cnt 当前路径
     */
    private void backtrace(int[] coins, int amount, int cur, int start, int cnt) {
        if (cur > amount) {
            return;
        }
        if (cur == amount) {
            res = cnt < res ? cnt : res;
            return;
        }

        for (int i = start; i < coins.length; i++) {
            cur += coins[i];
            cnt++;
            backtrace(coins, amount, cur, i, cnt);
            cnt--;
            cur -= coins[i];
        }
    }

}
