package customstructure;

/**
 * 基于数组的实现
 */
public class LRUArray {
    
    
    public static void main(String[] args) {
        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
        print(3, 5, chars);
        print(4,7, chars);
    }

    /**
     * 打印”循环数组“
     * @param begin
     * @param length
     * @param chars
     */
    private static void print(int begin, int length, char[] chars){
        for (int i = begin; i < begin + length; i++) {
            int j = i % chars.length;
            System.out.print(chars[j]);
        }
        System.out.println();
    }
}
