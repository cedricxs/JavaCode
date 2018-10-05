package Pro;

/**
 * ��дmap
 * �ײ�ʵ�֣�����+����
 * ������дhasCode()��equals()
 * ����equals()==true������Ԫ��hashCode()һ�����!!!!
 * ���Լ���ظ�ֻ����һ��������
 * @author ����
 * ��д����ʱ��Ҳ�ɽ�Entry<Object,Object>��ֻ���Ⱪ¶K��V���͵�put()����������
 *
 */
public class MyMap<K,V> {
	
	//ʢ������
	final MyArrayList<MyLinkedList<Entry<K,V>>> content;
	int count;
	/**
	 * ��ʼ�����鳤�ȣ�һ����ʼ�������Ȳ��ٱ�
	 * @param size
	 */
	
	MyMap(){
		this(16);
	}
	
	
	MyMap(int size){
		content = new MyArrayList<MyLinkedList<Entry<K,V>>>(size);
	}
	
	/**
	 * ��Keyֵ�Ĺ�ϣ��ȷ��λ�ã���ʵ���ظ��򸲸�
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
			//��ͬkeyֵ��hashCode()Ҳ��ȣ�����ͬһkeyֵ��Entry��Ȼ��λ��Ϊa��������!
			MyLinkedList<Entry<K,V>> list = content.getValueOf(a);
			if(!list.replace(t,t))
				list.addWithHead(t);
		}
		count++;
	}
	
	/**
	 * ָ��keyֵ������ֵ 
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