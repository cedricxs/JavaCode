package Pro;
import java.util.Scanner;
/**
 * ����Scanner����
 * @author ����
 * ���������ж�ȡ��Ҫ����������
 */
public class TestScanner {

	public static void main(String[] argvs) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������������:");
		String name = scanner.nextLine();
		System.out.println("�������������:");
		int age = scanner.nextInt();
		System.out.println(name+"\n���������������"+age*365);
		scanner.close();
	}
}
