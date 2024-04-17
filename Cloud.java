import java.awt.Graphics;
import java.awt.Color;

public class Cloud { 
	private int x;
	private int y;

	public Cloud(int _x, int _y) {
		x = _x;
		y = _y;
	}

	public void cloudDraw(Graphics g) {
		Color cloud = new Color(235, 245, 255, 200);	
		g.setColor(cloud);
		g.fillOval(x, y, 150, 50);
		g.fillOval(x + 50, y, 150, 60);
	}

	public void moveRight(int speed) {
		x += speed;
		if (x > 1200) {
			x = -200;
			y = (int)(Math.random() * 300);
	
		}
	}
} 