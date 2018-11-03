package TP2;

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
