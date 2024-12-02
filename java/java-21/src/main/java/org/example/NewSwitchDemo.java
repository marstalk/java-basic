package org.example;

import org.junit.Assert;

public class NewSwitchDemo {
    public static void main(String[] args) {
        String hello = new String("hello");
        Assert.assertEquals("String: hello", runtimeTypes(hello));
        Assert.assertEquals("null value", runtimeTypes(null));
        Assert.assertEquals("Integer: 2", runtimeTypes(2));
        Assert.assertEquals("Custom Type: org.example.NewSwitchDemo", runtimeTypes(new NewSwitchDemo()));
        Assert.assertEquals("Double: 2.0", runtimeTypes(2.0));
        Assert.assertEquals("else type: java.lang.Float", runtimeTypes(2.0f));
    }

    private static String runtimeTypes(Object obj) {
        return switch (obj) {
            case null -> "null value";
            case String s -> "String: " + s;
            case Integer i -> "Integer: " + i;
            case NewSwitchDemo myType -> "Custom Type: " + myType.getClass().getTypeName();
            case Double d -> getStr(d);
            default -> "else type: " + obj.getClass().getTypeName();
        };
    }

    private static String getStr(double d) {
        return "Double: " + d;
    }

    private static double toDouble(Object o){
        return switch (o){
            case null -> 0d;
            case Integer i -> i.doubleValue();
            case Float f -> f.doubleValue();
            case Double d -> d;
            case String s -> Double.parseDouble(s);
            default -> 0d;
        };
    }

    private static int switchValue(Integer i) {
        return switch (i) {
            case 1 -> 1 + 1;
            case 2 -> 2 + 2;
            default -> 99;
        };
    }
}
