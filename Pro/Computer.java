package Pro;

import java.io.Serializable;

/**
 * 外部引用类和instanceof
 * @author 安迪
 *
 */
public class Computer extends Object implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String brand;
	int value;
	
	Computer(){
		brand = "联想";
		value = 9999;
		System.out.println("电脑对象初始化完毕！");
	}
	
	void changeBrand(String b) {
		brand = b;
	}
	
	void changeValue(int val) {
		value = val;
	}
	
	public void prinfo() {
		System.out.println("电脑品牌："+this.brand+"电脑价格："+this.value);
	}
	
	public static void main(String[]argvs) {
		Computer comp = new Computer();
		System.out.println(comp.brand+"这是电脑的主函数!");
		System.out.println(comp instanceof Computer);
	}
}
