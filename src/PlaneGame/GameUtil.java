package PlaneGame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * ��̬��Ϸ������
 * @author ����
 *
 */

public class GameUtil {
	private GameUtil() {
		
	}
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static Image getImage(String path) {
		BufferedImage bi = null;
		try {
			URL u = GameUtil.class.getClassLoader().getResource(path);
			bi = ImageIO.read(u);
		}catch(IOException e){
			e.printStackTrace();
		}
		return bi;
	}
	
	public static void drawString(Graphics g,String str,int x,int y) {
		Font f = g.getFont();
		g.setFont(new Font("΢���ź�",Font.PLAIN,30));
		g.drawString(str, x, y);
		g.setFont(f);
	}
}
