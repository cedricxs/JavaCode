package Pro;

/**
 * 可以把基本数据类型转化为类处理
 * 自动装箱和拆箱
 * 可用Object引用
 * @author 安迪
 *
 */
public class TestWrapAndUnwrap {
	public static void main(String[] args) {
		Integer i = 10;   //自动装箱
		System.out.println(i);
		Object obj = new Integer(100);
		int a = (int) obj;  //Object->Integer->自动拆箱
		System.out.println(a);
		System.out.println(new Integer(new String("979581491".getBytes())));
	}
}
