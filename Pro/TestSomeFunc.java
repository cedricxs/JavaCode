package Pro;
/**
 * ��д��toString()&equals()����
 * @author ����
 *
 */
public class TestSomeFunc extends Object{

	String name;
	int age;
	String sno;
	//����generator�Զ�����set&get��������װ��Ա����
	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		if(sno.length()!=11) {
			System.out.println("������11λѧ�ţ�");
		}
		else{
			this.sno = sno;
		}
	}

	TestSomeFunc(){
		
	}
	
	TestSomeFunc(String name,int age,String sno){
		this.name = name;
		this.age = age;
		this.sno = sno;
	}
	
	public boolean equals(Object obj) {
		if(this == obj)return true;
		if(obj instanceof TestSomeFunc) {
			TestSomeFunc any = (TestSomeFunc)obj;  //��obj����ת��ΪTestSomeFunc
			return (this.sno == any.sno);
		}
		return false;
	}
	
	public String toString() {
		return (this.name+" "+this.age+" "+this.sno);
	}
	
	public static void main(String[] argvs) {
		TestSomeFunc tsf = new TestSomeFunc("cedricxs",20,"15020022003");
		TestSomeFunc _tsf = new TestSomeFunc("cedric",18,"15020022003");
		System.out.println(tsf);
		System.out.println(tsf.equals(_tsf));
		tsf.setSno("1502002200");
	}
}


