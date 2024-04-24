package dp;

import java.util.Arrays;

/**
 * 想到的第一个方法是穷举。关键是如何穷举完所有的情况。但是穷举的时候，会有冗余计算。于是版本2，看看如何优化这些冗余计算。
 * <p>
 * 使用自上而下的dp，dp(i, j) = min{dp(i+1, nextJ) | nextJ belongs to {j-1, j, j+1}} + matrix[i][j],
 * 即使使用了自上而下，但是仍然存在冗余计算。
 * 优化方法1， memo[][]， memo[i][j] 表示以它为起点的最小的路径之和。
 * 优化方法2， 自下而上
 */
public class HHMinFallingPathSum3DpWithMemo {
    private final int[] directions = new int[]{-1, 0, 1};

    public static void main(String[] args) {
        HHMinFallingPathSum3DpWithMemo minFallingPathSum = new HHMinFallingPathSum3DpWithMemo();
        System.out.println(minFallingPathSum.minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));
        System.out.println(minFallingPathSum.minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}}));
    }

    public int minFallingPathSum(int[][] matrix) {
        int res = Integer.MAX_VALUE;
        int n = matrix.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int col = 0; col < n; col++) {
            res = Math.min(dp(matrix, 0, col, memo), res);
        }
        return res;
    }

    //使用自上而下的dp，dp(i, j) = min{dp(i+1, nextJ) | nextJ belongs to {j-1, j, j+1}} + matrix[i][j],
    private int dp(int[][] matrix, int row, int col, int[][] memo) {
        if (row > matrix.length - 1 || col < 0 || col > matrix.length - 1) {
            return Integer.MAX_VALUE;
        }

        if (memo[row][col] != Integer.MAX_VALUE) {
            return memo[row][col];
        }

        int res = Integer.MAX_VALUE;
        for (int direction : directions) {
            int nextRow = row + 1;
            int nextCol = col + direction;
            res = Math.min(res, dp(matrix, nextRow, nextCol, memo));
        }

        res = Integer.MAX_VALUE == res ? 0 : res;
        int rtn = res + matrix[row][col];
        memo[row][col] = rtn;
        return rtn;
    }
}
