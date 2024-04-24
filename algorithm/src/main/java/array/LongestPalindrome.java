package array;

/**
 * 最长回文子串
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("abcbadd"));
        System.out.println(longestPalindrome.longestPalindrome("cbbd"));

    }

    private String longestPalindrome(String s) {
        String rtn = "";

        for (int i = 0; i < s.length(); i++) {
            String a = longestPalindome(s, i, i);
            String b = longestPalindome(s, i, i+1);
            rtn = a.length() > rtn.length()? a : rtn;
            rtn = b.length() > rtn.length() ? b : rtn;
        }
        return rtn;
    }

    /**
     * 以left和right为中心字符查找最长回文。
     * @param str
     * @param left
     * @param right
     * @return
     */
    String longestPalindome(String str, int left, int right){
        if(left < 0 || right >= str.length()){
            return "";
        }
        
        boolean cal = false;
        while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)){
            left--;
            right++;
            cal = true;
        }

        // 走到这里说明left和right对应的位置已经不是回文了，所以回文的长度是left++
        // str.substring()：使用的是【左闭右开】
        return cal? str.substring(left +1, right) : str.substring(left, right);
    }
    

}
