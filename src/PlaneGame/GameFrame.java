package PlaneGame;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import PlaneGame.GameUtil;

/**
 * 飞机游戏的主窗口
 * @author 安迪
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends Frame{

	long start ;
	long runtime; 
	int LoopCount;
	boolean running = false;
	
	Image planepic = GameUtil.getImage("image/plane.png");
	Image bg = GameUtil.getImage("image/bg.jpg");
	Image bomb = GameUtil.getImage("image/crashed.png");
	Image gameover = GameUtil.getImage("image/gameover.jpg");
	Image pbut = GameUtil.getImage("Image/playbut.png");
	Image exp = GameUtil.getImage("image/crashed.png");

	Plane plane ;
	Shell[] shells = new Shell[Constant.SHELL_NUM];              //仅仅声明了shells,在堆中没有实际数据!!
	Explode explode;
	
	/**
	 * 窗口加载函数
	 * 并启动游戏，由内部类PaintThread启动游戏线程
	 * 所谓启动游戏，不过是不断移动并重画GameObj
	 */
	public void launchFrame() {
		this.setTitle("飞机大战");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		
		//匿名内部类继承WindowAdapter并重写windowClosing()方法 
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.out.println("程序正常关闭...");
				System.exit(0);
			}
		});
		
		new PaintThread().start();				//启动重画线程
		addKeyListener(new KeyMonitor());      //监听键盘事件
		addMouseListener(new MouseMonitor());
	}
	
	/*
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 * paint方法自动调用,g是一支画笔
	 * 在这里我们只需继承JFrame并重写paint()函数，在运行时会自动调用draw(Frame frame)
	 * 由于多态，调用的frame.paint()即为我们重写后的paint()方法！
	 */
	public void paint(Graphics g) {
		
		//重画背景
		g.drawImage(bg, 0, 0, 700, 800, null);
		
		//运行态，由鼠标点击playbut触发
		if(running) {	
		//移动飞机
		plane.move();
		//重画飞机
		plane.drawSelf(g);	
		//重画子弹并同时检测碰撞
		for(Shell s:shells){ 
			if(s.living) {
				s.move();
			s.drawSelf(g);
			if(s.getRect().intersects(plane.getRect())) 
					plane.setHascrashed(true);
			}	
		}
		
		for(int i=0;i<plane.currn;i++) {
				plane.rs[i].move();
				plane.rs[i].drawSelf(g);
				for(Shell s:shells) {
					if(s.getRect().intersects(plane.rs[i].getRect()))
						s.living = false;
				}
				
		}
		//调用碰撞事件
		if(plane.isHascrashed())
				crashedEvent(g);           
		
		//计算游戏时长
		if(!plane.isHascrashed()){
			runtime = System.currentTimeMillis()-start;
			GameUtil.drawString(g,String.valueOf(runtime)+"ms",50,100); 
			}

		}
		else {
			g.drawImage(pbut,300,400,100,100, null);
		}
	}
	
	/**
	 * 子弹碰撞飞机后出发的事件函数
	 * @param g
	 */
	void crashedEvent(Graphics g) {
		if(explode==null)
			explode = new Explode(exp,plane.x,plane.y,100,80);
		explode.drawSelf(g);
		GameUtil.drawString(g,"GAME OVER你坚持了:"+String.valueOf(runtime)+"ms",100,(int)Constant.GAME_HEIGHT/2);
		LoopCount++;
		if(LoopCount>100)
		running = false;
	}

	/**
	 * 初始化或重置子弹
	 */
	void setShells() {
		for (int i=0;i<Constant.SHELL_NUM;i++) {
			shells[i] = new Shell();
		}
	}
	
	/**
	 * 初始化或重置飞机
	 */
	void setPlane() {
		plane = new Plane(planepic,340,400,6,20,20);
		plane.setHascrashed(false);	
		}
	
	/**
	 * 游戏开始的一些初始化操作
	 */
	void startGame() {
		running = true;
		setPlane();
		setShells();
		LoopCount = 0;
		explode = null;
		start = System.currentTimeMillis(); 
		
	}
	
	
	/**
	 * 创建线程类：每隔15ms调用重画进程
	 * @author 安迪
	 * 内部类提供了更好的封装，只能让外部类(GameFrame)直接访问
	 * 内部类可以直接调用外部类的方法与私有变量，是外部类的成员变量
	 * 使用在只为内部类提供服务的类的场合
	 * 非静态内部类的类体依靠于外部类的对象而存在！
	 */
	class PaintThread extends Thread{
		public void run() {
			while(true) {
				repaint();
				
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 按键事件监听类
	 * @author 安迪
	 *
	 */
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if(running)plane.addDirection(e);
		}

		
		public void keyReleased(KeyEvent e) {
			if(running)plane.subDirection(e);
		}
		
	}
	/**
	 * 鼠标事件监听类
	 * @author 安迪
	 *
	 */
	class MouseMonitor extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getComponent());
			if(!running)
			if(e.getX()>300&&e.getX()<400&&e.getY()>400&&e.getY()<500) {
				startGame(); 
			}
		}
	}

	/**
	 * 主程序入口
	 * @param argvs
	 */
	public static void main(String[] argvs) {
		GameFrame gf = new GameFrame();
		gf.launchFrame();
	}
	
	
	//解决闪烁问题
	private Image OffScreenImage = null;
	
	public void update(Graphics g) {
		if(OffScreenImage==null) 
			OffScreenImage = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
			
			Graphics gOff = OffScreenImage.getGraphics();
			paint(gOff);
			g.drawImage(OffScreenImage, 0, 0, null);
		
	}
}
