package Test;

public class DAY4 {
	public static void main(String[] args) {
		Ensemble e = new Ensemble(100);
		e.add(1);
		e.add(2);
		e.add(3);
		e.add(1);
		System.out.println(e);
		Tableau set = new Tableau();
		set.setFiltre(new Filtre());
		set.ajoute(5);set.ajoute(10);set.ajoute(15);set.ajoute(20);
		System.out.println(set);
		set = new Tableau();
		set.setFiltre(new Intervalle(6, 16));
		set.ajoute(5);set.ajoute(10);set.ajoute(15);set.ajoute(20);
		System.out.println(set);
		set = new Tableau();
		set.setFiltre(new Mul(3));
		set.ajoute(3);set.ajoute(7);set.ajoute(9);
		System.out.println(set);
	}
}
class Tableau{
	protected int[] tab;
	protected int n;
	Filtre filtre;
	public Tableau(int size) {
		tab = new int[size];
		n=0;
	}
	public Tableau() {
		this(16);
	}
	public void setFiltre(Filtre filtre) {
		this.filtre = filtre;
	}
	public void add(int val) { 
		tab[n++]=val;
	}
	public int get(int index) {
		return tab[index];
	}
	public int size() {
		return n;
	}
	public void ajoute(int val) {
		if(filtre.isSatisfity(val)) {
			this.add(val);
		}
	}
	public String toString() {
		String res = "";
		for(int i=0;i<n;i++) {
			res+=tab[i]+" ";
		}
		return res;
	}
}

class Ensemble extends Tableau{

	public Ensemble(int size) {
		super(size);
	}
	public void add(int val) {
		if(!contain(val)) {
			super.add(val);
		}
	}
	boolean contain(int val) {
		for(int i=0;i<this.size();i++) {
			if(tab[i]==val)return true;
		}
		return false;
	}
	public String toString() {
		String res = "";
		for(int i=0;i<this.size();i++) {
			res+=tab[i]+" ";
		}
		return res;
	}
}

class Filtre{
	
	public boolean isSatisfity(int val) {
		return true;
	}
	
}
class Intervalle extends Filtre {
	int min;
	int max;
	public Intervalle(int min,int max) {
		this.max = max;
		this.min = min;
	}
	public boolean isSatisfity(int val) {
		if(min<=val&&val<=max)return true;
		return false;
	}	
}
class Mul extends Filtre{
	int num;
	public Mul(int num) {
		this.num = num;
	}
	public boolean isSatisfity(int val) {
		if(val%num==0)return true;
		return false;
	}
}
