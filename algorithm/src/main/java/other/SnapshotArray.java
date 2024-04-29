package other;

import java.util.LinkedList;

/**
Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 

Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 

Constraints:

1 <= length <= 5 * 104
0 <= index < length
0 <= val <= 109
0 <= snap_id < (the total number of times we call snap())
At most 5 * 104 calls will be made to set, snap, and get.
 */
public class SnapshotArray {
    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0,5);
        System.out.println(snapshotArray.snap()); // 0
        snapshotArray.set(0,6);
        System.out.println(snapshotArray.get(0,0)); // 5
        System.out.println();

        //test2
        SnapshotArray snapshotArray2 = new SnapshotArray(1);
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
        SnapshotArray snapshotArray3 = new SnapshotArray(1);
        snapshotArray3.set(0,4);
        snapshotArray3.set(0, 16);
        snapshotArray3.set(0, 13);
        System.out.println(snapshotArray3.snap());; // 0
        System.out.println(snapshotArray3.get(0, 0));; // 13
        System.out.println(snapshotArray3.snap());; // 1
    }

    private LinkedList<int[]>[] list;
    private int currentVersion;;
    /**
     * 创建长度是length的快照数组。
     * @param length
     */
    @SuppressWarnings("unchecked")
    public SnapshotArray(int length) {
        this.list = new LinkedList[length];
        this.currentVersion = 0;
    }
    
    /**
     * set value on index position.
     * @param index
     * @param val
     */
    public void set(int index, int val) {
        if (this.list[index] == null){
            this.list[index] = new LinkedList<>();
        }
        int[] lastValues = this.list[index].peekLast();
        if ( lastValues == null){
            this.list[index].addLast(new int[]{this.currentVersion, val});
        }else if (lastValues[0] == this.currentVersion){
            lastValues[1] = val;
        }else{
            this.list[index].addLast(new int[]{this.currentVersion, val});
        }
        
    }
    
    /**
     * create snapshot, and return snap_id which starts from 0.
     * @return
     */
    public int snap() {
        int res = this.currentVersion++;
        //
        for (int i = 0; i < this.list.length; i++){
            LinkedList<int[]> valuesList = this.list[i];
            if (valuesList == null){
                continue;
            }
            int val = valuesList.getLast()[1];
            valuesList.addLast(new int[]{res, val});
        }
        return res;
    }

    /**
     * get value on index position at snap_id.
     * @param index
     * @param snap_id
     * @return
     */
    public int get(int index, int snap_id) {
        if (this.list[index] == null) {
            return 0;
        }

        LinkedList<int[]> valueList = this.list[index];
        for (int[] items : valueList){
            if (items[0] == snap_id){
                return items[1];
            }
        }
        return 0;
    }
}
