package Pro;

/**
 * 抽象类可以声明抽象方法，也可直接实现方法，没有接口interface抽象
 * 并且实现抽象方法的方式为子类继承重写,必须重写(实现)抽象类中所有抽象方法
 * 在此应对照接口
 * 一个.java文件中，只能有一个public类
 * @author 安迪
 *
 */
public abstract class TestAbstract {
	
	//
	abstract void cry();
	void getCry() {
		cry();
	}
	/**
	 * main函数必须写在唯一的public类里面
	 * @param argvs
	 */
	public static void main(String[]argvs) {
		MyClass mc = new MyClass();
		mc.cry();
		mc.getCry();
	}
}

class MyClass extends TestAbstract{

	@Override
	void cry() {
		System.out.println("I'm crying!");
		
	}
	
}