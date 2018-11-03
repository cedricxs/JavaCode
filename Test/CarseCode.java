package Test;

public class CarseCode {
	public static void main(String[] args) {
		String s1 = "CHINE";
		String s2 = "AZ";
		System.out.println(enCode(s1,2));
		System.out.println(enCode(s2,2));
		System.out.println(deCode("CB",2));
		
		System.out.println(-3%26);
	}
	
	public static String enCode(String msg,int Offset) {
		StringBuilder res = new StringBuilder();
		for(int i=0;i<msg.length();i++) {
			char t = msg.charAt(i);
			if('a'<=t&&'z'>=t) {
				t = (char) ('a'+(t+Offset-'a')%26);
			}
			else if('A'<=t&&'Z'>=t) {
				t = (char) ('A'+(t+Offset-'A')%26);
			}
			res.append(t);
		}
		return res.toString();
	}
	
	public static String deCode(String msg,int Offset) {
		StringBuilder res = new StringBuilder();
		for(int i=0;i<msg.length();i++) {
			char t = msg.charAt(i);
			if('a'<=t&&'z'>=t) {
				t = (char) ('a'+(t-Offset-'a')%26);
			}
			else if('A'<=t&&'Z'>=t) {
				t = (char) ('A'+(t-Offset-'A')%26);
			}
			res.append(t);
		}
		return res.toString();
	}
}
