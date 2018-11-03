package TP2;

public class Carte {
	String ActionName;
	public Carte(String name) {
		this.ActionName = name;
	}
	
	//the result of Action is this turn,how many step the player should forward
	//it may be -1,means after get the cart,don't de
	public int Action(Jeu jeu,Pion player) {
		System.out.println(player.name+" get a carte "+this.ActionName+"!!!");
		switch(this.ActionName) {
			case "flèche":
				return ActionOfFlèche(jeu,player);
			case "pont":
				return ActionOfPont(jeu,player);
			case "puits":
				return ActionOfPuits(jeu,player);
			case "mendiant":
				return ActionOfMendiant(jeu,player);
			default:
				break;
		}
		return -1;
	}
	int ActionOfFlèche(Jeu jeu,Pion player) {
		return 1;
	}
	int ActionOfPont(Jeu jeu,Pion player) {
		System.out.println(player.name+" forward 2 step");
		//forward 2 step plus
		jeu.merche(player,2);
		return 1;
	}
	int ActionOfPuits(Jeu jeu,Pion player) {
		//pass the current turn
		return -1;
	}
	int ActionOfMendiant(Jeu jeu,Pion player) {
		if(player.curpos==0)return 1;
		int num = jeu.dice.lance();
		if(player.piècesdeor<num) {
			jeu.currentParcour.cases[player.curpos-1].piècesdeor+=player.piècesdeor;
			player.piècesdeor = 0;
		}
		else {
			jeu.currentParcour.cases[player.curpos-1].piècesdeor+=num;
			player.piècesdeor -= num;
		}
		jeu.merche(player, 1);
		return -1;
	}
	
}


