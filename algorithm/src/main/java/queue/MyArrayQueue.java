package queue;


public class MyArrayQueue<T> {

    public static void main(String[] args) {
        System.out.println("----basic----");
        MyArrayQueue<Integer> queue = new MyArrayQueue<>(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.size() == 3);
        System.out.println(queue.dequeue() == 2);
        System.out.println(queue.dequeue() == 3);
        System.out.println(queue.size() == 0);
        System.out.println(queue.enqueue(4) == true);
        System.out.println(queue.toString());

        System.out.println(queue.enqueue(6));
        System.out.println(queue.toString());

    }



    private Object[] items;
    private int n;
    private int head = 0; //first的位置可以直接取。
    private int tail = 0; //last的位置可以直接放置。

    public MyArrayQueue(int capacity){
        this.n = capacity;
        this.items = new Object[capacity];
    }

    public int size(){
        return tail - head;
    }

    public boolean enqueue(T t){
        if(tail == n){
            if(head == 0) return false;
            //搬迁
            int j = 0;
            for(int i = head; i < tail; i++){
                items[j++] = items[i];
            }
            tail -= head;
            head = 0;
            // 清零以释放
            for(int i = tail; i < n; i++){
                items[i] = null;
            }
        }
        items[tail++] = t;
        return true;
    }

    /**
     * 一般来说，出列之后，要重新整理内存，但是这么做，时间复杂度是O(n)，为了优化这个，在入队之后如果发现没有空间了，再整体搬迁。
     */
    public T dequeue(){
        if(head == tail) return null;
        return (T)items[head++];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("head=").append(head).append(" tail=").append(tail).append(" items=");
        for(Object object: items){
            sb.append(object == null ? "_" : object.toString()).append(" ");
        }
        return sb.toString();
    }
}
