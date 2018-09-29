package Pro;


public class TestRecursion {
	
	public static void main(String[] argvs) {
		
		long start0 = System.currentTimeMillis();
		int res0 = factorial(10);
		long end0 = System.currentTimeMillis();
		System.out.println("递归结果："+res0+"\n递归耗时："+(end0-start0));
		
		long start1 = System.currentTimeMillis();
		int res1 = factorialLoop(10);
		long end1 = System.currentTimeMillis();
		System.out.println("递归结果："+res1+"\n递归耗时："+(end1-start1));
		
		Loop();
	}
	
	//递归由递归头和递归体组成
	//递归头：决定递归何时结束
	//递归体：决定何时调用自己
	static int factorial(int n) {
		if(n<=1) {
			return 1;         //递归头
		}
		else {
			return n*factorial(n-1);     //递归体
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
