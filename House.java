import java.awt.Graphics;
import java.awt.Color;

public class House { 
	private int x;
	private int y;
	long[][] brick = new long[18][10];
	
	public House(int _x, int _y) {
		x = _x;
		y = _y;
		
		for (int yy = 0; yy < 18; yy++) {
			for (int xx = 0; xx < 10; xx++) {
				brick[yy][xx] = Math.round(Math.random());
			}
		}
	}

	public void houseDraw(Graphics g, int mouseX, int mouseY, int amount) {
		int parX = (mouseX - 500) / amount;
		int parY = (mouseY - 500) / amount;
		
		Color wall = new Color(245, 235, 210);
		Color wall2 = new Color(250, 240, 215);
		int xOffset;
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
		
		Color doorFrame = new Color(180, 150, 115);
		g.setColor(doorFrame);
		g.fillRect(97 + x + parX, 230 + y + parY, 130, 230);
		
		Color door = new Color(165, 135, 100);
		g.setColor(door);
		g.fillRect(112 + x + parX, 250 + y + parY, 100, 200);
		
		Color doorKnob = new Color(220, 220, 210);
		g.setColor(doorKnob);
		g.fillOval(187 + x + parX, 350 + y + parY, 15, 15);
		
		Color roof = new Color(210, 100, 100);
		g.setColor(roof);
		int[] xRoofPoints = {-38 + x + parX, 262 + x + parX, 562 + x + parX};
		int[] yRoofPoints = {y + parY, -150 + y + parY, y + parY};
		g.fillPolygon(xRoofPoints, yRoofPoints, 3);
	}
} 