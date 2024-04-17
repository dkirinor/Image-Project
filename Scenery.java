import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Scenery extends JPanel {
	Sun sun;
	Cloud[] cloud = new Cloud[5];
	Mountain mountain;
	Mountain mountain2;
	Mountain mountain3;
	House house;
	
	public Scenery() {
		sun = new Sun(100, 50, 200);
		
		for (int i = 0; i < 4; i++) {
			cloud[i] = new Cloud(i * 400, (int)(Math.random() * 300));
		}
		
		int[] xPoints = {0, 150, 350, 400, 550, 650, 800, 850, 1000, 1000, 0};
		int[] yPoints = {350, 250, 300, 350, 200, 250, 350, 300, 400, 1000, 1000};
		mountain = new Mountain(xPoints, yPoints, 11);
		int[] xPoints2 = {0, 200, 400, 500, 600, 650, 750, 900, 1000, 1000, 0};
		int[] yPoints2 = {450, 350, 450, 400, 350, 500, 450, 400, 500, 1000, 1000};
		mountain2 = new Mountain(xPoints2, yPoints2, 11);
		int[] xPoints3 = {0, 150, 350, 400, 550, 650, 800, 850, 1000, 1000, 0};
		int[] yPoints3 = {600, 550, 600, 650, 500, 650, 550, 600, 650, 1000, 1000};
		mountain3 = new Mountain(xPoints3, yPoints3, 11);
		
		house = new House(338, 500);
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
		
		for (int i = 0; i < 4; i++) {
			cloud[i].cloudDraw(g);
		}
		
		Color mountainCol = new Color(180, 200, 205);
		g.setColor(mountainCol);
		mountain.mountainDraw(g);
		
		Color mountain2Col = new Color(160, 180, 185);
		g.setColor(mountain2Col);
		mountain2.mountainDraw(g);
		
		Color mountain3Col = new Color(140, 160, 165);
		g.setColor(mountain3Col);
		mountain3.mountainDraw(g);
		
		house.houseDraw(g);
		
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