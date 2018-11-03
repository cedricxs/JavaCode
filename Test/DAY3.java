package Test;

import java.util.Arrays;
import java.util.Scanner;

public class DAY3 {
	public static void main(String[] args) {
		Syracuse obj = new Syracuse(10);
		System.out.print(obj.premier()+" ");
		while(obj.existeSuivant()) {
			System.out.print(obj.suivant()+" ");
		}
		GuessWord game = new GuessWord("String");
		game.start();
	}
}

class GuessWord{
	private String word;
	private char[] guess;
	private boolean running;
	private Scanner in;
	private int numcorrect ;
	public GuessWord(String word) {
		this.word = word;
		guess = new char[word.length()];
		for(int i=0;i<word.length();i++)
		guess[i]= '_';
		in = new Scanner(System.in);
		running = true;
		numcorrect = 0;
	}
	public void start() {
		while(running) {
			System.out.println("请猜单词中的字母:");
			char w  = in.nextLine().charAt(0);
			for(int i=0;i<word.length();i++) {
				if(word.charAt(i)==w) {
					guess[i]=w;
					numcorrect++;
				}
			}
			System.out.println(Arrays.toString(guess));
			if(numcorrect==word.length())running=false;
		}
	}
}
class Syracuse{
	private int cur;
	private int pre;
	public Syracuse(int n) { 
		cur = n;
		pre = n;
	}
	public int premier() {
		return pre;
	}
	
	public int suivant() {
			if(cur%2==0) {
				this.cur = cur/2; 
			}
			else {
				this.cur = cur*3+1;
			}
			return this.cur;
	}
	public boolean existeSuivant() {
		if(cur == 1)
		return false;
		else return true;
	}
}