import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Scenery extends JPanel implements MouseListener, MouseMotionListener {
	Sun sun;
	Cloud[] cloud = new Cloud[5];
	Cloud[] cloud2 = new Cloud[3];
	Mountain mountain;
	Mountain mountain2;
	Mountain mountain3;
	House house;
	public int mouseX;
	public int mouseY;
	
	public Scenery() {
		addMouseListener(this);
		addMouseMotionListener(this);
		
		sun = new Sun(100, 50, 200);
		
		for (int i = 0; i < 4; i++) {
			cloud[i] = new Cloud(i * 400, (int)(Math.random() * 300));
		}
		for (int i = 0; i < 2; i++) {
			cloud2[i] = new Cloud(i * 800 + 200, (int)(Math.random() * 300));
		}
		
		int[] xPoints = {-100, 150, 350, 400, 550, 650, 800, 850, 1100, 1100, 0};
		int[] yPoints = {350, 250, 300, 350, 200, 250, 350, 300, 400, 1000, 1000};
		mountain = new Mountain(xPoints, yPoints, 11);
		int[] xPoints2 = {-100, 200, 400, 500, 600, 650, 750, 900, 1100, 1100, 0};
		int[] yPoints2 = {450, 350, 450, 400, 350, 500, 450, 400, 500, 1000, 1000};
		mountain2 = new Mountain(xPoints2, yPoints2, 11);
		int[] xPoints3 = {-100, 150, 350, 400, 550, 650, 800, 850, 1100, 1100, 0};
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
		for (int i = 0; i < 500; i += 20) {
			g.setColor(sky);
			g.fillRect(0, i, 1000, 20);
			sky = new Color(150 - i / 10, 230 - i / 10, 255);
		}
		
		sun.sunDraw(g);
		
		for (int i = 0; i < 4; i++) {
			cloud[i].cloudDraw(g, 0.8, mouseX, mouseY, 125);
		}
		
		Color mountainCol = new Color(180, 200, 205);
		g.setColor(mountainCol);
		mountain.mountainDraw(g, mouseX, mouseY, 100);
		
		for (int i = 0; i < 2; i++) {
			cloud2[i].cloudDraw(g, 1.2, mouseX, mouseY, 80);
		}
		
		Color mountain2Col = new Color(160, 180, 185);
		g.setColor(mountain2Col);
		mountain2.mountainDraw(g, mouseX, mouseY, 75);
		
		Color mountain3Col = new Color(140, 160, 165);
		g.setColor(mountain3Col);
		mountain3.mountainDraw(g, mouseX, mouseY, 50);
		
		house.houseDraw(g, mouseX, mouseY, 20);
		
		Color grass = new Color(135, 210, 150);
		g.setColor(grass);
		g.fillRect(-250 + (mouseX - 500) / 20, 950 + (mouseY - 500) / 20, 1500, 300);
	}
	
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		// System.out.println(mouseX + ", " + mouseY);
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
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
				cloud[i].moveRight(2);
			}
			for (int i = 0; i < 2; i++) {
				cloud2[i].moveRight(3);
			}
			
			repaint();
		}
	}
}

// Version 0.0.01