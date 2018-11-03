package TP2;

public class Parcours {
	Case[] cases;
	int N;
	int totalOr;
	boolean hasRunOver;
	public Parcours(Dice dice,int N) {
		cases = new Case[N];
		for(int i=0;i<N;i++) {
			cases[i]=new Case(i+1,dice.lance());
			totalOr+=cases[i].piècesdeor;
		}
		hasRunOver = false;
		this.N = N;
	}
	
	public String toString() {
		String res = "| ";
		for(int i=0;i<cases.length;i++) {
			res+=cases[i].piècesdeor+" | ";
		}
		return res;
	}
}
