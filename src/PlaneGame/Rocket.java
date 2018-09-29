package PlaneGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Rocket extends GameObj{
	
	public Rocket(Image img,double x,double y,int speed, int width, int height) {
		super(img,x,y,speed,width,height);
	}
	
	public void drawSelf(Graphics g) {
		g.drawImage(img,(int)x,(int)y,width,height,null);
	}
	
	public void move() {
		this.y -= speed; 
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)this.x,(int)this.y,this.width,this.height);
	}
}
