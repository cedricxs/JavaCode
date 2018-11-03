package TP4;

public interface Expression {
	void update(Pile pile) ;
}

class Constante implements Expression{
	int val;
	public Constante(int val) {
		this.val = val;	
	}
	@Override
	public void update(Pile pile) {
		pile.empile(this);
	}	
}
class Variable implements Expression{
	char name;
	public Variable(char name) {
		this.name = name;
	}
	@Override
	public void update(Pile pile) {
		String[] ins = Contexte.references[name-'a'].split(" ");
		String[] newInstrutions = new String[Contexte.instructions.length-Contexte.currentpos-1+ins.length];
		System.arraycopy(ins, 0, newInstrutions, 0, ins.length);
		System.arraycopy(Contexte.instructions, Contexte.currentpos+1, newInstrutions, ins.length,Contexte.instructions.length-Contexte.currentpos-1);
		//modified the sequence of instructions as the reference
		//reset the program pointer to -1
		Contexte.instructions = newInstrutions;
		Contexte.currentpos = -1;
	}
}