package Pro;
/**
 * 父类
 * @author 安迪
 *
 *在类的初始化时，初始化静态变量，字符串常量，静态代码
 *在对象的实例化时，调用构造函数
 *
 *基本数据类型在被创建时，在栈上给其划分一块内存，将数值直接存储在栈上。
 *引用数据类型在被创建时，首先要在栈上给其引用（句柄）分配一块内存，
 *而对象的具体信息都存储在堆内存上，然后由栈上面的引用指向堆中对象的地址。
 */
import Pro.Computer;
public class Student {
	
	//成员变量
	String name;
	int age;
	String sno;
	Computer comp;
	
	static {                                   //static变量随着类的产生而存在，存在于堆方法区
		System.out.println("类的初始化！！");
	}
	
	//构造函数
	Student(){
		name = "cedric";
		age = 18;
		sno = "15020022003";
		getComputer();
		System.out.println("学生对象初始化完毕！");
	}
	
	
	//方法
	void prinfo() {
		System.out.println(this.name+"\n"+this.age+"\n"+this.sno+"\n"+this.comp.brand);
	}
	
	void getComputer(){
		Computer c = new Computer();
		comp = c;
	}
	void study(){
		System.out.println("我正在学习，用的电脑："+comp.brand);
	}
	
	void play(){
		System.out.println("我正在玩王者荣耀");
	}
	
	//当前主函数
	public static void main(String[] argvs) {
		Student s = new Student();
		s.prinfo();
		s.study();
		s.play();
		s.comp.changeBrand("戴尔");
		s.comp.changeValue(1234);
		s.comp.prinfo();
	}
}
