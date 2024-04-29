package other;

import java.util.PriorityQueue;

public class DiagonalSort {
    public static void main(String[] args) {
        DiagonalSort sort = new DiagonalSort();
        int[][] res = sort.diagonalSort(new int[][]{{3,3,1,1},{2,2,1,2},{1,1,1,2}});
        for(int i = 0; i < res.length; i++){
            for(int j = 0; j < res[i].length; j++){
                System.out.print(res[i][j] + " ");    
            }
            System.out.println();
        }

    }

    public int[][] diagonalSort(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;

        for(int i = 0; i < m; i++){
            int x = i;
            PriorityQueue<Integer> list = new PriorityQueue<>();
            for(int j = 0; j < n; j++){
                if (x < m){
                    // System.out.println(mat[x++][j]);
                    list.offer(mat[x++][j]);
                }
            }
            
            x = i;
            for(int j = 0; j < n; j++){
                if (x < m){
                    mat[x++][j] = list.poll();
                }
            }
        }

        for(int j = 1; j < n; j++){
            int x = j;
            PriorityQueue<Integer> list = new PriorityQueue<>();
            for (int i = 0; i < m; i++){
                if (x < n){
                    // System.out.println(mat[i][x++]);
                    list.offer(mat[i][x++]);
                }
            }

            x = j;
            for (int i = 0; i < m; i++){
                if (x < n){
                    // System.out.println(mat[i][x++]);
                    mat[i][x++] = list.poll();
                }
            }
        }

        return mat;
    }
    
}
