package TP2;

import java.util.Collections;
import java.util.Stack;

public class PaquetCartes {
	Stack<Carte> cartes;
	static String[] ActionName = {"puits","flèche","pont","mendiant"};
	public PaquetCartes() {
		cartes = new Stack<Carte>();
	}
	//C est la nombre de carte
	public PaquetCartes(int C) {
		cartes = new Stack<Carte>();
		for(int i=0;i<C;i++) {
			cartes.add( new Carte(ActionName[i%4]));
		}
		Collections.shuffle(cartes);
	}
	//obtenir une carte dans current Paquet, et met la carte autre Paquet
	public Carte prendUnCarte(PaquetCartes another) {
		Carte res = cartes.pop();
		another.cartes.add(res);
		return res;
	}
	public void shuffle() {
		Collections.shuffle(this.cartes);
	}
	
}
