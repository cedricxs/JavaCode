package PlaneGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * ÅÚµ¯Àà
 * @author °²µÏ
 *
 */
public class Shell extends GameObj{
	double degree;
	boolean living;
	
	public Shell() {
		x = 200;
		y = 200;
		width = 10;
		height = 10;
		speed = 5;
		degree = Math.random()*Math.PI*2;
		living = true;
	}
	
	public void drawSelf(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.YELLOW);		
		g.fillOval((int)x,(int)y,width,height);

		g.setColor(color);
	}
	
	public void move() {
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);	
		if(y<0||y>Constant.GAME_HEIGHT) {
			degree = -degree;
		}
		if(x<0||x>Constant.GAME_WIDTH) {
			degree = Math.PI - degree;
		}
		
	}
}
