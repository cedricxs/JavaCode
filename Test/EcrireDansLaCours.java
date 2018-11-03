package Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class EcrireDansLaCours{
	
	public static void main(String[] args) {
		
		EX2();
		
	}
	public static void EX1() {
		Scanner scanner = new Scanner(System.in);
		String data = scanner.nextLine();
		scanner.close();
		String d = data.substring(0, data.indexOf("."));
		String[] qw=d.split(" ");
		int leg=0;int c=0;
		for(int i=0;i<qw.length;i++) {
			if(qw[i].length()>leg) {
				leg=qw[i].length();
				c=i;
			}
		}
		System.out.println("Taille="+leg+" Rang="+(c+1));
	}
	public static void EX2() {
		int [] arr = {1,2,3,4,5,6,6,4,2,10};
		Set<Integer>set = new HashSet<Integer>();
		for(int i=0;i<arr.length;i++) {
			set.add(arr[i]);
		}
		int[] res = new int[set.size()];
		int i=0;
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			res[i++] =(int)it.next();
		}
		System.out.println(Arrays.toString(res));
	}


}
