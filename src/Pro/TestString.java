package Pro;
/**
 * ��Java�У��������ͷ�Ϊ�����������ͺ�������������
 * �����������������֣��ַ���ֱ��λ���ڴ�ջ��
 * ������������������ַ�����������λ�ڶ��У���������ջ��
 * @author ����
 *
 */
public class TestString {
	public static void main(String[] args) {
		//��̬����abc�ڷ������У�String a,b��ֱ��ָ�򷽷����õ�ַ
		String a = "abc";
		String b = "abc";
		//String��char[] value����ֵ�����˾�̬����abc
		//���ڶ�����������ͬ��ַ��char[] value,new����������ַ��ͬ��value
		String c = new String("abc");
		String d = new String("abc");
		System.out.println(a == b);
		System.out.println(c == d);
		System.out.println(c.equals(d));
	}
}
