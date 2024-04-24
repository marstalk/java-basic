package dp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * The answer is guaranteed to fit into a signed 32-bit integer.
 */
public class KMCoinChange {
    public static void main(String[] args) {
        KMCoinChange kmCoinChange = new KMCoinChange();
        System.out.println(kmCoinChange.change(new int[]{1, 2, 5}, 5)); // 4;
    }

    private Set<String> rtn;

    /**
     * 存在重复计算问题。
     * @param coins
     * @param amount
     * @return
     */
    public int change(int[] coins, int amount) {
        rtn = new HashSet<>();
        LinkedList<Integer> path = new LinkedList<>();
        for (int i : coins) {
            backtracking(coins, amount, path);
        }
        return rtn.size();
    }

    private void backtracking(int[] coins, int amount, LinkedList<Integer> path) {
        if (amount < 0) {
            return;
        }
        if (amount == 0) {
            rtn.add(path.stream().sorted().map(String::valueOf).reduce("", (a, b) -> a + "," + b));
            return;
        }

        for (int i : coins) {
            path.addLast(i);
            backtracking(coins, amount - i, path);
            path.removeLast();
        }
    }
}
