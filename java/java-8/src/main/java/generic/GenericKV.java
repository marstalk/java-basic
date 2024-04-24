package generic;

import java.util.HashMap;
import java.util.Map;

public class GenericKV{
    static class MyMap<K, V>{
        private Map<K, V> map = new HashMap<>();

        public void put(K k, V v){map.put(k, v);}

        public V getValue(K k){
            return map.get(k);
        }
    }

    public static void main(String[] args) {
        MyMap<String, String> myMap = new MyMap<>();
        myMap.put("name", "Mars");
        System.out.println(myMap.getValue("name"));
    }
}
