package skiplist;

public class SkipNode<T>{
	private T val;
	private int id;
	private SkipNode<T> next;


	public SkipNode(int id, T val){
		this.id = id;
		this.val = val;
	}

	public void setNext(SkipNode<T> next){
		this.next = next;
	}

	public SkipNode<T> getNext(){
		return next;
	}

	public int getId(){
		return this.id;
	}

	public T getVal(){
		return this.val;
	}

}