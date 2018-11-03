package Exercise;

public class E1 {
	public static void main(String[] args) {
		int m = 5;
		int d = 4;
		int res = 0;
		for(int i=1;i<m;i++) {
			switch(i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: res+=31;
				break;
			case 2:
				res+=29;
				break;
			case 4:
			case 6:
			case 9:
			case 11:res+=30;
				   break;
			}
		}
		res+=d;
		System.out.println(res);
	}
}
