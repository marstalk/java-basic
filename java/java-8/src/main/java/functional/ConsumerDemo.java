package functional;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        test1(ConsumerDemo::sayHelloToConsole);
    }

    public static void test1(Consumer<String> consumer) {
        System.out.println("functional programming");
        consumer.accept("Hello World");
        // 1. 定义一个方法，接收一个参数，这个参数是一个接口
        // 2. 接口中定义一个方法
        // 3. 接口的实现类，实现这个接口
        // 4. 接口的实现类，实现这个接口
    }

    public static void sayHelloToConsole(String message){
        System.out.println(message);
    }
}
