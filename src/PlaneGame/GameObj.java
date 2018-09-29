package PlaneGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
/**
 * 游戏物体基类
 * @author 安迪
 *
 */

public class GameObj {
	
	Image img;
	double x,y;
	int speed;
	int width,height;
	
	public GameObj() {
		
	}
	
	public GameObj(Image img, double x, double y, int speed, int width, int height) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	
	public void drawSelf(Graphics g) {
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	 
	

}
