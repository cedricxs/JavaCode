package Pro;
/**
 * ���Է�װ��
 * 		private	           �ڱ�����
 * 		default		�ڱ��༰������
 * 		protected	�ڱ��ࡢ������������
 * 		public		����������
 * @author ����
 *
 */
public class TestEncapsulation {
	public static void main(String[] argvs) {
		Human h = new Human(20);
//		System.out.println(h.age);     //�ڲ�ͬ�����У�private is not visible
		System.out.println(h.id);
	}
}


class Human {
	private int age;
	protected String name;
	public int id;
	Human(int age){
		this.age = age;
		id = 10;
	}
	void gtage() {
		System.out.println(age);
	}
}

class Stu extends Human{
	Stu(int age){
		super(age);
		this.name = "cedricxs";           //nameΪprotected Ȩ�ޣ������ʹ��
	}
}
