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
 * �ɻ���Ϸ��������
 * @author ����
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
	Shell[] shells = new Shell[Constant.SHELL_NUM];              //����������shells,�ڶ���û��ʵ������!!
	Explode explode;
	
	/**
	 * ���ڼ��غ���
	 * ��������Ϸ�����ڲ���PaintThread������Ϸ�߳�
	 * ��ν������Ϸ�������ǲ����ƶ����ػ�GameObj
	 */
	public void launchFrame() {
		this.setTitle("�ɻ���ս");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		
		//�����ڲ���̳�WindowAdapter����дwindowClosing()���� 
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.out.println("���������ر�...");
				System.exit(0);
			}
		});
		
		new PaintThread().start();				//�����ػ��߳�
		addKeyListener(new KeyMonitor());      //���������¼�
		addMouseListener(new MouseMonitor());
	}
	
	/*
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 * paint�����Զ�����,g��һ֧����
	 * ����������ֻ��̳�JFrame����дpaint()������������ʱ���Զ�����draw(Frame frame)
	 * ���ڶ�̬�����õ�frame.paint()��Ϊ������д���paint()������
	 */
	public void paint(Graphics g) {
		
		//�ػ�����
		g.drawImage(bg, 0, 0, 700, 800, null);
		
		//����̬���������playbut����
		if(running) {	
		//�ƶ��ɻ�
		plane.move();
		//�ػ��ɻ�
		plane.drawSelf(g);	
		//�ػ��ӵ���ͬʱ�����ײ
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
		//������ײ�¼�
		if(plane.isHascrashed())
				crashedEvent(g);           
		
		//������Ϸʱ��
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
	 * �ӵ���ײ�ɻ���������¼�����
	 * @param g
	 */
	void crashedEvent(Graphics g) {
		if(explode==null)
			explode = new Explode(exp,plane.x,plane.y,100,80);
		explode.drawSelf(g);
		GameUtil.drawString(g,"GAME OVER������:"+String.valueOf(runtime)+"ms",100,(int)Constant.GAME_HEIGHT/2);
		LoopCount++;
		if(LoopCount>100)
		running = false;
	}

	/**
	 * ��ʼ���������ӵ�
	 */
	void setShells() {
		for (int i=0;i<Constant.SHELL_NUM;i++) {
			shells[i] = new Shell();
		}
	}
	
	/**
	 * ��ʼ�������÷ɻ�
	 */
	void setPlane() {
		plane = new Plane(planepic,340,400,6,20,20);
		plane.setHascrashed(false);	
		}
	
	/**
	 * ��Ϸ��ʼ��һЩ��ʼ������
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
	 * �����߳��ࣺÿ��15ms�����ػ�����
	 * @author ����
	 * �ڲ����ṩ�˸��õķ�װ��ֻ�����ⲿ��(GameFrame)ֱ�ӷ���
	 * �ڲ������ֱ�ӵ����ⲿ��ķ�����˽�б��������ⲿ��ĳ�Ա����
	 * ʹ����ֻΪ�ڲ����ṩ�������ĳ���
	 * �Ǿ�̬�ڲ���������������ⲿ��Ķ�������ڣ�
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
	 * �����¼�������
	 * @author ����
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
	 * ����¼�������
	 * @author ����
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
	 * ���������
	 * @param argvs
	 */
	public static void main(String[] argvs) {
		GameFrame gf = new GameFrame();
		gf.launchFrame();
	}
	
	
	//�����˸����
	private Image OffScreenImage = null;
	
	public void update(Graphics g) {
		if(OffScreenImage==null) 
			OffScreenImage = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
			
			Graphics gOff = OffScreenImage.getGraphics();
			paint(gOff);
			g.drawImage(OffScreenImage, 0, 0, null);
		
	}
}
