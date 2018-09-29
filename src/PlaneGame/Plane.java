package PlaneGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
/**
 * ·É»úÀà
 * @author °²µÏ
 *
 */
public class Plane extends GameObj{
	
	private boolean left,right,up,down;
	private boolean hascrashed;
	public Image roc = GameUtil.getImage("image/rocket.png");
	Rocket rs[] = new Rocket[Constant.ROCKET_NUM];
	int currn;
	
	public Plane(Image img,double x,double y,int speed, int width, int height) {
		super(img,x,y,speed,width,height);
	}
	
	void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_J:
				emitRocket();
				break;
		}
	}
	
	public boolean isHascrashed() {
		return hascrashed;
	}

	public void setHascrashed(boolean hascrashed) {
		this.hascrashed = hascrashed;
	}

	void subDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				up = false;
				break;
		}
	}
	
	public void move() { 
		if(!hascrashed) {
		x += left ? -speed:0;
		x += right ? speed:0;
		y += up ? -speed:0;
		y += down ? speed:0; 
		}
	}
	
	public void drawSelf(Graphics g) {
		if(!this.isHascrashed())
		g.drawImage(img,(int)x,(int)y,width,height,null);
	}
	
	public void emitRocket() {
		if(!this.isHascrashed()&&currn<Constant.ROCKET_NUM) {
			rs[currn] = new Rocket(roc,this.x,this.y,5,15,20); 
			currn++;
			}
	}
	
}
