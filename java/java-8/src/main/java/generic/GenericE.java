package generic;

/**
 * 类的泛型，作用域是属性和函数。
 */
public class GenericE {
    public static void main(String[] args) {
        MyList<Integer> integerMyList = new MyList<>();
        integerMyList.add(1);
        System.out.println(integerMyList.get(0) + 1);

        MyList<String> stringMyList = new MyList<>();
        stringMyList.add("Mars");
        System.out.println(stringMyList.get(0).toLowerCase());
    }

    static class MyList<E>{
        private E[] elements = (E[]) new Object[1];
        public void add(E e){elements[0] = e;}
        public E get(int index){return elements[index];}
    }

    static class MyList2<T>{
        private T[] elements;
        public void put(T t){}
        public T get(int index){return elements[index];}
    }

    static class MyList3<R>{
        private R[] elements;
        public void put(R r){}
        public R get(int index){return elements[index];}
    }

    class MyList4<A>{
        private A[] elements;
        public void put(A a){elements[1] = a;}
        public A get(int index){return elements[index];}
    }
}
