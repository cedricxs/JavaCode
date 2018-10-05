package Pro;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试多线程
 * 创建线程方法一：
 * 	继承Thread并重写run()
 * 	创建对象，调用start(),加入线程池，由Cpu调度运行
 * 创建线程方法二(推荐)：
 * 	继承Runnable,重写run(),并传递给Thread,静态代理，
 * 	可启动多个静态代理共同享用一份内存,会涉及到线程安不安全的问题，在高并发时应上线程锁
 * 创建线程方法三:
 *  继承Callable接口，并套用调用方法,可有返回值，可抛出异常
 * @author 安迪
 * 同一个进程的多个线程共享一份内存资源
 * t.join()方法阻塞当前线程,等待t线程运行结束再继续切换别的线程
 * Thead.currentThread(),setName(),getName(),isAlive()
 * Thread优先级设置,默认为5,利用thread.setPriority(),影响Cpu调度的概率
 * 可以通过Debug看到线程的运行状态和基本信息
 * 在网络延迟中，多并发访问同一资源会出现资源不同步线程不安全现象，利用synchronized声明线程实体，可以实现线程安全
 * 利用synchronized声明,同一时间只有一个代理使用资源，等该代理使用完之后下一代理在访问资源，即线程锁,本质上也是一种阻塞
 * 线程锁synchronized(Object)表示锁着的对象或静态类的字节码，表示只要有线程访问锁住的对象，就对对象上锁，禁止同时其他线程访问
 */
public class TestThread {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		//main主线程
		for(int i=0;i<100;i++) {
			System.out.println("main==>"+i);
		}
		System.out.println(Thread.currentThread());
		
		//方法一创建两个线程
		new Rabbit().start();
		new Turtle().start();
		
		//使用静态代理,Thread实现代理角色，app为真实运行角色
		//只有一个Web12306对象，三个代理使用同一份内存
		Web12306 r = new Web12306();
		Thread proxy1 = new Thread(r,"黄牛1");
		Thread proxy2 =new Thread(r,"黄牛2");
		Thread proxy3 =new Thread(r,"黄牛3");
		proxy3.setPriority(6);
		proxy1.start();
		proxy2.start();
		proxy3.start();
		
		
		//利用Callable接口创建线程
		//创建一个线程
		ExecutorService ser = Executors.newFixedThreadPool(1);
		Callable<Integer> app2 = new MyApp2();
		//提交线程运行实体
		Future<Integer> res = ser.submit(app2);
		int num =  res.get();
		System.out.println(num);
		ser.shutdown();
		
		//阻塞等待所有票抢完，打印抢票情况
		proxy1.join();
		proxy2.join();
		proxy3.join();
		System.out.println(r.map.entrySet());
	}

}
class Rabbit extends Thread{
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("兔子跑了:"+i+"m");
		}
	}
}
class Turtle extends Thread{
	public void run() {
		for(double i=0;i<100;i=i+0.5) {
			System.out.println("乌龟跑了:"+i+"m");
		}
	}
}

//多个代理的真实线程类
class Web12306 implements Runnable{
	//记录代理线程的使用次数
	Map<String,Integer> map = new HashMap<String, Integer>();
	//模拟剩余票数；
	private int num = 50;
	//由flag确定线程何时结束,控制线程的标志
	boolean flag=true;
	@Override
    public void run() {
		while(flag) {
			//代理优先级决定调用的代理调用该方法的概率
			test();
		}
	}
	//test1()线程不安全，会出现负数，资源未锁定，同一时间被多个代理使用,运行速度快
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
		System.out.println(Thread.currentThread().getName()+"抢到了票"+num--);
		}
		
	}
	/**
	 * test()线程安全，运行速度慢，存在等待，且保证锁定的是运行的线程实体
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
		System.out.println(Thread.currentThread().getName()+"抢到了票"+num--);
		String name = Thread.currentThread().getName();
		Object obj= map.get(name);
		if(obj==null) map.put(name, 0);
		else map.put(name, map.get(name)+1);
		}
	}
	/**
	 * 同样锁住了整个类
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
			System.out.println(Thread.currentThread().getName()+"抢到了票"+num--);
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