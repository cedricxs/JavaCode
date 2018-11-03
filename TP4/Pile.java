package TP4;

//task stack
public class Pile {
	private Object[] value;
	private int count;
	public Pile() {
		this(100);
	}
	public Pile(int size) {
		value = new Object[size]; 
		count = 0;
	}
	public boolean estVide() {
		return count==0;
	}
	public boolean isFull() {
		return count==value.length;
	}
	public int size() {
		return count;
	}
	public Object peek() {
		if(!estVide()) {
			return value[count-1];
		}
		return null;
	}
	public Object depile() {
		if(!estVide()) {
			return value[--count];
		}
		return null;
	}
	public void empile(Object obj) {
		if(!isFull()) {
		value[count++] = obj;
		}
	}
	public void clear() {
		count = 0;
	}
	public String toString() {
		if(estVide())return null;
		String res = "";
		for(int i=0;i<count;i++) {
			Constante j = (Constante)value[i];
			res+=j.val+" ";
		}
			
		return res;
	}

}






