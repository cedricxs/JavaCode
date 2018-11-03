package TP3;

import java.util.Random;

public class Dice {
	Random dice;
	public Dice(long seed) {
		dice = new Random(seed);
	}
	public int lance() {
		return dice.nextInt(6)+1;
	}

	public static void main(String[] args) {
		Dice e = new Dice(1);
		System.out.println(e.lance());
	}
}
