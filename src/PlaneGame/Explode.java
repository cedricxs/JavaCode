package PlaneGame;

import java.awt.Graphics;
import java.awt.Image;

public class Explode extends GameObj{

	
	Explode(Image img,double x,double y,int width,int height){
		super(img,x,y,0,width,height);
	}
	
	public void drawSelf(Graphics g) {
		g.drawImage(img,(int)x,(int)y,width,height,null);
	}
	
}
