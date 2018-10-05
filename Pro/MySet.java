package Pro;

/**
 * 重写set,利用map实现
 * 不可重复，利用map键的不可重复实现
 * @author 安迪
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
