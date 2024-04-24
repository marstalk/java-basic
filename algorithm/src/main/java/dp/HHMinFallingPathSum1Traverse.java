package dp;

/**
 * 想到的第一个方法是穷举。关键是如何穷举完所有的情况。自上而下的遍历整个选择树。
 */
public class HHMinFallingPathSum1Traverse {
    private final int[] directions = new int[]{-1, 0, 1};
    private int res;

    public static void main(String[] args) {
        HHMinFallingPathSum1Traverse minFallingPathSum = new HHMinFallingPathSum1Traverse();
        System.out.println(minFallingPathSum.minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));
        System.out.println(minFallingPathSum.minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}}));
    }

    public int minFallingPathSum(int[][] matrix) {
        res = Integer.MAX_VALUE;
        for (int col = 0; col < matrix.length; col++) {
            traverse(matrix, 0, col, matrix[0][col]);
        }
        return res;
    }

    private void traverse(int[][] matrix, int row, int col, int currentSum) {
        if (row == matrix.length - 1) {
            // base case, reach the last row.
            res = Math.min(res, currentSum);
            return;
        }

        for (int direction : directions) {
            int nextCol = col + direction;
            if (nextCol < 0 || nextCol > matrix.length - 1) {
                // cross the boarder, skip
                continue;
            }
            traverse(matrix, row + 1, nextCol, currentSum + matrix[row + 1][nextCol]);
        }

    }
}
