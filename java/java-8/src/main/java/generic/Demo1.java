package generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Demo1 {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(3);
        System.out.println(Demo1.size(strings));
        List<Integer> ints = new ArrayList<>(4);
        System.out.println(Demo1.size(ints));
        System.out.println();

        System.out.println(Demo1.size2(strings));
        System.out.println(Demo1.size2(ints));
        System.out.println();

        Demo1.print("11");
        Demo1.print(11);
    }

    /**
     * 函数泛型
     */
    public static <T> int size(Collection<T> list){
        return list.size();
    }

    /**
     * 函数泛型，因为我们不关心实际类型，所以这个用法更加合适。
     */
    public static int size2(Collection<?> list){
        return list.size();
    }

    /**
     * 获取两个集合的交集数量。
     */
    public static <T,T2> int beMixedSum(Set<T> s1, Set<T2> s2){
        int i = 0;
        for(T t: s1){
            if(s2.contains(t)) i++;
        }
        return i;
    }

    /**
     * 获取两个集合的交集数量
     */
    public static int beMixedSum2(Set<?> s1, Set<?> s2){
        int i = 0;
        for(Object o: s1){
            if(s2.contains(o)) i++;
        }
        return i;
    }

    public static <T> T print(T t){
        System.out.println(t);
        return t;
    }


}
