package Pro;

/**
 * String:���ɱ��ַ�����(ֱ�����õ�char[] value��ֵַ���ɸ��ģ������ݿ��Ը��ģ�ֻ��String����û��¶�˷���
 * �ɱ��ַ����С�StringBuilder(�̲߳���ȫ��Ч�ʸ�),StringBuffer(�̰߳�ȫ��Ч�ʵ�)
 * @author ����
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
		//�÷�ͬStringBuilder
		StringBuffer sbuf = new StringBuffer();
		
	}
}
