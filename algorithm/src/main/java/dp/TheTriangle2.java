package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个三角形，自顶向下找到最小路径和。
 * 2
 * 2,3
 * 6,5,7
 * 4,1,8,3
 * <p>
 * 方法2，遍历，自上而下。
 */
public class TheTriangle2 {
    public static void main(String[] args) {
        TheTriangle2 theTriangle = new TheTriangle2();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(theTriangle.minimumSum(triangle));// 11
    }

    public int minimumSum(List<List<Integer>> triangle) {
        int n = triangle.size();
        List<Integer> pre = triangle.get(0);
        List<Integer> cur;
        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            cur = new ArrayList<>(list.size());
            for (int j = 0; j < list.size(); j++) {
                if (j == 0) {
                    cur.add(pre.get(0) + list.get(0));
                } else if (j == list.size() - 1) {
                    cur.add(pre.get(pre.size() - 1) + list.get(list.size() - 1));
                } else {
                    int left = pre.get(j - 1);
                    int right = pre.get(j);
                    cur.add(Math.min(left, right) + list.get(j));
                }
            }
            pre = cur;
        }
        int res = Integer.MAX_VALUE;
        for (int i : pre) {
            res = Math.min(res, i);
        }
        return res;
    }
}
