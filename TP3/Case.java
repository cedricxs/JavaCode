package TP3;

public class Case {
	int position;
	int piècesdeor;
	public Case(int position,int piècesdeor) {
		this.piècesdeor = piècesdeor;
		this.position = position;
	}
	public void accueil(Pion p) {
		p.recoitPieces(this.piècesdeor);
		this.piècesdeor = 0;
	}
}
class Prison extends Case{

	public Prison(int position) {
		super(position, 0);
	}
	public void accueil(Pion p) {
		p.getBloqué();
	}
}
class Douane extends Case{
	public Douane(int position) {
		super(position, 0);
	}
	public void accueil(Pion p) {
		System.out.println(p.name+" arrive at Douane, perde l'or!!!");
		//nombre np déterminé par le déplacement en nombre de cases du joueur
		int np = p.curpos-p.prepos;
		if(np>p.piècesdeor)p.recoitPieces(-p.piècesdeor);
		else p.recoitPieces(-np);
	}
}
