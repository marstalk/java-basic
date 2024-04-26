package collection;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // demo to demonstrate the use of LinkedHashMap
        java.util.LinkedHashMap<String, String> lhm = new java.util.LinkedHashMap<String, String>();
        lhm.put("1", "one");
        lhm.put("2", "two");
        lhm.put("3", "three");
        System.out.println(lhm);
    }
}
