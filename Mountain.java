import java.awt.Graphics;
import java.awt.Color;

public class Mountain { 
	private int[] x;
	private int[] y;
	private int pointsNum;
	private int[] modX;
	private int[] modY;

	public Mountain(int[] _x, int[] _y, int _pointsNum) {
		x = _x;
		y = _y;
		pointsNum = _pointsNum;
		modX = new int[pointsNum];
		modY = new int[pointsNum];
	}

	public void mountainDraw(Graphics g, int mouseX, int mouseY, int amount, Color col, int time, int changeTime) {
		for (int i = 0; i < pointsNum; i++) {
			modX[i] = x[i] + (mouseX - 500) / amount;
			modY[i] = y[i] + (mouseY - 500) / amount;
			
			if (time == 666 && changeTime == 0) {
				modX[i] += Math.round(Math.random() * 500 / amount);
				modY[i] += Math.round(Math.random() * 500 / amount);
			}
		}
		
		g.setColor(col);
		g.fillPolygon(modX, modY, pointsNum);
	}
}

// Version 0.0.06