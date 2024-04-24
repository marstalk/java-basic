package array;

public class RightBound {
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 2, 2, 2, 3 };
        // int[] nums2 = new int[] { 1, 2, 3, 4, 4, 4, 5 };
        int target = 0;

        RightBound rightBound = new RightBound();
        System.out.println(rightBound.rightBound(nums, target));
        System.out.println(rightBound.rightBound2(nums, target));
    }

    /**
     * 左闭右闭
     */
    public int rightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        // left > right
        if(left == nums.length || left == 0){
            return -1;
        }
        return nums[left - 1] == target? left - 1 : -1;
    }

    /**
     * 左闭右开
     * 
     * @param nums
     * @param target
     * @return
     */
    public int rightBound2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }

        // 说明left == right
        if (left == nums.length || left == 0) {
            return -1;
        }

        return nums[left - 1] == target ? left - 1 : -1;
    }

}
