package Pro;

/**
 * �ӿڣ��ȳ����໹����ֻ�г����ͷ�����������û��ʵ��
 * ���ڽ�������ʵ�ַ��룬ʵ�ַֹ�����
 * ʵ�ֽӿڵĳ��󷽷��ķ�ʽΪʹ��implenents����ʵ����
 * �ڴ�Ӧ���ճ����࣬������౾�����÷���û������
 * @author ����
 *
 */
//�ӿ�������public���ε�,�Ӳ���public��һ��
public interface TestInterface {
	//�ӿ��еĳ�������public static final���ε�
	String name = "�ɷ�����";
	int MAX_SPEED = 7800;
	int MIN_HEIGHT = 1;
	//�ӿ��еķ�������public abstract���ε�
	void fly();
	void stop();
	
}

/**
 * ��implenents���η�ʵ�ֽӿڣ�����ʵ�����нӿ��������ķ�����
 * Ҳ��ͬʱʵ�ֶ���ӿ�
 * @author ����
 *
 */
class SomeFlyable implements TestInterface{


	@Override
	public void fly() {
		System.out.println("�ɻ����ڷɣ�");
		
	}

	@Override
	public void stop() {
		System.out.println("�ɻ������ˣ�");
		
	}
	
}
