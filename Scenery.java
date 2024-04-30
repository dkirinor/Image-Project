import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Scenery extends JPanel implements MouseListener, MouseMotionListener {
	Sky sky;
	Sun sun;
	Moon moon;
	Cloud[] cloud = new Cloud[5];
	Cloud[] cloud2 = new Cloud[3];
	Mountain mountain;
	Mountain mountain2;
	Mountain mountain3;
	Color mountainCol;
	Color mountain2Col;
	Color mountain3Col;
	Grass grass;
	House house;
	Fire[] fire = new Fire[50];
	Color day;
	Color setRise;
	Color night;
	Color blood;
	Color borders;
	public int mouseX;
	public int mouseY;
	int time = 0; // 0 = day, 1 = sunset, 2 = night, 3 = sunrise, 666 = blood moon
	int houseBurnt = 0; // 0 = fine, 1 = burning, 2 = burnt
	int eventWait = 100;
	
	public Scenery() {
		addMouseListener(this);
		addMouseMotionListener(this);
		
		sky = new Sky(1000, 500, 10);
		
		sun = new Sun(100, 50, 200);
		
		moon = new Moon(800, 200);
		
		for (int i = 0; i < 4; i++) {
			cloud[i] = new Cloud(i * 400, (int)(Math.random() * 300));
		}
		for (int i = 0; i < 2; i++) {
			cloud2[i] = new Cloud(i * 800 + 200, (int)(Math.random() * 300));
		}
		
		// top left of this mountain is (-100,200) ... or pass in the peak position and dimensions
		int[] xPoints = {-100, 150, 350, 400, 550, 650, 800, 850, 1100, 1100, 0};
		int[] yPoints = {350, 250, 300, 350, 200, 250, 350, 300, 400, 1000, 1000};
		mountain = new Mountain(xPoints, yPoints, 11);
		int[] xPoints2 = {-100, 200, 400, 500, 600, 650, 750, 900, 1100, 1100, 0};
		int[] yPoints2 = {450, 350, 450, 400, 350, 500, 450, 400, 500, 1000, 1000};
		mountain2 = new Mountain(xPoints2, yPoints2, 11);
		int[] xPoints3 = {-100, 150, 350, 400, 550, 650, 800, 850, 1100, 1100, 0};
		int[] yPoints3 = {600, 550, 600, 650, 500, 650, 550, 600, 650, 1000, 1000};
		mountain3 = new Mountain(xPoints3, yPoints3, 11);
		
		mountainCol = new Color(180, 200, 205);
		mountain2Col = new Color(160, 180, 185);
		mountain3Col = new Color(140, 160, 165);
		
		grass = new Grass(-250, 950, 1500, 75, 5);
		
		house = new House(338, 500);
		
		for (int i = 0; i < 49; i++) {
			fire[i] = new Fire(275, 900, 525, 200, 5, 10);
		}
		
		day = new Color(255, 255, 0, 20);
		setRise = new Color(50, 0, 50, 90);
		night = new Color(0, 0, 0, 180);
		blood = new Color(100, 0, 0, 160);
		
		borders = new Color(238, 238, 238);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1000, 1000);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (time == 0) sky.skyDraw(g, 150, 230, 255, 50, 150, 255);
		if (time == 1 || time == 3) sky.skyDraw(g, 30, 120, 230, 230, 110, 80);
		if (time == 2) sky.skyDraw(g, 20, 5, 80, 5, 5, 10);
		if (time == 666) sky.skyDraw(g, 40, 10, 0, 200, 5, 5);
		
		if (time == 0) sun.sunDraw(g, 0);
		if (time == 1 || time == 3) sun.sunDraw(g, 150);
		
		moon.moonDraw(g, time);
		
		for (int i = 0; i < 4; i++) {
			cloud[i].cloudDraw(g, 0.8, mouseX, mouseY, 125, time);
		}
		
		mountain.mountainDraw(g, mouseX, mouseY, 100, mountainCol, time);
		
		for (int i = 0; i < 2; i++) {
			cloud2[i].cloudDraw(g, 1.2, mouseX, mouseY, 80, time);
		}
		
		mountain2.mountainDraw(g, mouseX, mouseY, 75, mountain2Col, time);
		
		mountain3.mountainDraw(g, mouseX, mouseY, 50, mountain3Col, time);
		
		house.houseDraw(g, mouseX, mouseY, 20, houseBurnt);
		
		if (time == 666) {
			for (int i = 0; i < 49; i++) {
				fire[i].fireDraw(g, mouseX, mouseY, 20);
			}
		}
		
		if (houseBurnt == 0) grass.grassDraw(g, mouseX, mouseY, 20, 165, 220, 145, 135, 210, 150);
		else if (houseBurnt == 1) grass.grassDraw(g, mouseX, mouseY, 20, 160, 135, 125, 125, 160, 130);
		else grass.grassDraw(g, mouseX, mouseY, 20, 75, 75, 75, 85, 115, 105);
		
		if (time == 0) g.setColor(day);
		if (time == 1 || time == 3) g.setColor(setRise);
		if (time == 2) g.setColor(night);
		if (time == 666) g.setColor(blood);
		g.fillRect(0, 0, 1000, 1000);
		
		g.setColor(borders);
		g.fillRect(1000, 0, 500, 1000);
		g.fillRect(0, 1000, 1500, 500);
	}
	
	
	public void mouseClicked(MouseEvent e) {
		// System.out.println("click");
		
		if (time == 1 && Math.random() * 100 < 5) {
			time = 666;
			houseBurnt = (houseBurnt == 0) ? 1 : 2;
		}
		else if (time == 666) {
			if (eventWait <= 0) {
				time = 3;
				houseBurnt = 2;
			}
		}
		else if (time == 3) time = 0;
		else time++;
		System.out.println(time);
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
			
			if (time == 666) eventWait--;
			else eventWait = 100;
			
			sun.expand(Math.sin(sunI) * 25 + 325);
			sunI += 0.01;
			
			for (int i = 0; i < 4; i++) {
				cloud[i].moveRight(2);
			}
			for (int i = 0; i < 2; i++) {
				cloud2[i].moveRight(3);
			}
			
			for (int i = 0; i < 49; i++) {
				fire[i].update();
			}
			
			repaint();
		}
	}
}

// Version 0.0.04