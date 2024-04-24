package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("b", "B");
        hashMap.put("a", "A");
        hashMap.put("aa", "AA");
        hashMap.entrySet().forEach(entry -> System.out.println(entry + ":" + entry.getValue()));

        System.out.println("-------");

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("e", "E");
        treeMap.put("a", "A");
        treeMap.put("c", "C");
        treeMap.put("0", "0");

        treeMap.forEach((key, value) -> System.out.println(key + ":" + value));

        System.out.println(treeMap.floorEntry("b"));// expected a return, the greatest that smaller than b if not hit.
        System.out.println(treeMap.ceilingEntry("b"));// expected c return, the smallest that greater than b if not hit.
        System.out.println(treeMap.floorEntry("c")); // expected c return, hit.
        System.out.println(treeMap.ceilingEntry("c")); // expected c return, hit.
        System.out.println(treeMap.floorEntry("-"));
    }
}
