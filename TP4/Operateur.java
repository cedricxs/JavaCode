package TP4;

public abstract class Operateur implements Expression{
	String oper;
}
class Add extends Operateur{
	public Add() {
		this.oper="+";
	}
	@Override
	public void update(Pile pile) {
		Constante a = (Constante)pile.depile();
		Constante b = (Constante)pile.depile();
		pile.empile(new Constante(a.val+b.val));
	}
	
}
class Mul extends Operateur{
	public Mul() {
		this.oper = "*";
	}
	@Override
	public void update(Pile pile) {
		Constante a = (Constante)pile.depile();
		Constante b = (Constante)pile.depile();
		pile.empile(new Constante(a.val*b.val));
	}
	
}
class Sub extends Operateur{
	public Sub() {
		this.oper = "-";
	}
	@Override
	public void update(Pile pile) {
		Constante b = (Constante)pile.depile();
		Constante a = (Constante)pile.depile();
		pile.empile(new Constante(a.val-b.val));	
	}
	
}
class Div extends Operateur{
	public Div() {
		this.oper = "/";
	}
	@Override
	public void update(Pile pile) {
		Constante b = (Constante)pile.depile();
		Constante a = (Constante)pile.depile();
		pile.empile(new Constante(a.val/b.val));
	}
	
}
class Query extends Operateur{
	public Query() {
		this.oper="?";
	}
	@Override
	public void update(Pile pile) {
		Contexte.isQuery = true;
		//modified the sequence of instructions as the reference
		//reset the program pointer to -1
		Contexte.instructions = Contexte.references[Contexte.leftHand.name-'a'].split(" ");
		Contexte.currentpos = -1;	
	}
}

