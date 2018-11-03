package Exercise;

public class E2 {
	public static void main(String[] args) {
		int r = 1000;
		int b = 1000;
		int res = 0;
		for(int a=1;a<1000;a++) {
			while(a*a+b*b>r*r) {
				b--;
			}
			res+=b;
		}
		System.out.println(4*res);
	}
}
