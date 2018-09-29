package Pro;
/**
 * 测试封装：
 * 		private	           在本类中
 * 		default		在本类及本包中
 * 		protected	在本类、本包及子类中
 * 		public		在所有类中
 * @author 安迪
 *
 */
public class TestEncapsulation {
	public static void main(String[] argvs) {
		Human h = new Human(20);
//		System.out.println(h.age);     //在不同的类中，private is not visible
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
		this.name = "cedricxs";           //name为protected 权限，子类可使用
	}
}
