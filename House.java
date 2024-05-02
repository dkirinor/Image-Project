import java.awt.Graphics;
import java.awt.Color;

public class House { 
	private int x;
	private int y;
	long[][] brick = new long[18][10];
	long[][] brickBurning = new long[18][10];
	long[][] brickBurnt = new long[18][10];
	int parX, parY;
	int xOffset;
	Color wall, wall2;
	Color doorFrame;
	Color door;
	Color doorKnob;
	Color roof;
	
	public House(int _x, int _y) {
		x = _x;
		y = _y;
		
		for (int yy = 0; yy < 18; yy++) {
			for (int xx = 0; xx < 10; xx++) {
				brick[yy][xx] = Math.round(Math.random());
			}
		}
	}

	public void houseDraw(Graphics g, int mouseX, int mouseY, int amount, int houseBurnt) {
		parX = (mouseX - 500) / amount;
		parY = (mouseY - 500) / amount;
		
		
		if (houseBurnt == 0) {
			wall = new Color(245, 235, 210);
			wall2 = new Color(250, 240, 215);
		} else if (houseBurnt == 1) {
			wall = new Color(135, 85, 75);
			wall2 = new Color(125, 75, 85);
		} else {
			wall = new Color(50, 50, 50);
			wall2 = new Color(45, 45, 45);
		}
		
		for (int yy = y; yy < 450 + y; yy += 25) {
			xOffset = yy / 25 % 2 * 25;
			if (xOffset == 25) g.fillRect(x + parX, yy + parY, 25, 25);
			for (int xx = x; xx < 500 + x ; xx += 50) {
				// System.out.println(xx + " " + yy + " " + xOffset);
				if (brick[(yy - y) / 25][(xx - x + xOffset) / 50] == 0) g.setColor(wall);
				else g.setColor(wall2);
				g.fillRect(xx + xOffset + parX, yy + parY, 50, 25);
			}
			if (xOffset == 0) g.fillRect(x + 500 + parX, yy + parY, 25, 25);
		}
		
		if (houseBurnt == 0) doorFrame = new Color(180, 150, 115);
		else doorFrame = new Color(25, 20, 15);
		g.setColor(doorFrame);
		g.fillRect(97 + x + parX, 230 + y + parY, 130, 230);
		
		if (houseBurnt == 0) door = new Color(165, 135, 100);
		else door = new Color(30, 25, 15);
		g.setColor(door);
		g.fillRect(112 + x + parX, 250 + y + parY, 100, 200);
		
		if (houseBurnt == 0) doorKnob = new Color(220, 220, 210);
		else doorKnob = new Color(50, 50, 50);
		g.setColor(doorKnob);
		g.fillOval(187 + x + parX, 350 + y + parY, 15, 15);
		
		if (houseBurnt == 0) roof = new Color(210, 100, 100);
		else if (houseBurnt == 1) roof = new Color(145, 85, 75);
		else roof = new Color(50, 0, 0);
		g.setColor(roof);
		int[] xRoofPoints = {-38 + x + parX, 262 + x + parX, 562 + x + parX};
		int[] yRoofPoints = {y + parY, -150 + y + parY, y + parY};
		g.fillPolygon(xRoofPoints, yRoofPoints, 3);;
	}
}

// Version 0.0.05