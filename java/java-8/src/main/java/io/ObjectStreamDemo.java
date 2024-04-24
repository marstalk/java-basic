package io;

import java.io.*;

public class ObjectStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
         * 将对象写到文件中。
         */
        String path = "java/java-8/data/java_object_serializable";

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(new A(1, "mars", 18));
        }

        /*
         * 将对象从文件中读取出来
         */
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            A a = (A)ois.readObject();
            System.out.println(a); // A{id=1, name='mars', age=0, b=B{id=1}}
        }

    }


    static class A implements Serializable {
        private int id;
        private String name;
        /**
         * transient修饰的字段不会进行序列化。
         */
        transient private int age;

        /**
         * 支持对象嵌套
         */
        private B b;

        public A(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.b = new B(id);
        }

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", b=" + b +
                    '}';
        }
    }

    static class B implements Serializable{
        private int id;

        public B(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "B{" +
                    "id=" + id +
                    '}';
        }
    }

}
