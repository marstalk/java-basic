package bitoperation;

/**
 * each element appears exactly three times except for one which appears only once.
 */
public class SingleNumber2 {
    public static void main(String[] args) {
        SingleNumber2 singleNumber2 = new SingleNumber2();
        System.out.println(singleNumber2.single(new int[]{0,1,0,1,0,1,99})); // 99
        System.out.println(singleNumber2.single(new int[]{1,1,1,2,2,2,3}));
    }

    public int single(int[] nums){
        if(nums == null) return 0;
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for(int j = 0; j < nums.length; j++){
                if((nums[j] & 1) == 1){
                    cnt++;
                }
                nums[j] = nums[j] >>> 1;
            }
            ret = ret | (cnt % 3) << i;
        }
        return ret;
    }
}
