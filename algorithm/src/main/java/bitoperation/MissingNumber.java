package bitoperation;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 */
public class MissingNumber {
    public static void main(String[] args) {
        /**
         * there are 4 solution that I can think of to sove this problem.
         * 1. sort and find the missing number by iteration. time complexity nlogn, and space complexity depends on which sort method you use.
         * 2. use hashmap, time complexity O(n), and space complexity is O(n)
         * 3. an ingenious solution: sum(0~n) - sum(nums[0~n])
         * 4. bit operation skill. since the index the number of index is quit the same, and only one number is missing like this:
         * index: 0,1,2
         * numbe: 0,3,1
         * 0 ^ 0 ^ 1 ^ 3 ^ 2 ^ 1 equals 0 ^ 0 ^ 1 ^ 1 ^ 2 ^ 3 since ^ xor opertion follows the commutative law
         */

        MissingNumber missingNumber = new MissingNumber();
        System.out.println(missingNumber.missingNumber(new int[]{0,1,3}));
        System.out.println(missingNumber.missingNumber2(new int[]{0,1,3}));

    }

    public int missingNumber(int[] nums){
        if(nums == null) return 0;
        int sum = 0;
        int sum2 = 0;
        for(int i = 0; i <nums.length; i++){
            sum += nums[i];
            sum2 += i;
        }
        return sum2 + nums.length - sum;
    }

    public int missingNumber2(int[] nums){
        if(nums == null) return 0;
        int missing = 0;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i;
            missing ^= nums[i];
        }
        missing ^= nums.length;
        return missing;
    }
}
