package org.example;

import org.junit.Assert;

public class YieldInSwitchDemo {
    public static void main(String[] args) {
        Assert.assertEquals(null, yieldInSwitchRuntime(null));
        Assert.assertEquals("hi", yieldInSwitchRuntime("liuyouer"));
        Assert.assertEquals("0", yieldInSwitchRuntime(0));
        Assert.assertEquals("custom", yieldInSwitchRuntime(new YieldInSwitchDemo()));
        Assert.assertEquals("default", yieldInSwitchRuntime(0d));

    }

    private static String yieldInSwitchRuntime(Object o){
        return switch(o){
            case null -> {
                System.out.println("null");
                yield null;
            }
            case String s -> {
                System.out.println("string");
                yield "hi";
            }
            case Integer i -> {
                System.out.println("integer");
                yield "0";
            }
            case YieldInSwitchDemo demo -> {
                System.out.println("YieldInSwitchDemo type");
                yield "custom";
            }
            default -> {
                System.out.println("default");
                yield "default";
            }
        };
    }
}
