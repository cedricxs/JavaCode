package Pro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 容器中有两种比较方法:
 * 	1.定义元素实现Comparable接口，在对容器遍历排序<T extends Comparable<? super T>>
 *  2.定义实现排序的Comparator<? super T>类，调用Collections.sort()方法实现排序
 * @author 安迪
 *
 */
public class TestCompare {
	
	public static void main(String[] args) {
		ArrayList<Student_> stu = new ArrayList<Student_>();
		stu.add(new Student_(20,"123"));
		stu.add(new Student_(21,"354"));
		stu.add(new Student_(22,"355"));
		stu.add(new Student_(17,"357"));
		Collections.sort( stu);
		System.out.println(stu);
		//使用匿名内部类，继承Comparator并重写compare()
		Collections.sort(stu,new Comparator<Student_>() {;
		@Override
		public int compare(Student_	o1, Student_ o2) {
			return -o1.age>o2.age?1:(o1.age==o2.age?0:-1);
		}
		});
		System.out.println(stu);
		Collections.shuffle(stu);
		System.out.println(stu);
	}
}

/**
 * 对元素实现Comparable，可比较
 * @author 安迪
 *
 */
class Student_ implements Comparable<Student_>{

	int age;
	String name;
	Student_(int age,String name){
		this.age = age;
		this.name = name;
	}
	@Override
	public int compareTo(Student_ o) {
		return this.age>o.age?1:(this.age==o.age?0:-1);
	}
	public String toString() {
		return new String(name+":"+age);
	}
}

class Comp implements Comparator<Student_>{

	@Override
	public int compare(Student_	o1, Student_ o2) {
		return -o1.age>o2.age?1:(o1.age==o2.age?0:-1);
	}
	
}
