package Pro;

import java.io.Serializable;

/**
 * �ⲿ�������instanceof
 * @author ����
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
		brand = "����";
		value = 9999;
		System.out.println("���Զ����ʼ����ϣ�");
	}
	
	void changeBrand(String b) {
		brand = b;
	}
	
	void changeValue(int val) {
		value = val;
	}
	
	public void prinfo() {
		System.out.println("����Ʒ�ƣ�"+this.brand+"���Լ۸�"+this.value);
	}
	
	public static void main(String[]argvs) {
		Computer comp = new Computer();
		System.out.println(comp.brand+"���ǵ��Ե�������!");
		System.out.println(comp instanceof Computer);
	}
}
