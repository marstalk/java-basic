package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAnagrams {
    public static void main(String[] args) {
        FindAnagrams findAnagrams = new FindAnagrams();

        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> rtn = findAnagrams.findAnagrams(s, p);
        System.out.println(rtn);
    }

    public List<Integer> findAnagrams(String s, String p) {
        if(s.length() < p.length()){
            return null;
        }

        Map<Character, Integer> need = new HashMap<>(p.length());
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = 0, valid = 0;
        Map<Character, Integer> current = new HashMap<>();

        List<Integer> rtn = new ArrayList<>();
        while(r < s.length()){
            char charIn = s.charAt(r);
            r++;
            if(need.containsKey(charIn)){
                current.put(charIn, current.getOrDefault(charIn, 0) + 1);
                if(current.get(charIn).equals(need.get(charIn))){
                    valid++;
                }
            }

            while(r - l >= p.length()){
                if(valid == need.size()){
                    rtn.add(l);
                }

                char charOut = s.charAt(l);
                l++;
                if(need.containsKey(charOut)){
                    if(current.get(charOut).equals(need.get(charOut))){
                        valid--;
                    }
                    current.put(charOut, current.get(charOut) - 1);
                }
            }
        }

        return rtn;
    }
}