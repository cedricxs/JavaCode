package Pro;
import java.awt.*;
import javax.swing.*;
import static java.lang.Math.PI;
/**
 * @author cedricxs
 * @date 2018/9/23
 */

public class BallGame extends JFrame{
	
	Image ball = Toolkit.getDefaultToolkit().getImage("image/ball.png");
	Image desk = Toolkit.getDefaultToolkit().getImage("image/desk.jpg");
	
	//小球的横纵坐标
	double x = 100;
	double y = 100;
	//boolean right = true;
	
	double speed = 10;
	
	double degree = PI/3; //用弧度算
	
	//画窗口的方法
	public void paint(Graphics g) {
		System.out.println("窗口被画了一次");
		System.out.println(speed);
		g.drawImage(desk,0,0,null);
		g.drawImage(ball,(int)x,(int)y,null);
		/*
		if(right) {
			x += 10;
		}else {
			x -= 10;
		}
		if(x<40) {
			right = true;
		}
		if(x>856-40-30) {
			right = false;
		}
		*/
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		if(y>500-40-30||y<40+30) {   //最后一个30是标题栏的宽度
			degree = -degree;
		}
		if(x<40||x>856-40-30) {
			degree = PI - degree;
		}
		if(speed>0) {
			speed-=0.015;
		}
	}
	
	//加载窗口
	void launchframe() {
		setSize(856,500);
		setLocation(50,50);
		setVisible(true);
		
		
	//重画窗口
		while(speed>0) {
			repaint();
			try {
				Thread.sleep(10);  //10ms暂停，
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//加载图片
	public static void main(String[] argvs) {
		BallGame game = new BallGame();
		game.launchframe();
	}
}
