package Pro;

/**
* ��ʹ�÷��ͣ���Object��ΪԪ������ʱ��һ���������Դ����������͵�Ԫ�أ�
* ȱ������Ҫ�ֶ�����ת�������в������������ת������
* ����������ָ��Ԫ�����ͣ�ȱ����һ������ֻ������ָ������
* �ŵ��Ǳ������Զ�����ת������ȫ����������ʣ�ֻ�����ָ��Ԫ�����;Ϳ���ʹĳ����������ĳ��Ԫ��
* ����������
* 		T type
* 		K key V value
* 		E element
* @author ����
*
*/

public class TestFanXing {

	/**
	 * ���ͷ���
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
 * �����࣬������ʱָ����������
 * @author ����
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
 * �̳з����࣬����ķ��ͷ�ΧҪ���ڵ��ڸ���
 * @author ����
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
 * �̳�ʱ��������ķ���
 * @author ����
 *
 * @param <T>
 */
class test5 extends test1{
	test5(){
		
	}
}


