package Pro;

/**
* 不使用泛型：以Object作为元素类型时，一个容器可以储存任意类型的元素，
* 缺点是需要手动类型转换，稍有不慎会出现类型转换错误
* 泛型容器：指定元素类型，缺点是一个容器只能容纳指定类型
* 优点是编译器自动类型转换，安全，提高重用率，只需更改指定元素类型就可以使某个容器容纳某种元素
* 泛型声明：
* 		T type
* 		K key V value
* 		E element
* @author 安迪
*
*/

public class TestFanXing {

	/**
	 * 泛型方法
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static <T extends Comparable<T>> int test2(T a1,T a2){
		return a1.compareTo(a2);
	}
	
	public static void main(String[] args) {
		System.out.println(test2("asd","abc"));
		test1<Integer> t1 = new test1<Integer>();
		t1.t();
	}
}

/**
 * 泛型类，在声明时指定泛型类型
 * @author 安迪
 *
 * @param <T>
 */
class test1<T>{ 
	 T a;
	public T t() {
		return a;
	}
}

/**
 * 继承泛型类，子类的泛型范围要大于等于父类
 * @author 安迪
 *
 * @param <T>
 * @param <T1>
 */
class test3<T> extends test1<String>{
	T b;
	test3(){
	}
}
class test4<T,T1> extends test1<T>{
	test4(){
		
	}
}
/**
 * 继承时擦除父类的泛型
 * @author 安迪
 *
 * @param <T>
 */
class test5 extends test1<String>{
	test5(){
		
	}
}


