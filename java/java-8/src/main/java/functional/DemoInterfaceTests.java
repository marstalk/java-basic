package functional;

import java.util.function.Supplier;

public class DemoInterfaceTests {
    public static void main(String[] args) {
        // 1.
        DemoInterface demoInterface = new DemoInterface() {
            @Override
            public String getName() {
                return "DemoInterfaceTests";
            }
        };

        System.out.println(demoInterface.getName());

        // 2. functional interface as parameter
        hello(() -> "Tom");

        // 3. funtional interface as return value
        System.out.println(getDemoInterface().getName());

        // 4. comparing build-in functional interface: Consumer, Supplier, Function, Predicate
        System.out.println("hello, " + new Supplier<String>() {
            @Override
            public String get() {
                return "Tom from supplier";
            }
        }.get());
    }

    public static void hello(DemoInterface demoInterface) {
        System.out.println("hello, " + demoInterface.getName());
    }

    public static DemoInterface getDemoInterface() {
        return () -> "Tom in functional as return value";
    }
    

}
