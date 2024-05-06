import java.awt.Graphics;
import java.awt.Color;

public class Rain { 
	private int x;
	private int y;
	private int yEnd;
	private int size;
	private int thick;
	private int modX;
	private int modY;
	Color rain;

	public Rain(int _x, int _y, int _yEnd, int _size, int _thick) {
		x = _x;
		y = _y;
		yEnd = _yEnd;
		size = _size;
		thick = _thick;
	}

	public void rainDraw(Graphics g, int mouseX, int mouseY, int amount, int time, int changeTime) {
		modX = x + (mouseX + 500) / amount;
		modY = y + (mouseY + 500) / amount;
		
		if (time == 666 && changeTime == 0) rain = new Color(255, (int)(Math.random() * 50), (int)(Math.random() * 50), 100);	
		else rain = new Color((int)(Math.random() * 50), (int)(Math.random() * 50), (int)(Math.random() * 100) + 150, 100);
		g.setColor(rain);
		int[] xPoints = {modX, modX + (int)(size / 2), modX + (int)(size / 2) + thick, modX + thick};
		int[] yPoints = {modY, modY + (int)(0.87 * size), modY + (int)(0.87 * size), modY};
		
		g.fillPolygon(xPoints, yPoints, 4);
	}

	public void rainDown(int speed) {
		x += speed / 2;
		y += speed * 0.87;
		if (x > 1000) x = -100;
		if (y > yEnd) {
			x = (int)(Math.random() * 1100) - 100;
			y = -100;
	
		}
	}
	
	public void rainReset() {
		y -= 1000;
	}
}

// Version 0.0.06