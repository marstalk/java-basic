package compileandrun;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BWithSimpleImport {
    public static void main(String[] args) {
        System.out.println("classpath:" + System.getProperty("java.class.path"));
        System.out.println("args:" + Arrays.stream(args).collect(Collectors.joining(",")));
    }
}
