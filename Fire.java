import java.awt.Color;
import java.awt.Graphics;

public class Fire {
	private int x;
	private int y;
	private int sx;
	private int sy;
	private int w;
	private int h;
	private int speed;
	private int size;
	int modX, modY;
	Color fireCol;
	Color fireFlashCol;
	Color fireGlowCol;
	
	public Fire(int _sx, int _sy, int _w, int _h, int _speed, int _size){
		sx = _sx;
		sy = _sy;
		w = _w;
		h = _h;
		x = (int)Math.round(Math.random() * w) + sx;
		y = sy - h + (int)Math.round(Math.random() * h);
		speed = _speed;
		size = _size;
	}
	
	public void update() {
		x += (int)Math.round(Math.random() * 20) - 10;
		y -= speed;
		if (y < sy - h && (int)Math.round(Math.random() * 100) < 5) {
			x = (int)Math.round(Math.random() * w) + sx;
			y = sy;
		}
	}

	public void fireDraw(Graphics g, int mouseX, int mouseY, int amount) {
		modX = x + (mouseX + 500) / amount;
		modY = y + (mouseY + 500) / amount;
		
		fireGlowCol = new Color((255 - Math.abs((sy - y) / speed) > 0) ? 255 - Math.abs((sy - y) / speed) : 0,
		(225 - Math.abs((sy - y) / speed) > 0) ? 225 - Math.abs((sy - y) / speed) : 0, 100, 
		(75 - Math.abs((sy - y) / speed) > 0) ? 75 - (int)Math.abs((sy - y) / speed) : 0);
		g.setColor(fireGlowCol);
		for (int i = 2; i < 5; i++) {
			g.fillOval(modX - 20 * i, modY - 20 * i, size + 40 * i, size + 40 * i);
		}
		
		fireCol = new Color(255, (255 - Math.abs((sy - y) / speed) > 0) ? 255 - Math.abs((sy - y) / speed) : 0, 0,
		(255 - Math.abs((sy - y) / speed) > 0) ? 255 - (int)Math.abs((sy - y) / speed) : 0);
		fireFlashCol = new Color(255, 255, 255);
		if (Math.random() * 100 < 15) g.setColor(fireFlashCol);
		else g.setColor(fireCol);
		g.fillOval(modX, modY, size, size);
	}

}

// Version 0.0.06