package Pro;

/**
 * �׳��쳣����ֹ�����߳�����
 * @author ����
 * finally�ܻ����У�������finally����ʱ����ʹ��try���Ѿ���������ֵ��Ҳֻ�Ƕ�Ϊ����ֵ,�ᱻfinally�еķ���ֵ����
 * ��finally��Ϊ��������ֵ�ŷ���try�еķ���ֵ
 *
 */
public class TestException {
	public static void main(String[] args) {
		
		try {
			//throw new Exception();
		}catch(Exception e) {
			e.printStackTrace();
			//�˷�����ӡ�������쳣�Ķ�ջ(��������)���,������ֹ���߳�����
		}finally {
			System.out.println("�쳣�������ˣ�");		
		}
		
		try {
			add();
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
		String str = new String("123");
		char c = str.charAt(0);
		System.out.println(c);
		System.out.println(mytry());
	}
	//�����׳����Կ��ܳ��ֵ��쳣���ڱ��˵���ʱ����try/catch����������׳��쳣
	public static void add() throws ArithmeticException{
		int x = 1/0;
	}
	
	public static int mytry() {
		try {
			return 123;
		}catch(Exception e){
			
		}
		finally {
			System.out.println("finally");
		}
		return 0;
	}
}
