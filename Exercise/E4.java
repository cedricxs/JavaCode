package Exercise;

public class E4 {
	public static int binaryGap(int N) {
		int i = (int) Math.pow(2, 30);
		int res = 0;
		int max = 0;
		boolean h  = false;
		while(i>=1) {
			int temp = i&N;
			if(temp==0&&h) {
				res++;
			}
			else if(temp>0&&!h){
				h  = true;
				
			}
			else if (temp>0&&h){
				res++;
				max = Math.max(max, res);
				res = 0;
			}
			i = i>>1;
		}
		return max;
	}
	public static void main(String[] args) {
		System.out.println(binaryGap(6));
	}
}
