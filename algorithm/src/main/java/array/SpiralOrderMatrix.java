package array;

import java.util.LinkedList;
import java.util.List;

/**
 * 螺旋遍历矩阵
 */
public class SpiralOrderMatrix {
    public static void main(String[] args) {
        SpiralOrderMatrix spiralOrderMatrix = new SpiralOrderMatrix();
        spiralOrderMatrix.spiralOrder(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } }).stream()
                .forEach(System.out::println);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        List<Integer> rtn = new LinkedList<>();

        int upperBound = 0;
        int lowerBound = m - 1;
        int leftBound = 0;
        int rightBound = n - 1;

        while (rtn.size() < m * n) {
            if (upperBound <= lowerBound) {
                // 上，从leftBound到rightBound
                for (int i = leftBound; i <= rightBound; i++) {
                    rtn.add(matrix[upperBound][i]);
                }
                upperBound++;
            }

            if (rightBound >= leftBound) {
                // 右，从upperBound到lowerBound
                for (int i = upperBound; i <= lowerBound; i++) {
                    rtn.add(matrix[i][rightBound]);
                }
                rightBound--;
            }

            if (upperBound <= lowerBound) {
                // 下，从右往左，遍历lowerBound这一行
                for (int i = rightBound; i >= leftBound; i--) {
                    rtn.add(matrix[lowerBound][i]);
                }
                lowerBound--;
            }

            if (leftBound <= rightBound) {
                // 左，从下往上，遍历leftBound这一列
                for (int i = lowerBound; i >= upperBound; i--) {
                    rtn.add(matrix[i][leftBound]);
                }
                leftBound++;
            }
        }

        return rtn;
    }

}
