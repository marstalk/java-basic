package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 加权随机选择
 */
public class WeightRandomPick {
    public static void main(String[] args) {
        Solution solution = new Solution(new int[] { 1, 50, 50, 0 });
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int picked = solution.pickIndex();
            count.put(picked, count.getOrDefault(picked, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry: count.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

class Solution {
    private int[] preSum;
    private Random random;

    /**
     * 权重数组如 [1,3,2,1]，转为前缀和，即[0, 1, 4, 6, 7]
     * @param w
     */
    public Solution(int[] w) {
        random = new Random();
        preSum = new int[w.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + w[i - 1];
        }
    }

    /**
     * 使用等概率随机获取[1, 7]，即random.nextInt(7) + 1, 即[0, 7), 即[0, 6], +1之后即[1, 7] <br/>
     * 在前缀和中寻找target的左边界 - 1。（左边界还有一个含义是>=target的最小元素的小标）
     * random等概率随机产生数有：1,2,3,4,5,6,7，分别看：
     * 如果target=1，则返回0
     * 如果target=2，则返回1
     * 如果target=3，则返回1
     * 如果target=4，则返回1
     * 如果target=5，则返回2
     * 如果target=6，则返回2
     * 如果target=7，则返回3
     * 即可实现返回0的概率是1/7，返回1的概率是3/7，返回2的概率是2/7，返回3的概率是1/7，对应权重数组[1,3,2,1]
     * @return
     */
    public int pickIndex() {
        int i = random.nextInt(preSum[preSum.length - 1]) + 1;
        return left_bound(preSum, i) - 1;
    }

    /**
     * 寻找左边界，左闭右开
     */
    public int left_bound(int[] nums, int target){
        int left = 0;
        int right = nums.length;

        while(left < right){
            int mid = left + (right - left) / 2;
            if(target == nums[mid]){
                right = mid;
            }else if (target < nums[mid]){
                right = mid;
            }else if(target > nums[mid]){
                left = mid + 1;
            }
        }
        
        return left;
    }
}
