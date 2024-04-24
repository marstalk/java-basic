package dp;

/**
 * 最长回文子串。
 * solution 1:
 */
public class NNLongestPalindroDp {
    public static void main(String[] args) {
        NNLongestPalindroDp nnLongestPalindroDp = new NNLongestPalindroDp();
        System.out.println(nnLongestPalindroDp.longestPalindrome("racecar")); // racecar
        System.out.println(nnLongestPalindroDp.longestPalindrome("babad"));//bab, aba
    }


    /**
     * dp[i][j]表示string[i,j]子串是不是回文，那么
     * 当i=j，且i在[0, length)这个区间内时，都是回文（长度是1的子串都是回文）
     * 当j=i+1，且i在[0, length-1)这个区间时，当且仅当 string[i] == string[j]时，是回文。
     * 
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";

        boolean[][] dp = new boolean[s.length()][s.length()];
        // 1. for dp[i][i] and i in [0, len], it's always true.
        for(int i = 0; i < s.length(); i++){
            dp[i][i] = true;
        }

        int start = 0;
        int maxLen = 1;
        // 2. for dp[i][i+1] and i in [0, len], it's true when s[i] == s[j];
        for(int i = 0; i < s.length() - 1; i++){
            if (s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                start = i;
                maxLen = 2;
            }
        }

        // 3. for len >= 3 of all substring, dp[i][j] = true when dp[i+1][j-1] is true and s[i] == s[j];
        for(int subLen = 3; subLen <= s.length(); subLen++){
            for(int i = 0; i < s.length() - subLen + 1; i++){
                int j = i + subLen -1;//左闭右闭
                if(dp[i+1][j-1] && s.charAt(i) == s.charAt(j)){
                    dp[i][j] = true;
                    start = i;
                    maxLen = subLen;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }
}
