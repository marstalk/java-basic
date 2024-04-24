package array;

/**
 * 有序数组nums，原地删除重复项，返回新的数组的长度。
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        RemoveDuplicates re = new RemoveDuplicates();
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4,6,6,6,6};
        System.out.println(re.removeDuplicates(nums));;
    }

    public int removeDuplicates(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (slow != fast && nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

}
