package Pro;


public class TestRecursion {
	
	public static void main(String[] argvs) {
		
		long start0 = System.currentTimeMillis();
		int res0 = factorial(10);
		long end0 = System.currentTimeMillis();
		System.out.println("�ݹ�����"+res0+"\n�ݹ��ʱ��"+(end0-start0));
		
		long start1 = System.currentTimeMillis();
		int res1 = factorialLoop(10);
		long end1 = System.currentTimeMillis();
		System.out.println("�ݹ�����"+res1+"\n�ݹ��ʱ��"+(end1-start1));
		
		Loop();
	}
	
	//�ݹ��ɵݹ�ͷ�͵ݹ������
	//�ݹ�ͷ�������ݹ��ʱ����
	//�ݹ��壺������ʱ�����Լ�
	static int factorial(int n) {
		if(n<=1) {
			return 1;         //�ݹ�ͷ
		}
		else {
			return n*factorial(n-1);     //�ݹ���
		}
	}
	
	static int factorialLoop(int n) {
		int res = 1;
		while(n>1) {
			res *= n;
			n--;
		}
		return res;
	}
	
	static int count = 10;
	static void Loop() {
		if(count>0) {
			System.out.println(count);
			count--;
			Loop();
		}
		else {
			return ;
		}
	}
}
