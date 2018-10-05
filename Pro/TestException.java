package Pro;

/**
 * 抛出异常，终止所属线程运行
 * @author 安迪
 * finally总会运行，并且在finally存在时，即使在try中已经声明返回值，也只是定为返回值,会被finally中的返回值覆盖
 * 若finally中为声明返回值才返回try中的返回值
 *
 */
public class TestException {
	public static void main(String[] args) {
		
		try {
			//throw new Exception();
		}catch(Exception e) {
			e.printStackTrace();
			//此方法打印出出现异常的堆栈(函数调用)情况,不会中止主线程运行
		}finally {
			System.out.println("异常被捕获了！");		
		}
		
		try {
			add();
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
		String str = new String("123");
		char c = str.charAt(0);
		System.out.println(c);
		System.out.println(mytry());
	}
	//声明抛出所以可能出现的异常，在别人调用时，需try/catch或继续向上抛出异常
	public static void add() throws ArithmeticException{
		int x = 1/0;
	}
	
	public static int mytry() {
		try {
			return 123;
		}catch(Exception e){
			
		}
		finally {
			System.out.println("finally");
		}
		return 0;
	}
}
