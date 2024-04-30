import java.awt.Graphics;
import java.awt.Color;

public class Cloud { 
	private int x;
	private int y;
	private int modX;
	private int modY;

	public Cloud(int _x, int _y) {
		x = _x;
		y = _y;
	}

	public void cloudDraw(Graphics g, double size, int mouseX, int mouseY, int amount, int time) {
		modX = x + (mouseX + 500) / amount;
		modY = y + (mouseY + 500) / amount;
		
		Color cloud = new Color(235, 245, 255, 150);
		if (time == 0) cloud = new Color(235, 245, 255, 150);	
		if (time == 1 || time == 3) cloud = new Color(255, 225, 255, 150);	
		if (time == 2) cloud = new Color(135, 145, 155, 150);	
		if (time == 666) cloud = new Color(255, 0, 0, 150);	
		g.setColor(cloud);
		g.fillOval(modX, modY, (int)Math.round(150 * size), (int)Math.round(50 * size));
		g.fillOval(modX + 50, modY, (int)Math.round(150 * size), (int)Math.round(60 * size));
	}

	public void moveRight(int speed) {
		x += speed;
		if (x > 1000) {
			x = -200;
			y = (int)(Math.random() * 300);
	
		}
	}
}

// Version 0.0.04