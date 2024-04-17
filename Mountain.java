import java.awt.Graphics;
import java.awt.Color;

public class Mountain { 
	private int[] x;
	private int[] y;
	private int pointsNum;

	public Mountain(int[] _x, int[] _y, int _pointsNum) {
		x = _x;
		y = _y;
		pointsNum = _pointsNum;
	}

	public void cloudDraw(Graphics g) {
		g.fillPolygon(x, y, pointsNum);
	}

	public void parallax(int layer) {
		
	}
} 