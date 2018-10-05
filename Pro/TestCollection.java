package Pro;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Collection是所有容器类(除Map)的最高父类接口，其下派生出
 * List->ArrayList/LinkedList/Vector->Stack
 * Queue->Deque->ArrayDeque
 * Set->HashSet/TreeSet
 * Map->HashMap/TreeMap
 * Collections类是包含一些常用容器处理方法,利用Collections.synchronizedxxx()可以将容器编程线程同步容器
 * Collections类地位同Arrays，都是静态定义了一些常用处理的方法
 * 测试一些容器和迭代器
 * @author 安迪
 * 容器在使用泛型时，元素类型仍然是Object，但是只有规定泛型类型T才合法，编译通过可执行
 * 迭代器Iterator<T>由容器的itrerator()方法提供，有三个方法：
 * 		hasNext()
 * 		next()
 * 		remove()
 * 迭代器是遍历泛型容器的快速高效方法
 * TreeSet/TreeMap是两种自动排序的容器，需满足元素继承Comparable
 */
public class TestCollection {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		Iterator<Integer> e = list.iterator();
		while(e.hasNext()) {
			System.out.print(e.next()+" ");
		}
		System.out.println();
		Set<Integer> s = new HashSet<Integer>();
		s.add(0);
		s.add(2);
		s.add(4);
		s.add(6);
		Iterator<Integer> e1 = s.iterator();
		while(e1.hasNext()) {
			System.out.print(e1.next()+" ");
		}
		System.out.println(args.getClass().getName());
		Set<Student_> stu = new TreeSet<Student_>();
		stu.add(new Student_(10,"213"));
		Stack<String> stack = new Stack<String>(); 
		Queue<String> queue = new ArrayDeque<String>();
		List<Integer> list1 = Collections.synchronizedList(list);
		List<Integer> list2 = Collections.unmodifiableList(list);
		//list2.add(10);
	}
	
}
