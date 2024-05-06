import java.awt.Graphics;
import java.awt.Color;

public class Cloud { 
	private int x;
	private int y;
	private int modX;
	private int modY;
	double percent;
	int prevColor;
	Color[] colors;

	public Cloud(int _x, int _y) {
		x = _x;
		y = _y;
		
		colors = new Color[1000];
        colors[0] = new Color(235, 245, 255, 150);
        colors[1] = new Color(255, 225, 255, 150);
        colors[2] = new Color(135, 145, 155, 150);
		colors[3] = new Color(255, 225, 255, 150);
        colors[666] = new Color(255, 0, 0, 150);
		
		percent = 0;
	}

	public void cloudDraw(Graphics g, double size, int mouseX, int mouseY, int amount, int time, int cycle, int changeTime) {
		modX = x + (mouseX + 500) / amount;
		modY = y + (mouseY + 500) / amount;
		
		Color cloud;
		if (cycle == 0) {
			cloud = colors[0];
		} else {
			if (time > 0) prevColor = time - 1;
			if (time == 0) prevColor = 3;
			if (time == 666 && changeTime == 0) prevColor = 666;
			if (time == 666 && changeTime == 1) {
				time = 2;
				prevColor = 1;
			}
			
			int red = (int)(colors[prevColor].getRed() + (colors[time].getRed() - colors[prevColor].getRed()) * percent);
			int green = (int)(colors[prevColor].getGreen() + (colors[time].getGreen() - colors[prevColor].getGreen()) * percent);
			int blue = (int)(colors[prevColor].getBlue() + (colors[time].getBlue() - colors[prevColor].getBlue()) * percent);
			int alpha = (int)(colors[prevColor].getAlpha() + (colors[time].getAlpha() - colors[prevColor].getAlpha()) * percent);
			
			cloud = new Color(red, green, blue, alpha);
		}
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
	
	public void colorShift(double speed) {
		if (percent < 1) percent += speed;
	}
	
	public void resetPercent() {
		percent = 0;
	}
}

// Version 0.0.06