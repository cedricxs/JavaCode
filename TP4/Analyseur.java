package TP4;


//Le rôle de l'analyseur est de calculer l'expression de droite du symbole d'affectation
public class Analyseur{
	//running stack
	Pile pile;
	public Analyseur(Pile pile) {
		this.pile = pile;		
	}
	private void init() {
		//init the running stack context
		Contexte.Running = true;
		Contexte.currentpos = 0;
		Contexte.leftHand = null;
		Contexte.instructions = null;
		Contexte.isQuery = false;
		pile.clear();
	}
	public void Analysis(String str) {
		init();
		if(str.indexOf("=")>-1) {
			Contexte.leftHand = new Variable(str.charAt(0));
			String instructions = str.substring(str.indexOf("= ")+2);	
			Contexte.instructions = instructions.split(" ");
			//if the instructions is not to query the variable
			//we don't need calculate the value,just record the reference
			if(str.indexOf("?")==-1) {
				Contexte.references[Contexte.leftHand.name-'a'] =instructions;
				return ;
			}
		}
		else {
			Contexte.instructions = str.split(" ");
		}
		run();
	}
	public Expression create(String token) { 
		Expression res = null;
		switch(token){
			case "*":
				res = new Mul();
				break;
			case "+":
				res = new Add();
				break;
			case "-":
				res = new Sub();
				break;
			case "/":
				res = new Div();
				break;
			case "?":
				res = new Query();
				break;
			default:
				try{
					//token is an integer
					int val  = Integer.parseInt(token);
					res = new Constante(val);
				}catch(NumberFormatException e) {
					//token is a variable
					res = new Variable(token.charAt(0));
				}
				break;
		}
		return res;
	}
	public void run() {
		for(;Contexte.currentpos<Contexte.instructions.length&&Contexte.Running;Contexte.currentpos++) {
			if(Contexte.instructions[Contexte.currentpos].length()>0)
				create(Contexte.instructions[Contexte.currentpos]).update(pile);
			System.out.println(pile);
		}
		//if query the value of variable,print it
		if(Contexte.isQuery) {
			Constante val = (Constante)pile.depile();
			System.out.println(Contexte.leftHand.name+" = "+val.val);
		}
	}
}
