package Pro;

/**
 * ���԰ѻ�����������ת��Ϊ�ദ��
 * �Զ�װ��Ͳ���
 * ����Object����
 * @author ����
 *
 */
public class TestWrapAndUnwrap {
	public static void main(String[] args) {
		Integer i = 10;   //�Զ�װ��
		System.out.println(i);
		Object obj = new Integer(100);
		int a = (int) obj;  //Object->Integer->�Զ�����
		System.out.println(a);
		System.out.println(new Integer(new String("979581491".getBytes())));
	}
}
