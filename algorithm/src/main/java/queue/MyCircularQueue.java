package queue;

/**
 * 
 */
public class MyCircularQueue {

    public static void main(String[] args) {
        System.out.println("----basic----");
        MyCircularQueue queue = new MyCircularQueue(5);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        System.out.println(queue.size() == 3);
        System.out.println(queue.dequeue().equals("1"));
        System.out.println(queue.dequeue().equals("2"));
        System.out.println(queue.size() == 1);
        System.out.println(queue.enqueue("4") == true);
        System.out.println(queue.toString());

        System.out.println(queue.enqueue("5"));
        System.out.println(queue.toString());

        System.out.println(queue.enqueue("6"));
        System.out.println(queue.enqueue("7"));
        System.out.println(queue.toString());

    }


    private String[] items;
    private int cap;
    private int head = 0;
    private int tail = 0;
    
    public MyCircularQueue(int capacity){
        this.cap = capacity;
        this.items = new String[capacity];
    }

    public boolean enqueue(String i){
        if((tail + 1) % cap == head) return false;
        items[tail] = i;
        tail = (tail + 1) % cap;
        return true;
    }

    public String dequeue(){
        if(head == tail) return null;
        String res = items[head];
        items[head] = null;
        head = (head + 1) % cap;
        return res;
    }

    public int size(){
        return tail - head;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("head=").append(head).append(" tail=").append(tail).append(" items=");
        for(String str: items){
            sb.append(str == null ? "_" : str.toString()).append(" ");
        }
        return sb.toString();
    }
}
