package TP3;

public class Jeu {
	Dparcours parcours;
	int PlayerNum;
	Pion[] players;
	Dice dice;
	boolean running = true;
	Pion gagnant;
	public Jeu(Dice dice,Dparcours dparcours,int playerNum) {
		this.dice = dice;
		this.parcours = dparcours;
		this.PlayerNum = playerNum;
		players = new Pion[this.PlayerNum];
		for(int i=0;i<players.length;i++) {
			players[i] = new Pion("p"+(i+1));
		}
		
	}
	public void partie() {
		System.out.println("Le jeu comporte "+parcours.N+" cases.");
		System.out.println("Le gagnant doit avoir au moins 21 pieces.");
		System.out.println("Repartition des pieces sur le parcours :");
		System.out.println(parcours);
		System.out.println("De\tJoueur\tPrepos\tCurpos\tGain");
		while(running) {
			for(int i=0;i<players.length;i++) {
				if(running)run(players[i]);
			}
			
		}
		System.out.println(gagnant.name+" win!");
	}
	public void run(Pion player) {
		if(player.bloqué) {
			player.getDebloqué();
			return ;
		}
		int step = dice.lance();
		int desk = step+player.curpos;
		if(player.curpos+step>=parcours.N) {
			parcours.cases[parcours.N-1].accueil(player);
			//m = parcours.totalOr/players.length-1
			if(player.piècesdeor>=parcours.totalOr/players.length-1) {
				player.actualise(parcours.cases[parcours.N-1]);
				gagnant = player;
				running = false;
			}
			else {
				player.actualise(parcours.cases[0]);
				parcours.cases[0].accueil(player);
			}
		}
		else{
			player.actualise(parcours.cases[desk-1]);	
			parcours.cases[desk-1].accueil(player);
			
		}
		System.out.println(step+"\t"+player.name+"\t\t"+player.prepos+"\t\t"+player.curpos+"\t\t"+player.piècesdeor);
	}
	public static void main(String[] args) {
		Dice dice1 = new Dice(1);
		Dice dice2 = new Dice(0);
		Dparcours parcours = new Dparcours(dice1, 24);
		Jeu jeu = new Jeu(dice2,parcours,2);
		jeu.partie();
	}
}
