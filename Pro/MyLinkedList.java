package Pro;

/**
 * ArrayList/Vector/LinkedList
 * @author 安迪
 *
 */
public class MyLinkedList<T> {
	
	/**
	 * 头尾节点不计入链表，仅用于牵引链表
	 */
	private Node<T> head = new Node<T>();
	private Node<T> tail = new Node<T>();
	private int size;
	
	MyLinkedList(){
		head.setNext(tail);
		tail.setPrevious(head);
	}
	/**
	 * 在链表正向尾部添加节点
	 * @param any
	 */
	public void addWithHead(T any) {
		Node<T> h = new Node<T>(any);
		tail.getPrevious().setNext(h);
		h.setNext(tail);
		size++;
	}
	
	/**
	 * 在链表反向头部添加节点
	 * @param any
	 */
	public void addWithTail(T any) {
		Node<T> h = new Node<T>(any);
		head.getNext().setPrevious(h);
		h.setPrevious(head);
		size++;
	}
	
	/**
	 * 在头部开始的索引处插入元素
	 * 在此，不作越界检测
	 * @param index
	 */
	public void insertAtPosWithHead(int index,T any) {
		Node<T> h = head.getNext();
		while(index-->0) {
			h = h.getNext();
		}
		Node<T> t = new Node<T>(any);
		h.getPrevious().setNext(t);
		t.setPrevious(h.getPrevious());
		t.setNext(h);
		h.setPrevious(t);
		size++;
	}
	
	/**
	 * 返回指定索引的值
	 * @param index
	 * @return
	 */
	public T get(int index) {
		int i=0;
		Node<T> h;
		if(index>size)return null;
		for(h = head.getNext();h!=tail;h = h.getNext()) {
			if(i==index)
				break;
			i++;
		}
		return h.getSelf(); 
	}
	
	/**
	 * 调用元素的equals()方法获取与tar相等的元素
	 * @param tar
	 * @return
	 */
	public T get(Object tar) {
		for(Node<T> h = head.getNext();h!=tail;h = h.getNext()) {
			if(h.getSelf().equals(tar)) {
				return h.getSelf();
			}
		}
		return null;
	}
	
	/**
	 * 将old替换为now并返回true
	 * 如old不存在则返回false;
	 * @param old
	 * @param now
	 * @return
	 */
	public boolean replace(T old,T now) {
		for(Node<T> h = head.getNext();h!=tail;h = h.getNext()) {
			if(h.getSelf().equals(old)) {
				h.setSelf(now);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除指定元素
	 * @param any
	 */
	public boolean remove(T any) {
		for(Node<T> h = head.getNext();h!=tail;h = h.getNext()) {
			if(any.equals(h.getSelf())) {
				h.getPrevious().setNext(h.getNext());
				h.getNext().setPrevious(h.getPrevious());
				size--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 返回链表元素个数，用处不大
	 * 只为实现List的接口(在此处未继承List)
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 从头结点打印链表每个元素值
	 */
	public void print() {
		for(Node<T> h = head.getNext();h!=tail;h = h.getNext()) {
			System.out.print(h.getSelf()+" "); 
		}
		System.out.println();
	}	
	public void printWithTail() {	
		for(Node<T> h = tail.getPrevious();h!=head;h = h.getPrevious()) {
			System.out.print(h.getSelf()+" "); 
		}
		System.out.println();
	
	}
	
	
	
	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		someTestOfNode();
		System.out.println();
		list.addWithHead(1);
		list.addWithHead(2);
		list.addWithHead(3);
		list.addWithHead(4);
		list.addWithHead(5);
		list.addWithTail(6);
		list.print();
		list.remove(4);
//		list.addWithTail("aaa");
//		list.addWithTail(4.5);
		list.print();
		System.out.println(list.size());
//		list.insertAtPosWithHead(2, "any");
		list.print();
		list.printWithTail();
	}

	 private static void someTestOfNode() {
		 Node<Integer> head = new Node<Integer>();
			Node<Integer> l0 = new Node<Integer>();
			Node<Integer> l1 = new Node<Integer>();
			Node<Integer> l2 = new Node<Integer>();
			Node<Integer> tail = new Node<Integer>();
			head.setNext(l0).setNext(l1).setNext(l2).setNext(tail);
			head.setSelf(1).setSelf(2).setSelf(3).setSelf(4).setSelf(5);
			for(Node<Integer> l = head;l!=null;l = l.getNext()) {
				System.out.print(l.getSelf()+" ");
			}
			for(Node<Integer> l = tail;l!=null;l = l.getPrevious()) {
				System.out.print(l.getSelf()+" ");
			}
	 }

}




/**
 * 双向链表
 * @author 安迪
 *
 */
class Node<T>{
	private T self;
	private Node<T> previous;
	private Node<T> next;
	

	Node() {
		self = null;
		previous = null;
		next = null;
	}
	
	Node(T any) {
		self = any;
		previous = null;
		next = null;
	}
	
	public T getSelf() {
		return this.self;
	}
	
	public Node<T> setSelf(T self) {
		this.self = self;
		return this.next;
	}
	
	public Node<T> getPrevious() {
		return this.previous;
	}
	
	public void setPrevious(Node<T> previous) {
		this.previous = previous;
		previous.next = this;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	//可链式操作
	public Node<T> setNext(Node<T> next) {
		this.next = next;
		next.previous = this;
		return next;
	}	
}