package Pro;
/**
 * 测试多态
 * @author 安迪
 * java多态的关键在于父类引用子类，可以调用相应子类的重名方法
 * 
 * 非常重要!!!
 * 父类兼容子类，即使用父类声明，在运行时其类型也为子类！
 * 但是编译器仍然认为为声明类型，不可调用子类特有方法！
 */
public class TestPolym {

	public static void main(String[]argvs) {
		Animal a = new Animal();
		Dog b = new Dog();
		Cat c = new Cat();
		Animal d = new Dog();                //父类兼容子类，即使用父类声明，在运行时其类型也为子类！
											//但是编译器仍然认为为声明类型，不可调用子类特有方法！
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
		System.out.println("叫了一声！");
	}
}

class Dog extends Animal{
	void cry() {
		System.out.println("汪汪汪!");
	}
}

class Cat extends Animal{
	void cry() {
		System.out.println("喵喵喵!");
	}
}