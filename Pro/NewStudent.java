package Pro;            //将该类放入Pro包中，一定要有

import Pro.Student;
/**
 * 派生类和override
 * @author 安迪
 *
 */
public class NewStudent extends Student{
	
	NewStudent(){
		super();								//总是先调用父类的构造函数super(),累积木式构造
		System.out.println("父类构造函数调用完毕！"); 
	}
	NewStudent(String name,int age){
		super(name,age);
	}
	void study() {                             //覆盖父类的study()方法
		System.out.println("新学生类的学习方法！");
		super.study();                    //但可以用super.调用父类的同名函数
	}
	
	public String toString() {                    //输出时调用toString方法，override toString()
		return (this.name+" "+this.age);
	}
	
	public static void main(String[] argvs) {
		NewStudent ns = new NewStudent();
		ns.study();
		System.out.println(ns/*.toString()*/);
	}
}
