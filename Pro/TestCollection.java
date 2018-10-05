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
 * Collection������������(��Map)����߸���ӿڣ�����������
 * List->ArrayList/LinkedList/Vector->Stack
 * Queue->Deque->ArrayDeque
 * Set->HashSet/TreeSet
 * Map->HashMap/TreeMap
 * Collections���ǰ���һЩ��������������,����Collections.synchronizedxxx()���Խ���������߳�ͬ������
 * Collections���λͬArrays�����Ǿ�̬������һЩ���ô���ķ���
 * ����һЩ�����͵�����
 * @author ����
 * ������ʹ�÷���ʱ��Ԫ��������Ȼ��Object������ֻ�й涨��������T�źϷ�������ͨ����ִ��
 * ������Iterator<T>��������itrerator()�����ṩ��������������
 * 		hasNext()
 * 		next()
 * 		remove()
 * �������Ǳ������������Ŀ��ٸ�Ч����
 * TreeSet/TreeMap�������Զ������������������Ԫ�ؼ̳�Comparable
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
