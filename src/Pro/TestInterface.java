package Pro;

/**
 * 接口，比抽象类还抽象，只有常量和方法的声明，没有实现
 * 用于将定义与实现分离，实现分工合作
 * 实现接口的抽象方法的方式为使用implenents声明实现类
 * 在此应对照抽象类，与抽象类本质与用法上没有区别
 * @author 安迪
 *
 */
//接口总是用public修饰的,加不加public都一样
public interface TestInterface {
	//接口中的常量总是public static final修饰的
	String name = "可飞行物";
	int MAX_SPEED = 7800;
	int MIN_HEIGHT = 1;
	//接口中的方法总是public abstract修饰的
	void fly();
	void stop();
	
}

/**
 * 用implenents修饰符实现接口，必须实现所有接口中声明的方法，
 * 也可同时实现多个接口
 * @author 安迪
 *
 */
class SomeFlyable implements TestInterface{


	@Override
	public void fly() {
		System.out.println("飞机正在飞！");
		
	}

	@Override
	public void stop() {
		System.out.println("飞机降落了！");
		
	}
	
}
