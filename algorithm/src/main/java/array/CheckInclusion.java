package array;

import java.util.HashMap;
import java.util.Map;

public class CheckInclusion {

    public static void main(String[] args) {
        CheckInclusion checkInclusion = new CheckInclusion();
        // String str = "eidbaooo";
        // String target = "ab";

        String str = "abcdeabcdx";
        String target = "abcdxabcde";
        
        
        boolean rtn = checkInclusion.checkInclusion(str, target);
        System.out.println(rtn);

        boolean rtn2 = checkInclusion.checkInclusion2(str, target);
        System.out.println(rtn2);
    }

    boolean checkInclusion(String s, String t) {
        if (s.length() < t.length()) {
            return false;
        }

        Map<Character, Integer> need = new HashMap<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> current = new HashMap<>();
        int l = 0, r = 0, valid = 0;
        while (r < s.length()) {
            char inChar = s.charAt(r);
            r++;

            //我不太明白为什么这里需要判断need.containsKey()：你想一下valid++就知道了。
            if (need.containsKey(inChar)) {
                current.put(inChar, current.getOrDefault(inChar, 0) + 1);
                if (need.get(inChar).equals(current.get(inChar))) {
                    valid++;
                }
            }

            // 保证窗口的长度跟目标字符串的长度是一样的。valid == need.size()行不行？可以，看checkInclusion2()
            // 另外一点需要注意📢 ，这里应该是t.length而不是need.size()。因为元素可能重复！！！！
            while (r - l >= t.length()) {
                if (valid == need.size()) {
                    return true;
                }

                char outChar = s.charAt(l);
                l++;

                if (need.containsKey(outChar)) {
                    if (need.get(outChar).equals(current.get(outChar))) {
                        valid--;
                    }
                    current.put(outChar, current.get(outChar) - 1);
                }
            }
        }

        return false;
    }


    boolean checkInclusion2(String s1, String s2){
        if(s1 == null || s2 == null){
            return false;
        }
        if(s1.length() < s2.length()){
            return false;
        }
    
        Map<Character, Integer> need = new HashMap<>(s2.length());
        for(int i =0; i < s2.length(); i++){
            char c = s2.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
    
        int l = 0, r = 0, valid = 0;
        Map<Character, Integer> current = new HashMap<>(s2.length());
    
        while(r < s1.length()){
            char charIn = s1.charAt(r);
            r++;
    
            if(need.containsKey(charIn)){
                current.put(charIn, current.getOrDefault(charIn, 0) + 1);
                if(need.get(charIn).equals(current.get(charIn))){
                    valid++;
                }
            }
    
            while(valid == need.size()){
                // 注意📢 ，这里应该是s2.length而不是need.size()。因为元素可能重复！！！！
                if(r - l == s2.length()){
                    return true;
                }
                char charOut = s1.charAt(l);
                l++;
                if(need.containsKey(charOut)){
                    if(need.get(charOut).equals(current.get(charOut))){
                        valid--;
                    }
                    current.put(charOut, current.get(charOut) - 1);
                }
            }
        }
        return false;
    }
}
