package array;

/**
 * 顺时针旋转90°正方形矩阵，要求原地旋转
 * 
 */
public class RotateMatrix {
    public static void main(String[] args) {
        RotateMatrix rotateMatrix = new RotateMatrix();
        int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        rotateMatrix.print(matrix);
        rotateMatrix.rotateMatrix(matrix);
        rotateMatrix.print(matrix);

        matrix = new int[][] { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
        rotateMatrix.print(matrix);
        rotateMatrix.rotateMatrix(matrix);
        rotateMatrix.print(matrix);
    }

    /**
     * 1. 镜像
     * 2. 每行反转
     * TODO，这个方法内存消耗排名很低，可以改进下
     * @param matrix
     */
    void rotateMatrix(int[][] matrix) {
        // 对列>行的数据进行交换
        for (int row = 0; row < matrix.length; row++) {
            // 使用int col = row + 1表示列>行
            for (int col = row + 1; col < matrix[row].length; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }

        // 对每一行进行反转操作
        for (int row = 0; row < matrix.length; row++) {
            reverse(matrix[row]);
        }
    }

    /**
     * 反转数组
     * 
     * @param row
     */
    private void reverse(int[] row) {
        int l = 0, r = row.length - 1;
        while (l < r) {
            int tmp = row[l];
            row[l] = row[r];
            row[r] = tmp;
            l++;
            r--;
        }
    }

    void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
