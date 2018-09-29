package Pro;

/**
 * String:不可变字符序列(直接引用的char[] value地址值不可更改，但内容可以更改，只是String类中没暴露此方法
 * 可变字符序列。StringBuilder(线程不安全，效率高),StringBuffer(线程安全，效率低)
 * @author 安迪
 *
 */
public class Stringbuilder {
	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
		sb.delete(3, 5);
		System.out.println(sb);
		sb.reverse();
		System.out.println(sb);
		System.out.println(sb.getClass());
		//用法同StringBuilder
		StringBuffer sbuf = new StringBuffer();
		
	}
}
