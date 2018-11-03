package TP2;

public class Jeu {
	Parcours[] parcours;
	int PlayerNum;
	Pion[] players;
	Dice dice;
	Pion gagnant;
	PaquetCartes cur;
	PaquetCartes another;
	//the number of "or" to win the game
	int m;
	Parcours currentParcour;
	public Jeu(Dice dice,Parcours[] parcours,int playerNum,int carteNum) {
		this.dice = dice;
		this.parcours = parcours;
		this.PlayerNum = playerNum;
		players = new Pion[this.PlayerNum];
		for(int i=0;i<players.length;i++) {
			players[i] = new Pion("p"+(i+1));
		}
		cur = new PaquetCartes(carteNum);
		another = new PaquetCartes();
	}
	public void partieWithManyParcours() {
		for(int i =0;i<parcours.length;i++) {
			m += parcours[i].totalOr/PlayerNum-1;
			currentParcour = parcours[i];
			partie();
			//set all player to position 0 and start next parcour
			for(int j=0;j<PlayerNum;j++) {
				players[j].reStart();
			}
		}
		System.out.println("the final winner is "+gagnant.name+"!!!");
	}
	public void affiche() {
		System.out.println("Le jeu comporte "+currentParcour.N+" cases.");
		System.out.println("Le gagnant doit avoir au moins "+m+" pieces.");
		System.out.println("Repartition des pieces sur le parcours :");
		System.out.println(currentParcour);
		System.out.println("Step\tJoueur\tPrepos\tCurpos\tGain");
	}
	public void partie() {
		affiche();
		while(!currentParcour.hasRunOver) {
			for(int i=0;i<PlayerNum;i++) {
				//before lance the dice ,get a cart
				if(!currentParcour.hasRunOver) {
					int res = cur.prendUnCarte(another).Action(this,players[i]);
					//if res == -1 means the player don't lance
					//if res == 1means the player lance
					if(res==1) {
						int step = dice.lance();
						merche(players[i],step);
					}
					if(cur.cartes.empty()) {
						PaquetCartes temp = cur;
						cur = another;
						another = temp;
						cur.shuffle();
					}
				}		
			}		
		}
		System.out.println("This Parcour is Over, the gagnant is "+gagnant.name);
	}
	public void merche(Pion player,int step) {
		int desk = step+player.curpos;
		Parcours parcours = currentParcour;
		if(desk>=parcours.N) {
			parcours.cases[parcours.N-1].accueil(player);
			if(player.piècesdeor>=m) {
				player.actualise(parcours.cases[parcours.N-1]);
				currentParcour.hasRunOver = true;
				gagnant = player;
			}
			else {
				player.actualise(parcours.cases[0]);
				parcours.cases[0].accueil(player);
			}
		}
		else{
			parcours.cases[desk-1].accueil(player);
			player.actualise(parcours.cases[desk-1]);	
		}
		System.out.println(step+"\t\t"+player.name+"\t\t"+player.prepos+"\t\t"+player.curpos+"\t\t"+player.piècesdeor);
		System.out.println(currentParcour);
	}
	public static void main(String[] args) {
		//pour init les cases
		Dice dice1 = new Dice(1);
		//pour utilise par player
		Dice dice2 = new Dice(0);
		Parcours []parcours= new Parcours[2];
		for(int i=0;i<2;i++) {
			parcours[i] = new Parcours(dice1, 24);
		}
		Jeu jeu = new Jeu(dice2,parcours,3,4);
		jeu.partieWithManyParcours();
	}
}
