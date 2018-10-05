package Pro;
import java.io.Serializable;

/**
 * ����
 * @author ����
 *
 *����ĳ�ʼ��ʱ����ʼ����̬�������ַ�����������̬����
 *�ڶ����ʵ����ʱ�����ù��캯��
 *
 *�������������ڱ�����ʱ����ջ�ϸ��仮��һ���ڴ棬����ֱֵ�Ӵ洢��ջ�ϡ�
 *�������������ڱ�����ʱ������Ҫ��ջ�ϸ������ã����������һ���ڴ棬
 *������ľ�����Ϣ���洢�ڶ��ڴ��ϣ�Ȼ����ջ���������ָ����ж���ĵ�ַ��
 */
import Pro.Computer;
public class Student implements Comparable<Student>,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//��Ա����
	String name;
	int age;
	String sno;
	Computer comp;
	
	static {                                   //static����������Ĳ��������ڣ������ڶѷ�����
											   //�����ڳ�ʼ����ľ�̬��Ա����
		System.out.println("��ĳ�ʼ������");
	}
	
	//���캯��
	Student(){
		name = "cedric";
		age = 18;
		sno = "15020022003";
		getComputer();
		System.out.println("ѧ�������ʼ����ϣ�");
	}
	
	Student(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	//����
	void prinfo() {
		System.out.println(this.name+"\n"+this.age+"\n"+this.sno+"\n"+this.comp.brand);
	}
	
	void getComputer(){
		Computer c = new Computer();
		comp = c;
	}
	void study(){
		System.out.println("������ѧϰ���õĵ��ԣ�"+comp.brand);
	}
	
	void play(){
		System.out.println("��������������ҫ");
	}
	
	/**
	 * 
	 */
	public Student clone() {
		return new Student();
	}
	//��ǰ������
	public static void main(String[] argvs) {
		Student s = new Student();
		s.prinfo();
		s.study();
		s.play();
		s.comp.changeBrand("����");
		s.comp.changeValue(1234);
		s.comp.prinfo();
		System.out.println(s);

		Student r = s.clone();
		System.out.println(r);
		Student s1 = new Student("Aline",22);
		System.out.println(s1.compareTo(s));
	}


	@Override
	public int compareTo(Student o) {
		if(this.age == o.age)return 0;
		if(this.age>o.age)return 1;
		else return -1;
	}
}