package array;

/**
 * We are given hours, a list of the number of hours worked per day for a given
 * employee.
 * 
 * A day is considered to be a tiring day if and only if the number of hours
 * worked is (strictly) greater than 8.
 * 
 * A well-performing interval is an interval of days for which the number of
 * tiring days is strictly larger than the number of non-tiring days.
 * 
 * Return the length of the longest well-performing interval.
 */
public class LongestWpi {
    public static void main(String[] args) {
        LongestWpi longestWpi = new LongestWpi();
        System.out.println(longestWpi.longestWpi(new int[] { 9, 9, 6, 0, 6, 6, 9 }));// 3:996
        System.out.println(longestWpi.longestWpi(new int[] { 6, 6, 6 }));// 0
    }

    /**
     * 我们需要关注的区域内的情况，所以使用滑动窗口算法。
     * 右指针负责无条件往前走，没走一步就会有新的元素加入到区域内。区域内发生变化，检查是否满足解。
     * 每走一步都需要判断 左指针是否需要往前走一步（缩小范围）。区域内发生变化，检查是否满足解。
     * 
     * @param hours
     * @return
     */
    public int longestWpi(int[] hours) {
        if (hours == null || hours.length == 0)
            return 0;

        int left = -1
        
        ;
        int right = 0;
        int res = 0;
        int tmp = 0;
        int tiredCnt = 0;
        int nonTiredCnt = 0;
        while (right < hours.length) {
            int i = hours[right];
            right++;
            if (i > 8) {
                tiredCnt++;
            }else{
                nonTiredCnt++;
            }
            
            if(tiredCnt > nonTiredCnt){
                tmp++;
            }else{
                res = tmp > res ? tmp : res;
                tmp=0;
                left=right;
            }
            

        }
        return res;
    }

}
