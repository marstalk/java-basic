package cow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CopyOnWriteDemo1 {
    public static void main(String[] args) {
        // Create a shared list (original data)
        List<Integer> sharedList = new ArrayList<>();

        // Populate the shared list
        for (int i = 1; i <= 10; i++) {
            sharedList.add(i);
        }

        // Create a copy of the shared list (initially, this is a reference to the same list)
        List<Integer> copyList = new ArrayList<>(sharedList);

        // Modify the copy list
        copyList.add(11);

        // Now, we'll see that the shared list remains unchanged
        System.out.println("Shared List: " + sharedList);
        System.out.println("Copy List: " + copyList);

        // Using a synchronized version of the shared list (thread-safe)
        List<Integer> synchronizedList = Collections.synchronizedList(sharedList);
        synchronizedList.add(12);

        // The synchronized version allows concurrent access, but modifications are synchronized
        System.out.println("Synchronized List: " + synchronizedList);

        exceptionWhenUpdateListWhileReading();

    }

    public static void exceptionWhenUpdateListWhileReading(){
        List<Integer> sharedList = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            sharedList.add(i);
        }

        new Thread(()->{
            for (int i = 0; i < sharedList.size(); i++) {
                System.out.print(sharedList.get(i) + " ");
            }
        }).start();

        new Thread(()->{
            sharedList.remove(1);
        }).start();
    }
}

