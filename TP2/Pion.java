package TP2;

public class Pion {
	String name;
	int prepos;
	int curpos;
	int piècesdeor;
	
	public Pion(String name) {
		this.name = name;
	}
	public void recoitPieces(int np) {
		this.piècesdeor+=np;
	}
	public void actualise(Case c) {
		this.prepos = this.curpos;
		this.curpos = c.position;
	}
	
	public void reStart() {
		this.prepos = 0;
		this.curpos = 0;
	}

}
