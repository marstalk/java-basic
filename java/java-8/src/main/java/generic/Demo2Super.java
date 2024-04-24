package generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Demo2Super {
    public static void main(String[] args) {
        CollectionUtil<String> collectionUtil = new CollectionUtil<>();

        List<String> strings = new ArrayList<>();
        strings.add("Welcome");
        strings.add("Mars");
        List<String> copy = collectionUtil.listCopy(strings);
        copy.forEach(System.out::println);

        //因为我们在声明collectionUtil的时候，用的是String泛型，所以下面的编译异常。通用性不强。怎么办？CollectionUtil2
//        List<Number> list = new ArrayList<>();
//        list.add(new Integer(1));
//        list.add(new Long(2L));
//        collectionUtil.listCopy(list);

    }

    static class CollectionUtil<T>{
        public List<T> listCopy(Collection<T> collection){
            List<T> list = new ArrayList<>();
            for(T t: collection){
                list.add(t);
            }
            return list;
        }
    }

}
