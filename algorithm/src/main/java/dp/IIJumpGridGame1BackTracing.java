package dp;

import java.util.LinkedList;

/**
 * 小明和朋友玩跳格子游戏，有 n 个连续格子，每个格子有不同的分数，小朋友可以选择以任意格子起跳，但是不能跳连续的格子，也不能回头跳；
 * 1 2 3 1 -> 4
 * 2 7 9 3 1 -> 12
 * 打家劫舍完全一致。
 *
 * 方法1. 遍历所有的情况，选择最大值。缺点是存在重复计算。
 */
public class IIJumpGridGame1BackTracing {
    public static void main(String[] args) {
        IIJumpGridGame1BackTracing jumpGridGame = new IIJumpGridGame1BackTracing();
        System.out.println(jumpGridGame.jump(new int[]{1, 2, 3, 1}));//4
        System.out.println(jumpGridGame.jump(new int[]{2, 7, 9, 3, 1}));// 12
    }

    int res;

    public int jump(int[] nums) {
        res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            LinkedList<Integer> path = new LinkedList<>();
            path.addLast(nums[i]);
            backtrace(nums, i, path);
        }
        return res;
    }

    private void backtrace(int[] nums, int i, LinkedList<Integer> path) {
        if (i + 2 >= nums.length) {
            // next to be chosen index is outbound. so over here.
            int sum = 0;
            for (int selected : path) {
                sum += selected;
            }
            res = Math.max(res, sum);
            System.out.println(path);
            return;
        }

        for (int j = i + 2; j < nums.length; j++) {
            path.addLast(nums[j]);
            backtrace(nums, j, path);
            path.removeLast();
        }
    }
}
