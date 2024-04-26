package other;

import java.util.Map;
import java.util.TreeMap;

/**
    optimize time complexity from O(n) to O(1) by using TreeMap.
    treeMap providing the floorEntry which is really useful in the case.
 */
public class SnapshotArrayV2 {
    public static void main(String[] args) {
        SnapshotArrayV2 snapshotArray = new SnapshotArrayV2(3);
        snapshotArray.set(0,5);
        System.out.println(snapshotArray.snap()); // 0
        snapshotArray.set(0,6);
        System.out.println(snapshotArray.get(0,0)); // 5
        System.out.println();

        //test2
        SnapshotArrayV2 snapshotArray2 = new SnapshotArrayV2(1);
        snapshotArray2.set(0,15);
        System.out.println(snapshotArray2.snap()); // 0 
        System.out.println(snapshotArray2.snap()); // 1
        System.out.println(snapshotArray2.snap()); // 2
        System.out.println(snapshotArray2.get(0,2)); // 15
        System.out.println(snapshotArray2.snap()); // 3
        System.out.println(snapshotArray2.snap()); // 4
        System.out.println(snapshotArray2.get(0,0)); // 15
        System.out.println();

        //test3
        SnapshotArrayV2 snapshotArray3 = new SnapshotArrayV2(1);
        snapshotArray3.set(0,4);
        snapshotArray3.set(0, 16);
        snapshotArray3.set(0, 13);
        System.out.println(snapshotArray3.snap());; // 0
        System.out.println(snapshotArray3.get(0, 0));; // 13
        System.out.println(snapshotArray3.snap());; // 1
    }

    private Map<Integer, Integer>[] mapArray;
    private int currentVersion;;
    /**
     * 创建长度是length的快照数组。
     * @param length
     */
    public SnapshotArrayV2(int length) {
        this.mapArray = new Map[length];
        this.currentVersion = 0;
    }
    
    /**
     * set value on index position.
     * @param index
     * @param val
     */
    public void set(int index, int val) {
        Map<Integer, Integer> map = mapArray[index];
        if (map == null) {
            map = new TreeMap<>();
            mapArray[index] = map;
        }

        map.put(currentVersion, val);
    }
    
    /**
     * create snapshot, and return snap_id which starts from 0.
     * @return
     */
    public int snap() {
        int res = this.currentVersion++;
        return res;
    }

    /**
     * get value on index position at snap_id.
     * @param index
     * @param snap_id
     * @return
     */
    public int get(int index, int snap_id) {
        if (this.mapArray[index] == null) {
            return 0;
        }

        TreeMap<Integer, Integer> map = (TreeMap<Integer, Integer>) this.mapArray[index];
        return map.floorEntry(snap_id) == null? 0 : map.floorEntry(snap_id).getValue();
    }
}
