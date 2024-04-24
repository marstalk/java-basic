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
 *
 * 方法1，递归，自下而上。
 */
public class TheTriangle1 {
    public static void main(String[] args) {
        TheTriangle1 theTriangle = new TheTriangle1();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(theTriangle.minimumSum(triangle));// 11
    }

    public int minimumSum(List<List<Integer>> triangle) {
        List<Integer> dp = dp(triangle, triangle.size() - 1);
        int res = Integer.MAX_VALUE;
        for (int i : dp) {
            res = Math.min(res, i);
        }
        return res;
    }

    private List<Integer> dp(List<List<Integer>> triangle, int level) {
        if (level == 0) {
            return triangle.get(0);
        }

        List<Integer> dp = dp(triangle, level - 1);
        List<Integer> res = new ArrayList<>();
        List<Integer> currentLevelList = triangle.get(level);
        for (int i = 0; i < currentLevelList.size(); i++) {
            if (i == 0) {
                res.add(dp.get(0) + currentLevelList.get(0));
            } else if (i == currentLevelList.size() - 1) {
                res.add(dp.get(i - 1) + currentLevelList.get(currentLevelList.size() - 1));
            } else {
                int left = dp.get(i - 1);
                int right = dp.get(i);
                res.add(Math.min(left, right) + currentLevelList.get(i));
            }
        }
        return res;
    }
}
