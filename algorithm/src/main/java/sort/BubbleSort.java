package sort;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        for(int i: bubbleSort.bubbleSort(new int[]{3,1,5,8,5,1})){
            System.out.print(i + " ");
        }
    }
    

    public int[] bubbleSort(int[] arr){
        if(arr == null || arr.length == 1) return arr;

        for(int i = 0; i < arr.length; i++){
            boolean swap = false;
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    swap = true;
                }
            }
            if(!swap) break;// 优化点，如果没有必须进行交换的，说明整个数组已经有序了。
        }
        return arr;
    }
}
