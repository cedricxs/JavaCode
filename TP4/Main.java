package TP4;

public class Main {
	public static void main(String[] args) {
		Analyseur a = new Analyseur(new Pile());
		a.Analysis("b = 2 4 *");
		a.Analysis("a = 3 4 * b +");
		a.Analysis("a = ?");
	}
}
