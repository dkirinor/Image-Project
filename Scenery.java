import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Scenery extends JPanel {
	Sun sun;
	Cloud [] cloud = new Cloud[5];
	long [][] brick = new long[18][10];
	
	public Scenery() {
		sun = new Sun(100, 50, 200);
		
		for (int i = 0; i < 4; i++) {
			cloud[i] = new Cloud(i * 400, (int)(Math.random() * 300));
		}
		
		for (int y = 0; y < 18; y++) {
			for (int x = 0; x < 10; x++) {
				brick[y][x] = Math.round(Math.random());
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1000, 1000);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color sky = new Color(150, 230, 255);
		for (int i = 0; i < 400; i += 20) {
			g.setColor(sky);
			g.fillRect(0, i, 1000, 20);
			sky = new Color(150 - i / 10, 230 - i / 10, 255);
		}
		
		sun.sunDraw(g);
		
		Color mountain = new Color(180, 200, 205);
		g.setColor(mountain);
		int[] xMountainPoints = {0, 150, 350, 400, 550, 650, 800, 850, 1000, 1000, 0};
		int[] yMountainPoints = {350, 250, 300, 350, 200, 250, 350, 300, 400, 1000, 1000};
		g.fillPolygon(xMountainPoints, yMountainPoints, 11);
		
		for (int i = 0; i < 4; i++) {
			cloud[i].cloudDraw(g);
		}
		
		Color mountain2 = new Color(160, 180, 185);
		g.setColor(mountain2);
		int[] xMountain2Points = {0, 200, 400, 500, 600, 650, 750, 900, 1000, 1000, 0};
		int[] yMountain2Points = {450, 350, 450, 400, 350, 500, 450, 400, 500, 1000, 1000};
		g.fillPolygon(xMountain2Points, yMountain2Points, 11);
		
		Color mountain3 = new Color(140, 160, 165);
		g.setColor(mountain3);
		int[] xMountain3Points = {0, 150, 350, 400, 550, 650, 800, 850, 1000, 1000, 0};
		int[] yMountain3Points = {600, 550, 600, 650, 500, 650, 550, 600, 650, 1000, 1000};
		g.fillPolygon(xMountain3Points, yMountain3Points, 11);
		
		Color wall = new Color(245, 235, 210);
		Color wall2 = new Color(250, 240, 215);
		g.setColor(wall);
		int xOffset;
		for (int y = 500; y < 950; y += 25) {
			xOffset = y / 25 % 2 * 25;
			if (xOffset == 25) g.fillRect(338, y, 25, 25);
			for (int x = 338; x < 838; x += 50) {
				System.out.println(x + " " + y + " " + xOffset);
				if (brick[(y - 500) / 25][(x - 338 + xOffset) / 50] == 0) g.setColor(wall);
				else g.setColor(wall2);
				g.fillRect(x + xOffset, y, 50, 25);
			}
			if (xOffset == 0) g.fillRect(838, y, 25, 25);
		}
		
		Color doorFrame = new Color(180, 150, 115);
		g.setColor(doorFrame);
		g.fillRect(435, 735, 130, 230);
		
		Color door = new Color(165, 135, 100);
		g.setColor(door);
		g.fillRect(450, 750, 100, 200);
		
		Color doorKnob = new Color(220, 220, 210);
		g.setColor(doorKnob);
		g.fillOval(525, 850, 15, 15);
		
		Color roof = new Color(210, 100, 100);
		g.setColor(roof);
		int[] xRoofPoints = {300, 600, 900};
		int[] yRoofPoints = {500, 350, 500};
		g.fillPolygon(xRoofPoints, yRoofPoints, 3);
		
		Color grass = new Color(135, 210, 150);
		g.setColor(grass);
		g.fillRect(0, 950, 1000, 50);
	}
	
	double sunI = 0;
	public void animate() {
		for(;;) {
			try {
				Thread.sleep(15);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			// System.out.println("ran");
			
			sun.expand(Math.sin(sunI) * 25 + 325);
			sunI += 0.01;
			
			for (int i = 0; i < 4; i++) {
				cloud[i].moveRight(3);
			}
			
			repaint();
		}
	}
}