package Pro;
import java.util.Scanner;
/**
 * ����switch
 * @author ����
 *
 */
public class TestSwitch {
	
	public static void main(String[] argvs) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������һ������:");
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
