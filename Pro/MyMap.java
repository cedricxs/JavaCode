package Pro;

/**
 * 重写map
 * 底层实现：数组+链表
 * 必须重写hasCode()和equals()
 * 满足equals()==true的两个元素hashCode()一定相等!!!!
 * 所以检查重复只需检查一根链表即可
 * @author 安迪
 * 重写泛型时，也可将Entry<Object,Object>并只对外暴露K，V类型的put()方法！！！
 *
 */
public class MyMap<K,V> {
	
	//盛放链表
	final MyArrayList<MyLinkedList<Entry<K,V>>> content;
	int count;
	/**
	 * 初始化数组长度，一旦初始化，长度不再变
	 * @param size
	 */
	
	MyMap(){
		this(16);
	}
	
	
	MyMap(int size){
		content = new MyArrayList<MyLinkedList<Entry<K,V>>>(size);
	}
	
	/**
	 * 由Key值的哈希码确定位置，可实现重复则覆盖
	 * @param key
	 * @param value
	 */
	public void put(K key,V value) {
		Entry<K,V> t = new Entry<K,V>(key,value);
		int a = Math.abs(t.hashCode())%content.getCapacity();
		if(content.getValueOf(a)==null) {
			MyLinkedList<Entry<K,V>> list = new MyLinkedList<Entry<K,V>>();
			list.addWithHead(t);
			content.setValueOf(list,a);
		}
		else {
			//相同key值的hashCode()也相等，所以同一key值的Entry必然在位置为a的链表上!
			MyLinkedList<Entry<K,V>> list = content.getValueOf(a);
			if(!list.replace(t,t))
				list.addWithHead(t);
		}
		count++;
	}
	
	/**
	 * 指定key值返回其值 
	 * @param key
	 * @param defaulted
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public V getValue(Object key,V defaulted) {
		Entry<Object,V> tar = new Entry<Object,V>(key);
		int a = Math.abs(tar.hashCode())%content.getCapacity();
		MyLinkedList<Entry<K,V>> list = content.getValueOf(a);
		if(list == null)return defaulted;
		tar = (Entry<Object, V>) list.get(tar);
		return tar == null?null:(V)tar.value;
	}

	public boolean remove(K key) {
		Entry<K,V> tar = new Entry<K,V>(key);
		int a = Math.abs(tar.hashCode())%content.getCapacity();
		MyLinkedList<Entry<K,V>> list = content.getValueOf(a);
		return list==null? false:list.remove(tar);
	}
	
	public static void main(String[] args) {
		MyMap<Integer,String> m = new MyMap<Integer,String>(100);
		m.put(0,"Cedricxs");
		m.put(1, "Aline");
		m.put(0, "AAA");
		System.out.println(m.getValue(0,"don't exsit"));
		System.out.println(m.getValue(2, "don't exsit"));
		System.out.println(m.getValue("1","don't exsit"));
	}
}


class Entry<K,V>{
	Object key;
	Object value;
	
	public Entry(K key,V value) {
		this.key = key;
		this.value = value;
	}
	public Entry(K key) {
		this.key = key;
		this.value = null;
	}
	public String toString() {
		return new String("("+key+","+value+")");
	}
	
	public boolean equals(Object any) {
		if(this == any)return true;
		if(any instanceof Entry) {
			@SuppressWarnings("unchecked")
			Entry<K,V> t = (Entry<K,V>)any;
			if(t.key == this.key)
				return true;
		}
		return false;
	}
	public int hashCode() {
		return key.hashCode();
	}
}