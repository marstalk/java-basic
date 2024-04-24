package random;

import java.util.Random;

/**
 * 
 */
public class RandomDemo {
    public static void main(String[] args) {
        Random rnd = new Random(99999);
        Random rnd2 = new Random(99999);

        for(int i = 0; i < 10; i++){
            System.out.print(rnd.nextInt(20) + " ");
        }
        System.out.println();
        for(int i = 0; i < 10; i++){
            System.out.print(rnd2.nextInt(40) + " ");
        }
    }
}
