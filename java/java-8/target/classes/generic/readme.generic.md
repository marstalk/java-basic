In Java, ? and T are both used in generic type declarations, but they serve different purposes.

? is known as a wildcard and is used to denote an unknown type. It is often used in situations where you want to work with a generic type, but you do not know what the exact type will be. There are two types of wildcards in Java: ? extends SomeClass and ? super SomeClass. The ? extends syntax is used when you want to specify a upper bound for the wildcard, and the ? super syntax is used when you want to specify a lower bound.

T is known as a type parameter and is used to specify a generic type. It is often used in situations where you want to create a generic class or method that can work with any type. When you use T, you are essentially telling the compiler that there will be a type that will be specified later, and that type will be used throughout the class or method.

In summary, ? is used to denote an unknown type, while T is used to specify a generic type.


# 泛型类
类上定义泛型，作用域是**变量**和**函数**
```java
public class MyList<E>{
    private E e;
    public void function(E e){}
    public E get(){return e;}
}
```

# 泛型接口
定义在接口上，作用域是**函数**
```java
public interface MyInterface<T>{
    public T get();
    public void set(T t);
    public T delete(T t);
    
    default T hello(T t){
        return t;
    }
}
```

# 泛型函数
定义在方法上，作用域是**函数**
```java
public class Aa{
    public <T> void hello(T t){}
    public <T> T get(T t) {return t;}
    public <T> String get(T t){return "";}
}
```

