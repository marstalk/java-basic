package array;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        System.out.println(l.lengthOfLongestSubstring("aab"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }

        int l = 0, r = 0, rtn = 0;
        Set<Character> current = new HashSet<>(s.length());
        while (r < s.length()) {
            char c = s.charAt(r);
            r++;

            while (current.contains(c)) {
                char out = s.charAt(l);
                l++;
                current.remove(out);
            }

            // 走到这里，说明current里没有c，把c加入之后就是局部最优解。
            current.add(c);
            rtn = rtn < current.size() ? current.size() : rtn;
        }
        return rtn;
    }
}
