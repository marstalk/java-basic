package backtracing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目二：中庸行者
 * 给定一个m*n的整数阵作为地图，短阵数值为地形高度；
 * 中庸行者选择地图中的任意一点作为起点，尝试往上、下、左、右四个相邻格子移动;
 * 移动时有如下约束：
 * 中庸行者只能上坡或者下坡，不能走到高度相同的点。
 * 不允许连续上坡或者连续下坡，需要交替进行;
 * 每个位置只能经过一次，不能重复行走；
 * 请给出中庸行者在本地图内，能连续移动的最大次数。
 * <p>
 * 第一行两个数字，分别为行数和每行的列数
 * 后续数据为矩阵地图内容
 * 矩阵边长范围：[1,8]
 * 地形高度范围：[0,100000]
 * <p>
 * 2 2
 * 1 2
 * 4 3
 * -> 3
 * <p>
 * 3->4->1->2，一共移动3次。
 */
public class MaximumStepsInGrid {
    private int maximumStep = 0;
    private LinkedList<Integer> finalPath;
    private static final int[][] directions;

    static {
        directions = new int[4][2];
        directions[0] = new int[]{0, 1};
        directions[1] = new int[]{0, -1};
        directions[2] = new int[]{1, 0};
        directions[3] = new int[]{-1, 0};
    }

    public static void main(String[] args) {
        MaximumStepsInGrid maximumStepsInGrid = new MaximumStepsInGrid();
        System.out.println(maximumStepsInGrid.maximumSteps(2, 2, new int[][]{{1, 2}, {4, 3}}));
        System.out.println(maximumStepsInGrid.maximumSteps(2, 2, new int[][]{{1, 2}, {1, 2}}));
        System.out.println(maximumStepsInGrid.maximumSteps(2, 2, new int[][]{{1, 2}, {3, 4}}));
        System.out.println(maximumStepsInGrid.maximumSteps(3, 4, new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
        System.out.println(maximumStepsInGrid.maximumSteps(3, 4, new int[][]{{1, 2, 3, 4}, {9, 10, 11, 12}, {5, 6, 7, 8}}));
    }

    public int maximumSteps(int r, int c, int[][] grid) {
        maximumStep = 0;


        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                //System.out.printf("begin with i=%d, j=%d\n", i, j);
                LinkedList<Integer> path = new LinkedList<>();
                boolean[][] used = new boolean[r][c];
                used[i][j] = true;
                path.add(grid[i][j]);
                backtracking(path, i, j, r, c, used, grid, 0, true);
                backtracking(path, i, j, r, c, used, grid, 0, false);
            }
        }
        System.out.println(finalPath);
        return maximumStep;
    }

    private void backtracking(LinkedList<Integer> path, int i, int j, int r, int c, boolean[][] used, int[][] grid, int currentStep, boolean lastStepIsUp) {
        // reach the end
        List<int[]> availableDirection = new ArrayList<>();
        for (int[] direction : directions) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            if ((nextI < r && nextI >= 0) && (nextJ < c && nextJ >= 0) && !used[nextI][nextJ]) {
                // 没有越过边界，且没有走过这个位置。
                if (lastStepIsUp) {
                    //这一步只能往下走。
                    if (grid[i][j] > grid[nextI][nextJ]) {
                        availableDirection.add(direction);
                    }
                } else {
                    // 这一步只能往上走
                    if (grid[i][j] < grid[nextI][nextJ]) {
                        availableDirection.add(direction);
                    }
                }
            }
        }
        if (availableDirection.isEmpty()) {
            if (currentStep > maximumStep) {
                maximumStep = currentStep;
                finalPath = new LinkedList<>(path);
            }
            return;
        }

        for (int[] direction : availableDirection) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            used[nextI][nextJ] = true;
            path.addLast(grid[nextI][nextJ]);
            backtracking(path, nextI, nextJ, r, c, used, grid, currentStep + 1, !lastStepIsUp);
            used[nextI][nextJ] = false;
            path.removeLast();
        }
    }
}
