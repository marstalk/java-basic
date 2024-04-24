package array;

/**
 * 构建螺旋n*n矩阵（由外到内）
 */
public class GenerateSpiralMatrix {
    public static void main(String[] args) {
        GenerateSpiralMatrix generateSpiralMatrix = new GenerateSpiralMatrix();
        int[][] matrix = generateSpiralMatrix.generateMatrix(3);
        generateSpiralMatrix.print(matrix);

        matrix = generateSpiralMatrix.generateMatrix(5);
        generateSpiralMatrix.print(matrix);
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int upperBound = 0;
        int lowerBound = n - 1;
        int leftBound = 0;
        int rightBound = n - 1;

        int num = 1;

        while (num <= n * n) {
            if (upperBound <= lowerBound) {
                for (int i = leftBound; i <= rightBound; i++) {
                    matrix[upperBound][i] = num++;
                }
                upperBound++;
            }

            if (rightBound >= leftBound) {
                for (int i = upperBound; i <= lowerBound; i++) {
                    matrix[i][rightBound] = num++;
                }
                rightBound--;
            }

            if (lowerBound >= upperBound) {
                for (int i = rightBound; i >= leftBound; i--) {
                    matrix[lowerBound][i] = num++;
                }
                lowerBound--;
            }

            if (leftBound <= rightBound) {
                for (int i = lowerBound; i >= upperBound; i--) {
                    matrix[i][leftBound] = num++;
                }
                leftBound++;
            }
        }

        return matrix;
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
