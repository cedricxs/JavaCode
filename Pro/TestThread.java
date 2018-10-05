package Pro;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ���Զ��߳�
 * �����̷߳���һ��
 * 	�̳�Thread����дrun()
 * 	�������󣬵���start(),�����̳߳أ���Cpu��������
 * �����̷߳�����(�Ƽ�)��
 * 	�̳�Runnable,��дrun(),�����ݸ�Thread,��̬����
 * 	�����������̬����ͬ����һ���ڴ�,���漰���̰߳�����ȫ�����⣬�ڸ߲���ʱӦ���߳���
 * �����̷߳�����:
 *  �̳�Callable�ӿڣ������õ��÷���,���з���ֵ�����׳��쳣
 * @author ����
 * ͬһ�����̵Ķ���̹߳���һ���ڴ���Դ
 * t.join()����������ǰ�߳�,�ȴ�t�߳����н����ټ����л�����߳�
 * Thead.currentThread(),setName(),getName(),isAlive()
 * Thread���ȼ�����,Ĭ��Ϊ5,����thread.setPriority(),Ӱ��Cpu���ȵĸ���
 * ����ͨ��Debug�����̵߳�����״̬�ͻ�����Ϣ
 * �������ӳ��У��ಢ������ͬһ��Դ�������Դ��ͬ���̲߳���ȫ��������synchronized�����߳�ʵ�壬����ʵ���̰߳�ȫ
 * ����synchronized����,ͬһʱ��ֻ��һ������ʹ����Դ���ȸô���ʹ����֮����һ�����ڷ�����Դ�����߳���,������Ҳ��һ������
 * �߳���synchronized(Object)��ʾ���ŵĶ����̬����ֽ��룬��ʾֻҪ���̷߳�����ס�Ķ��󣬾ͶԶ�����������ֹͬʱ�����̷߳���
 */
public class TestThread {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		//main���߳�
		for(int i=0;i<100;i++) {
			System.out.println("main==>"+i);
		}
		System.out.println(Thread.currentThread());
		
		//����һ���������߳�
		new Rabbit().start();
		new Turtle().start();
		
		//ʹ�þ�̬����,Threadʵ�ִ����ɫ��appΪ��ʵ���н�ɫ
		//ֻ��һ��Web12306������������ʹ��ͬһ���ڴ�
		Web12306 r = new Web12306();
		Thread proxy1 = new Thread(r,"��ţ1");
		Thread proxy2 =new Thread(r,"��ţ2");
		Thread proxy3 =new Thread(r,"��ţ3");
		proxy3.setPriority(6);
		proxy1.start();
		proxy2.start();
		proxy3.start();
		
		
		//����Callable�ӿڴ����߳�
		//����һ���߳�
		ExecutorService ser = Executors.newFixedThreadPool(1);
		Callable<Integer> app2 = new MyApp2();
		//�ύ�߳�����ʵ��
		Future<Integer> res = ser.submit(app2);
		int num =  res.get();
		System.out.println(num);
		ser.shutdown();
		
		//�����ȴ�����Ʊ���꣬��ӡ��Ʊ���
		proxy1.join();
		proxy2.join();
		proxy3.join();
		System.out.println(r.map.entrySet());
	}

}
class Rabbit extends Thread{
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("��������:"+i+"m");
		}
	}
}
class Turtle extends Thread{
	public void run() {
		for(double i=0;i<100;i=i+0.5) {
			System.out.println("�ڹ�����:"+i+"m");
		}
	}
}

//����������ʵ�߳���
class Web12306 implements Runnable{
	//��¼�����̵߳�ʹ�ô���
	Map<String,Integer> map = new HashMap<String, Integer>();
	//ģ��ʣ��Ʊ����
	private int num = 50;
	//��flagȷ���̺߳�ʱ����,�����̵߳ı�־
	boolean flag=true;
	@Override
    public void run() {
		while(flag) {
			//�������ȼ��������õĴ�����ø÷����ĸ���
			test();
		}
	}
	//test1()�̲߳���ȫ������ָ�������Դδ������ͬһʱ�䱻�������ʹ��,�����ٶȿ�
	void test1() {
		if(num<=0) {
			flag = false;
			return ;
		}
		else {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"������Ʊ"+num--);
		}
		
	}
	/**
	 * test()�̰߳�ȫ�������ٶ��������ڵȴ����ұ�֤�����������е��߳�ʵ��
	 */
	synchronized void test() { 

		if(num<=0) {
			flag = false;
			return ;
		}
		else {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"������Ʊ"+num--);
		String name = Thread.currentThread().getName();
		Object obj= map.get(name);
		if(obj==null) map.put(name, 0);
		else map.put(name, map.get(name)+1);
		}
	}
	/**
	 * ͬ����ס��������
	 */
	public void test2() { 
		synchronized(this) {
			if(num<=0) {
				flag = false;
				return ;
			}
			else {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"������Ʊ"+num--);
			String name = Thread.currentThread().getName();
			Object obj= map.get(name);
			if(obj==null) map.put(name, 0);
			else map.put(name, map.get(name)+1);
			}
		}
	}

}
		
class MyApp2 implements Callable<Integer>{
	
		@Override
		public Integer call() throws Exception {
			// TODO Auto-generated method stub
			return 1000;
		}
		
	}