package array;

public class RemoveElements {
    public static void main(String[] args) {
        RemoveElements removeElements = new RemoveElements();
        int[] nums = new int[] { 0, 1, 2, 2, 3, 0, 4, 2 };
        int val = 2;
        System.out.println(removeElements.removeElements(nums, val));
    }

    public int removeElements(int[] nums, int val) {
        if (null == nums) {
            return 0;
        }
        // slow -1,0 关系到 slow++和nums[slow] = nums[fast]的先后顺序，以及是return slow还是return slow+1
        int slow = -1;
        // fast -1,0 关系到是fast++的放置位置，是while的循环体的开始位置还是结束位置。
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
