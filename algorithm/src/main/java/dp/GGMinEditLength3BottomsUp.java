package dp;

/**
 * insert, replace, delete S1 to S2 with minimum steps.
 * rad -> apple
 */
public class GGMinEditLength3BottomsUp {

    public static void main(String[] args) {
        GGMinEditLength3BottomsUp minEditLength = new GGMinEditLength3BottomsUp();
        System.out.println(minEditLength.minSteps("rad", "apple")); // 5
        System.out.println(minEditLength.minSteps("horse", "roe")); //3
        System.out.println(minEditLength.minSteps("intention", "execution")); //5
        System.out.println(minEditLength.minSteps("dinitrophenylhydrazine", "benzalphenylhydrazone"));
    }


    public int minSteps(String s1, String s2) {
        // 实现自上而下的
        return 0;
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
