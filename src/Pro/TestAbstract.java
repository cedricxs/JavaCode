package Pro;

/**
 * ����������������󷽷���Ҳ��ֱ��ʵ�ַ�����û�нӿ�interface����
 * ����ʵ�ֳ��󷽷��ķ�ʽΪ����̳���д,������д(ʵ��)�����������г��󷽷�
 * �ڴ�Ӧ���սӿ�
 * һ��.java�ļ��У�ֻ����һ��public��
 * @author ����
 *
 */
public abstract class TestAbstract {
	
	//
	abstract void cry();
	void getCry() {
		cry();
	}
	/**
	 * main��������д��Ψһ��public������
	 * @param argvs
	 */
	public static void main(String[]argvs) {
		MyClass mc = new MyClass();
		mc.cry();
		mc.getCry();
	}
}

class MyClass extends TestAbstract{

	@Override
	void cry() {
		System.out.println("I'm crying!");
		
	}
	
}