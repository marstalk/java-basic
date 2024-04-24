package stream;

import java.util.stream.Stream;

/**
 * jdk>=8
 */
public class StreamDemo {
    public static void main(String[] args) {
        String[] strs = new String[]{"react", "", "spring", "bo_le", "bo_le", "spring"};
        // b, o, l, e
        Stream.of(strs)
                .filter(s -> !s.isEmpty())
                .distinct()
                .sorted()
                .limit(1)
                .map(s -> s.replaceAll("_", ""))
                .flatMap(s -> Stream.of(s.split("")))
                .forEach(System.out::println);
    }
}
