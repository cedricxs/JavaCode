package Pro;

/**
 * ��дset,����mapʵ��
 * �����ظ�������map���Ĳ����ظ�ʵ��
 * @author ����
 *
 */
public class MySet<K> {
	MyMap<K,Object> map;
	
	private static final Object PRESENT = new Object();
	public MySet() {
		map = new MyMap<K,Object>();
	}
	
	public void add(K any) {
		map.put(any, PRESENT);
	}
	
	public boolean remove(K any) {
		return map.remove(any);
	}
	public int size() {
		return map.count;
	}
	public static void main(String[] args) {
		MySet<String> ms = new MySet<String>();
		ms.add("123");
		System.out.println(ms.size());
	}
}
