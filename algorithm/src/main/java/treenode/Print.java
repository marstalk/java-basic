package treenode;

import java.util.List;

public class Print {
    public static void print(List<List<Integer>> res) {
        for(List<Integer> list : res){
            for(int i: list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
