package dp;

public class DDTwoDimensionArrayLoop {
    public static void main(String[] args) {
        int[][] array = new int[5][5];
        array[0] = new int[]{0, 1, 2, 3, 4};
        array[1] = new int[]{5, 6, 7, 8, 9};
        array[2] = new int[]{10, 11, 12, 13, 14};
        array[3] = new int[]{15, 16, 17, 18, 19};
        array[4] = new int[]{20, 21, 22, 23, 24};
        int n = array.length;

        // from top to bottom, loop the top right part.
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                int x = i;
                int y = i + j;
                System.out.println(array[x][y]);
            }
        }

        System.out.println("--------------");
        // from bottom up, loop the top right part of array

    }
}
