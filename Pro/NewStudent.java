package Pro;            //���������Pro���У�һ��Ҫ��

import Pro.Student;
/**
 * �������override
 * @author ����
 *
 */
public class NewStudent extends Student{
	
	NewStudent(){
		super();								//�����ȵ��ø���Ĺ��캯��super(),�ۻ�ľʽ����
		System.out.println("���๹�캯��������ϣ�"); 
	}
	NewStudent(String name,int age){
		super(name,age);
	}
	void study() {                             //���Ǹ����study()����
		System.out.println("��ѧ�����ѧϰ������");
		super.study();                    //��������super.���ø����ͬ������
	}
	
	public String toString() {                    //���ʱ����toString������override toString()
		return (this.name+" "+this.age);
	}
	
	public static void main(String[] argvs) {
		NewStudent ns = new NewStudent();
		ns.study();
		System.out.println(ns/*.toString()*/);
	}
}
