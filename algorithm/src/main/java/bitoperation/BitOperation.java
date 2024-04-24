package bitoperation;

public class BitOperation {
    public static void main(String[] args) {

        int bi = 0;
        // make true
        bi = bi | (1 << 5);
        bi = bi | (1 << 2);
        printInt("a", bi);

        // make false
        // printInt("(1 << 5) -1", (1 << 5) -1);
        bi = bi & ((1 << 5) - 1);

        printInt("bi & ((1 << 5) -1)", bi);

        bi = 0;
        // make true
        bi = bi | (1 << 5);
        bi = bi | (1 << 2);

        bi = bi ^ (1 << 5);
        printInt("bi ^(1 << 5)", bi);

        bi = bi & (1 << 4 - 1);
        printInt("d", bi);

        // make false
        bi = bi ^ (1 << 3);

        System.out.println("-------");
        System.out.println(3 >> 1);
        System.out.println();
        System.out.println(-3 >> 1);
        System.out.println(3 >>> 1);
        System.out.println(-3 >>> 1);
    }

    public static void printInt(String mark, int i) {
        System.out.print(mark + " " + ": ");
        print(i);
        System.out.println();
    }

    private static void print(int i) {
        if (i == 0) {
            System.out.print(0);
            return;
        }
        int cu = i & 1;
        print(i >> 1);
        System.out.print(cu);
    }
}
