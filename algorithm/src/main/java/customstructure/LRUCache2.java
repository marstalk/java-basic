package customstructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通过LinkedHashMap实现的LRU缓存。
 */
public class LRUCache2<K, V> extends LinkedHashMap<K, V>{
    private int size;
    public LRUCache2(int size) {
        super(size, 0.75f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }

    public static void main(String[] args) {
        LRUCache2<Integer, Integer> cache = new LRUCache2<>(10);
        for (int i = 0; i < 100; i++) {
            cache.put(i, i);
            cache.get(0);
            cache.get(8);
        }
        for(int key: cache.keySet()){
            System.out.println(key);
        }
    }
}
