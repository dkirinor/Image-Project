// This code is in progress

import java.awt.Color;
import java.awt.Graphics;

public class Particle {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int sx;
	private int sy;
	private int size;
	private int life;
	private int red;
	private int green;
	private int blue;
	
	public Particle(int _x, int _y, int _dx, int _dy, int _sx, int _sy, int _size, int _life, int _red, int _green, int _blue){
		x = _x;
		y = _y;
		dx = _dx;
		dy = _dy;
		sx = _sx;
		sy = _sy;
		size = _size;
		life = _life;
		red = _red;
		green = _green;
		blue = _blue;
	}
	
	public void update(){
		dx += sx;
		dy += sy;
		life--;
		if (life <= 0) {
			dx = x;
			dy = y;
		}
	}

	public void particleDraw(Graphics g){
		Color particleCol = new Color(red, green, blue);
		g.fillOval(dx, dy, size, size);
	}

}

// Version 0.0.03