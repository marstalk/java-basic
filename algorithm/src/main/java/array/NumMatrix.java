package array;

/**
 * TODO 二维数组差点走火入魔
 */
public class NumMatrix {
    private int[][] regionSum;

    public NumMatrix(int[][] matrix) {
        // regionSum[i][j]表示[0][0] ~ [i - 1][j - 1]区域和
        int m = matrix.length;
        int n = matrix[0].length;
        regionSum = new int[m + 1][n + 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                regionSum[i][j] = matrix[i][j] + matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {

        return -1;
    }


    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(null);
        System.out.println(numMatrix.sumRegion(3, 4, 5, 6));
    }
}
