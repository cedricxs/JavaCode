package Pro;
import java.util.Scanner;
/**
 * 测试Scanner对象
 * @author 安迪
 * 从输入流中读取想要的数据类型
 */
public class TestScanner {

	public static void main(String[] argvs) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入你的名字:");
		String name = scanner.nextLine();
		System.out.println("请输入你的年龄:");
		int age = scanner.nextInt();
		System.out.println(name+"\n来到地球的天数："+age*365);
		scanner.close();
	}
}
