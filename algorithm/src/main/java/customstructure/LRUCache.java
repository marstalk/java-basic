package customstructure;

/**
 * 使用HashMap和双向链表实现
 */
public class LRUCache<K, V> {

    /**
     * @param cap 容量
     */
    public LRUCache(int cap){
        // TODO
    }

    /**
     * 根据key获得value，如果不存在，则返回null。
     * 注意：
     * 1. 将key设置为最近使用过的：放到tail的位置。
     * 2. 时间复杂度要求O(1)
     * @param key
     * @return
     */
    public V get(K key){
        // TODO
        return null;
    }

    /**
     * 将键值对加入或者更细到缓存中。
     * 注意：
     * 1. 保证容量不超过cap
     * 2. 将key设置为最近使用过的：放到tail的位置。
     * 3. 时间复杂度要求O(1)
     * @param key
     * @param value
     */
    public void put(K key, V value){
        // TODO
    }
    
}

class DoubleLinkedList{
    
}