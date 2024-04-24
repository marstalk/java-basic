package bitoperation;

import java.util.Arrays;

/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 *
 * [1,2,1,3,2,5] -> [3,5]
 * [-1,0] -> [-1,0]
 *
 */
public class SingleNumber3 {
    public static void main(String[] args) {
        SingleNumber3 singleNumber = new SingleNumber3();
        System.out.println(Arrays.toString(singleNumber.singleNumber(new int[]{1, 2, 1, 3, 2, 5})));//[3,5]
        System.out.println(Arrays.toString(singleNumber.singleNumber(new int[]{-1, 0})));//[-1,0][-1,0]
        System.out.println(Arrays.toString(singleNumber.singleNumber(new int[]{1, 1, 0, -2147483648})));//[-2147483648, 0]
    }

    /**
     * 1. XOR all the elements
     * 2. find the lowest position which bit value is 1
     * 3. group by
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums){
        if(nums == null || nums.length <= 2) return nums;

        int x = 0;
        for(int num: nums){
            x ^= num;
        }

        // find the lowest position bit value which is 1
        int c = 0;
        while((x & 1) == 0){
            c++;
            x = x >>> 1;
        }

        int a=0,b=0;
        for(int num: nums){
             // group by c position.
             if((num >>> c & 1) == 1){
                 a = a ^ num;
             }else{
                 b = b ^ num;
             }
        }

        return new int[]{a,b};
    }
}
