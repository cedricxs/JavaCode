package Pro;
/**
 * 重写了toString()&equals()方法
 * @author 安迪
 *
 */
public class TestSomeFunc extends Object{

	String name;
	int age;
	String sno;
	//利用generator自动生成set&get方法，封装成员变量
	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		if(sno.length()!=11) {
			System.out.println("请输入11位学号！");
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
			TestSomeFunc any = (TestSomeFunc)obj;  //将obj类型转化为TestSomeFunc
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


