package sort;

public class InsertionSort {
    public static void main(String[] args) {
        InsertionSort bubbleSort = new InsertionSort();
        for (int i : bubbleSort.insertionSort(new int[] { 3, 1, 5, 8, 5, 1 })) {
            System.out.print(i + " ");
        }
    }

    public int[] insertionSort(int[] arr) {
        if (arr == null || arr.length == 1)
            return arr;

        for (int i = 1; i < arr.length; i++) {
            int v = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > v) {
                    arr[j + 1] = arr[j];
                } else {
                    //前面的数已经有序了。
                    break;
                }
            }
            arr[j+1] = v;
        }
        return arr;
    }
}
