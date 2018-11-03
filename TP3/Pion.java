package TP3;

public class Pion {
	String name;
	int prepos;
	int curpos;
	int piècesdeor;
	boolean bloqué;
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
	public void getBloqué() {
		System.out.println("Oh no,"+this.name+" get Bloqué!!!");
		
		this.bloqué = true;
	}
	public void getDebloqué() {
		System.out.println("Oh Yeah,"+this.name+" get Debloqué!!!");
		this.prepos = this.curpos;
		this.bloqué = false;
	}
}
