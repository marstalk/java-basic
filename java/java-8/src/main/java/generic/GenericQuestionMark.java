package generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ? is known as a wildcard and is used to denote an unknown type.
 * It is often used in situations where you want to work with a generic type,
 * but you do not know what the exact type will be. There are two types of wildcards in Java:
 * ? extends SomeClass and ? super SomeClass.
 *
 * The ? extends syntax is used when you want to specify a upper bound for the wildcard,
 * and the ? super syntax is used when you want to specify a lower bound.
 *
 * ？可以很多种类型，比如当我们想创建一个IoC容器的时候，Map中的key是Class类型
 */
public class GenericQuestionMark {

    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<Number>();
        numberList.add(1);
        numberList.add(0.5);
        numberList.add(10000L);

        // List<Number> 这种泛型使用方式要求变量和对象之间的【泛型绝对一致】，否则编译异常。如果避免编译异常呢？可以使用？
        //List<Number> numberList2 = new ArrayList<Integer>();

        List<Integer> integerList = new ArrayList<>();
        integerList.add(100);
        List<? extends Number> numberList3 = integerList;
        // extends只读不写，否则编译异常。
        //numberList3.add(2);
        System.out.println(numberList3.get(0));// 100

        List<? super Integer> superList = integerList;
        superList.add(200);
        // 只返回object类型，没有什么意义。
        Object object = superList.get(0);

        List<Object> objects = new ArrayList<>();
        superList = objects;
        superList.add(111);
    }
}
