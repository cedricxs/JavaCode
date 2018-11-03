package TP4;

//the context of the running task stack
//any operator could change the context
public class Contexte{
	//record Variables' references
	static String[] references = new String[26];
	//if task is running
	static boolean Running;
	//the sequence of instructions
	static String[] instructions;
	//the program pointer
	static int currentpos;
	//if is to query
	static boolean isQuery;
	//record the left-hand 
	static Variable leftHand;
}