package Pro;
/**
 * 在Java中，数据类型分为基本数据类型和引用数据类型
 * 基本数据类型如数字，字符，直接位于内存栈中
 * 引用数据类型如对象，字符串，其数据位于堆中，但引用在栈中
 * @author 安迪
 *
 */
public class TestString {
	public static void main(String[] args) {
		//静态常量abc在方法区中，String a,b都直接指向方法区该地址
		String a = "abc";
		String b = "abc";
		//String的char[] value的数值引用了静态常量abc
		//但在堆中有两个不同地址的char[] value,new出了两个地址不同的value
		String c = new String("abc");
		String d = new String("abc");
		System.out.println(a == b);
		System.out.println(c == d);
		System.out.println(c.equals(d));
	}
}
