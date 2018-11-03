package Test;

public class DAY6 {
	public static void main(String[] args) {
		Element e1=new Element(1); Element e2=new Element(2);
		Element e3=new Element(3); Element e4=new Element(4);
		Element e5=new Element(5);
		Ensemblee v1=new Ensemblee(1000);
		v1.add(e1);
		Ensemblee v2=new Ensemblee(1000);
		v2.add(e2); v2.add(e3); v2.add(e4);
		Ensemblee v3=new Ensemblee(1000);
		v3.add(e5); v3.add(v1); v3.add(v2);
		v3.affiche();
		// affiche {5,{1},{2,3,4}}
		System.out.println(e1.nbreElements()) ; // affiche 1
		System.out.println(v3.nbreElements()) ; // affiche 5
		Ensemblee v4=new Ensemblee(1000);
		v4.add(v3);
		v4.affiche();
		
		
	}
}
class Element{
	int value;
	public Element(int val) {
		this.value = val;
	}
	public void affiche() {
		System.out.print(value);
	}
	int nbreElements() {
		return 1;
	}
}
class Ensemblee{
	Object[] value;
	int count = 0;
	Ensemblee(){
		this(100);
	}
	Ensemblee(int size){
		value= new Object[size];
	}
	void add(Object obj) {
		value[count++] = obj;
	}

	void affiche(){
		System.out.print("{");
		for(int i=0;i<count;i++) {
			 if(value[i] instanceof Ensemblee) {
				 Ensemblee a = (Ensemblee)value[i] ;
				 a.affiche();
			 }
			 else {
				 Element a = (Element)value[i] ;
				 a.affiche();
				 if(i!=count-1)System.out.print(",");
			 }
		}
		System.out.print("}");
	}

	int nbreElements() {
		 int n = 0;
		 for(int i =0;i<count;i++) {
			 if(value[i] instanceof Ensemblee) {
				 Ensemblee a = (Ensemblee)value[i] ;
				 n+=a.count;
			 }
			 else {
				 n++;
			 }
		 }
		 return n;
	}
}