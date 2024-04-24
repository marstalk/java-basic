package dp;

/**
 * 小明和朋友玩跳格子游戏，有 n 个连续格子，每个格子有不同的分数，小朋友可以选择以任意格子起跳，但是不能跳连续的格子，也不能回头跳；求跳完之后获得的最大的分数。
 * 1 2 3 1 -> 4
 * 2 7 9 3 1 -> 12
 * 打家劫舍完全一致。
 */
public class IIJumpGridGame2DP {
    public static void main(String[] args) {
        IIJumpGridGame2DP jumpGridGame = new IIJumpGridGame2DP();
        System.out.println(jumpGridGame.jump(new int[]{1, 2, 3, 1}));//4
        System.out.println(jumpGridGame.jump(new int[]{2, 7, 9, 3, 1}));// 12
    }

    /**
     * dp(i)表示nums[0,i]格子获取的最大的分数。
     * 那么dp(i) = Math.max(nums[i] + dp(i-2), dp(i-1))
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
