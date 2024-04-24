package array;

import java.util.HashMap;
import java.util.Map;

public class BasicWindow {
    public static void main(String[] args) {
        BasicWindow basicWindow = new BasicWindow();
        // minWindow使用startIndex, len
        String rtn = basicWindow.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(rtn);
        // minWindow2使用startIndex, endIndex
        String rtn2 = basicWindow.minWindow2("ADOBECODEBANC", "ABC");
        System.out.println(rtn2);
    }

    String minWindow(String s, String t){
        // 如果s的长度小于t的长度，那么肯定包不住t的需求，直接return
        if(t.length() > s.length()){
            return "";
        }

        // 将需求转为map，为后序使用提供方便。
        Map<Character, Integer> need = new HashMap<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        // current
        Map<Character, Integer> current = new HashMap<>();

        // 为什么是start和len，而不是start和end？因为我们要判断最优解的时候，是根据长度来判断的，
        int start = 0;
        int len = Integer.MAX_VALUE;

        int l=0, r=0, valid=0;

        while(r < s.length()){
            char ch = s.charAt(r);
            r++;

            if(need.containsKey(ch)){
                current.put(ch, current.getOrDefault(ch, 0) + 1);
                // 注意区分==和equals
                if(current.get(ch).equals(need.get(ch))){
                    valid++;
                }
            }

            while(valid == need.size()){
                if(r-l < len){
                    start = l;
                    len = r-l;
                }

                char cha = s.charAt(l);
                l++;

                if(need.containsKey(cha)){
                    if(need.get(cha).equals(current.get(cha)) ){
                        valid--;
                    }
                    current.put(cha, current.get(cha) -1);
                }
            }
        }

        return len == Integer.MAX_VALUE? "": s.substring(start, start+len);
    }

    String minWindow2(String s, String t){
        if(s.length() < t.length()){
            return "";
        }

        // window condition
        Map<Character, Integer> need = new HashMap<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) +1);
        }
        
        // corrent window
        Map<Character, Integer> current = new HashMap<>();

        int l=0, r=0, valid=0;
        int start = 0;
        int end = Integer.MAX_VALUE;

        while(r < s.length()){
            char inChar = s.charAt(r);
            r++;

            if(need.containsKey(inChar)){
                current.put(inChar, current.getOrDefault(inChar, 0) + 1);
                if(need.get(inChar).equals(current.get(inChar))){
                    valid++;
                }
            }

            while(valid == need.size()){
                if(r - l < end - start){
                    start = l;
                    end  = r;
                }

                char outChar = s.charAt(l);
                l++;

                if(need.containsKey(outChar)){
                    if(current.get(outChar).equals(need.get(outChar))){
                        valid--;
                    }
                    current.put(outChar, current.get(outChar)-1);
                }
            }
        }
        
        
        return end == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }

    
}
