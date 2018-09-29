package Pro;
import java.util.Scanner;
/**
 * 测试switch
 * @author 安迪
 *
 */
public class TestSwitch {
	
	public static void main(String[] argvs) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入一个数字:");
		int a = scanner.nextInt();
		switch(a) {
		case 0:
		case 1:
			System.out.println(1);
			break;
		case 2:
			System.out.println(2);
			break;
		default:
			break;
		}
		scanner.close();
	}
}
