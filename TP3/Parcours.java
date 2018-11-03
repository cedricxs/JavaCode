package TP3;

public class Parcours {
	Case[] cases;
	int N;
	int totalOr;
	public Parcours(Dice dice,int N) {
		cases = new Case[N];	
		for(int i=0;i<N;i++) {
			cases[i] = new Case(i+1,dice.lance());
		}
		this.N = N;
	}
	
}
class Dparcours extends Parcours{

	public Dparcours(Dice dice, int N) {
		super(dice, N);
		for(int i=0;i<N;i++) {
			if((i+1)%5==0&&i!=N-1) {
				cases[i] = new Prison(i+1);
			}
			else if((i+1)%11==0&&i!=N-1) {
				cases[i] = new Douane(i+1);
			}
		}

	}
	public String toString() {
		String res = "| ";
		for(int i=0;i<cases.length;i++) {
			if(cases[i] instanceof Prison) {
				res+="P | ";
			}
			else if(cases[i] instanceof Douane) {
				res+="D | ";
			}
			else res+=cases[i].piècesdeor+" | ";
		}
		return res;
	}
	
}
