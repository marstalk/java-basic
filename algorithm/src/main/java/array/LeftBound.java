package array;

/**
 * 查找有序数组中的目标值的左侧边界，比如1,2,2,2,2,3，查找2，返回1；比如，1,2,3,4,4,4,5，查找4返回3，查找6返回-1
 */
public class LeftBound {
    public static void main(String[] args) {
        //int[] nums = new int[] { 1, 2, 2, 2, 2, 3 };
        int[] nums2 = new int[] { 1, 2, 3, 4, 4, 4, 5 };
        int target = 4;

        LeftBound leftBound = new LeftBound();
        System.out.println(leftBound.leftBound(nums2, target));
        System.out.println(leftBound.leftBound2(nums2, target));
    }

    /**
     * 使用左闭右闭方式查找目标值边界。
     * 
     * @param nums
     *            有序数组
     * @param target
     *            目标值
     * @return 存在则返回下标志，否则返回-1
     */
    int leftBound(int nums[], int target) {
        if (nums == null) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                right = mid - 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // left > right
        if (left == nums.length) {
            return -1;
        }
        return target == nums[left] ? left : -1;
    }

    /**
     * 左闭右开
     * 
     * @param nums
     * @param target
     * @return
     */
    int leftBound2(int nums[], int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                right = mid;
            } else if (target < nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }

        // 当left == right，说明所有的元素已经查找玩了，要么已经找到，要么没有。
        if (left == nums.length) {
            return -1;
        }

        return nums[left] == target ? left : -1;
    }
}
