import java.awt.Graphics;
import java.awt.Color;

public class Sun { 
	private int x;
	private int y;
	private double size;

	public Sun(int _x, int _y, double _size) {
		x = _x;
		y = _y;
		size = _size;
	}

	public void sunDraw(Graphics g, int yOffset) {
		Color sun = new Color(255, 205, 0);	
		Color sunGlow = new Color(255, 205, 0, 80);	
		g.setColor(sun);
		g.fillOval(x, y + yOffset, (int)size, (int)size);
		g.setColor(sunGlow);
		for (int i = 0; i < 4; i++) {
			g.fillOval(x - i * 10, y - i * 10 + yOffset, (int)size + i * 20, (int)size + i * 20);
		}
	}

	public void expand(double amount) {
		size = amount;
	}
}

// Version 0.0.05