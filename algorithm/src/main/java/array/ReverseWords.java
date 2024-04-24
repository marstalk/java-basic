package array;

/**
 * Java中的String类型是不可变类型，所以如果以String作为入参的话，做不到原地反转。
 * Welcome to Java
 * avaJ ot emocleW
 * Java to Welcome
 */
public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        System.out.println(reverseWords.reverseWords("the sky is blue"));
        System.out.println(reverseWords.reverseWords("a good   example"));
        System.out.println(reverseWords.reverseWords("  hello world  "));
    }

    /**
     * 使用栈来反转，需要额外的空间O(n)，并不是最优解。
     * 使用双指针可以实现数组原地反转，是最优解。
     * 
     * @param s
     * @return
     */
    String reverseWords(String s) {
        
        char[] ch = s.toCharArray();

        reverse(ch, 0, ch.length - 1);
        System.out.println(new String(ch));

        int i = 0;
        int j = 0;

        while (i < ch.length && j < ch.length) {
            while(i < ch.length && ch[i] == ' '){
                i++;
            }
            // 走到这里 ch[i] != ' '
            j = i;
            while(j < ch.length && ch[j] != ' '){
                j++;
            }
            // 走到这里 ch[j] == ' '
            reverse(ch, i, j-1);

            i = j;
        }

        // 去除多余的空格。TODO 
        return new String(ch).trim();
    }

    /**
     * 反转左闭右闭[left, right]区间的元素
     * 
     * @param ch
     * @param left
     * @param right
     */
    void reverse(char[] ch, int left, int right) {
        while (left < right) {
            char leftChar = ch[left];
            ch[left] = ch[right];
            ch[right] = leftChar;

            left++;
            right--;
        }
    }
}
