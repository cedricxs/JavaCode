package Pro;
/**
 * ���Զ�̬
 * @author ����
 * java��̬�Ĺؼ����ڸ����������࣬���Ե�����Ӧ�������������
 * 
 * �ǳ���Ҫ!!!
 * ����������࣬��ʹ�ø���������������ʱ������ҲΪ���࣡
 * ���Ǳ�������Ȼ��ΪΪ�������ͣ����ɵ����������з�����
 */
public class TestPolym {

	public static void main(String[]argvs) {
		Animal a = new Animal();
		Dog b = new Dog();
		Cat c = new Cat();
		Animal d = new Dog();                //����������࣬��ʹ�ø���������������ʱ������ҲΪ���࣡
											//���Ǳ�������Ȼ��ΪΪ�������ͣ����ɵ����������з�����
		System.out.println(d.getClass()); 
		getCry(a);
		getCry(b);
		getCry(c);
	}
	
	static void getCry(Animal any) {
		any.cry();
	}
}

class Animal{
	void cry() {
		System.out.println("����һ����");
	}
}

class Dog extends Animal{
	void cry() {
		System.out.println("������!");
	}
}

class Cat extends Animal{
	void cry() {
		System.out.println("������!");
	}
}