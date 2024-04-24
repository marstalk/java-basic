package dp;

import java.util.Arrays;

/**
 * 优化方法, 自下而上，空间复杂度从O(n平方)降为O(n)
 */
public class HHMinFallingPathSum4DpBottomsUp {
    private final int[] directions = new int[]{-1, 0, 1};

    public static void main(String[] args) {
        HHMinFallingPathSum4DpBottomsUp minFallingPathSum = new HHMinFallingPathSum4DpBottomsUp();
        System.out.println(minFallingPathSum.minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}})); // 13
        System.out.println(minFallingPathSum.minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}})); // -59
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] preRow = new int[n];
        Arrays.fill(preRow, 0);

        int[] currentRow;
        for (int row = n - 1; row >= 0; row--) {
            currentRow = new int[n];
            // bottoms-up
            for (int col = 0; col < n; col++) {
                // left to right
                int min = Integer.MAX_VALUE;
                for (int direction : directions) {
                    int preCol = col + direction;
                    if (preCol > -1 && preCol < n) {
                        min = Math.min(min, preRow[preCol]);
                    }
                }
                currentRow[col] = matrix[row][col] + min;
            }
            preRow = currentRow;
        }

        int res = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            res = Math.min(res, preRow[col]);
        }
        return res;
    }
}
