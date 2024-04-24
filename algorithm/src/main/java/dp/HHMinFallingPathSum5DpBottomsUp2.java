package dp;

/**
 * 从上往下落，函数dp(i,j)的含义是，落在（i,j）这个点上最小的和。
 */
public class HHMinFallingPathSum5DpBottomsUp2 {
    private final int[] directions = new int[]{-1, 0, 1};

    public static void main(String[] args) {
        HHMinFallingPathSum5DpBottomsUp2 minFallingPathSum = new HHMinFallingPathSum5DpBottomsUp2();
        System.out.println(minFallingPathSum.minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}})); // 13
        System.out.println(minFallingPathSum.minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}})); // -59
    }

    public int minFallingPathSum(int[][] matrix) {
        //TODO
        return 0;
    }
}