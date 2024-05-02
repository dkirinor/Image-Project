import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Scenery extends JPanel implements MouseListener, MouseMotionListener {
	Sky skyDay, skyRiseSet, skyNight;
	Sun sun;
	Moon moon;
	Cloud[] cloud = new Cloud[5];
	Cloud[] cloud2 = new Cloud[3];
	Rain[] rain = new Rain[25];
	Rain[] rain2 = new Rain[25];
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
	int cycle = 0;
	int houseBurnt = 0; // 0 = fine, 1 = burning, 2 = burnt
	int raining = 0; // 0 = none, 1+ = raining
	long eventTimer = 0;
	int eventWait = 1200;
	int changeTime = 0;
	double sunI = 0;
	double skyI = 0;
	
	public Scenery() {
		addMouseListener(this);
		addMouseMotionListener(this);
		
		skyDay = new Sky(0, 1000, 500, 10);
		skyRiseSet = new Sky(500, 1000, 500, 10);
		skyNight = new Sky(1000, 1000, 500, 10);
		
		sun = new Sun(100, 50, 200);
		
		moon = new Moon(800, 200);
		
		for (int i = 0; i < 4; i++) {
			cloud[i] = new Cloud(i * 400, (int)(Math.random() * 300));
		}
		for (int i = 0; i < 2; i++) {
			cloud2[i] = new Cloud(i * 800 + 200, (int)(Math.random() * 300));
		}
		
		for (int i = 0; i < 24; i++) {
			rain[i] = new Rain((int)(Math.random() * 1000), i * -35 + 500, 500, 35, 2);
			rain2[i] = new Rain((int)(Math.random() * 1000), i * -100 + 1000, 1000, 50, 3);
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
			fire[i] = new Fire(300, 900, 475, 200, 5, 10);
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

		skyDay.skyDraw(g, 150, 230, 255, 255, 50, 150, 255, 255);
		skyRiseSet.skyDraw(g, 50, 150, 255, 255, 230, 110, 80, 255);
		skyNight.skyDraw(g, 20, 5, 80, 255, 5, 5, 10, 255);
		if (time == 666) skyNight.skyDraw(g, 40, 10, 0, 255, 200, 5, 5, 255);
		
		if (time == 0) sun.sunDraw(g, 0);
		if (time == 1 || time == 3) sun.sunDraw(g, 150);
		
		moon.moonDraw(g, time);
		
		for (int i = 0; i < 4; i++) {
			cloud[i].cloudDraw(g, 0.8, mouseX, mouseY, 250, time);
		}
		
		if (raining >= 1) {
			for (int i = 0; i < 24; i++) {
				rain[i].rainDraw(g, mouseX, mouseY, 250, time);
			}
		}
		
		mountain.mountainDraw(g, mouseX, mouseY, 200, mountainCol, time);
		
		for (int i = 0; i < 2; i++) {
			cloud2[i].cloudDraw(g, 1.2, mouseX, mouseY, 175, time);
		}
		
		mountain2.mountainDraw(g, mouseX, mouseY, 150, mountain2Col, time);
		
		mountain3.mountainDraw(g, mouseX, mouseY, 100, mountain3Col, time);
		
		house.houseDraw(g, mouseX, mouseY, 20, houseBurnt);
		
		if (time == 666) {
			for (int i = 0; i < 49; i++) {
				fire[i].fireDraw(g, mouseX, mouseY, 20);
			}
		}
		
		if (raining >= 1) {
			for (int i = 0; i < 24; i++) {
				rain2[i].rainDraw(g, mouseX, mouseY, 20, time);
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
		
		// g.setColor(borders);
		// g.fillRect(1000, 0, 500, 1000);
		// g.fillRect(0, 1000, 1500, 500);
	}
	
	double temp = 0;
	public void mouseClicked(MouseEvent e) {
		// System.out.println("click");
		
		if (System.currentTimeMillis() - eventTimer > eventWait) {
			if (changeTime == 0) {
				cycle++;
				changeTime = 1;
				
				if (time == 1 && Math.random() * 100 < ((cycle * 0.01 > 2) ? 2 : cycle * 0.01)) {
					time = 666;
					houseBurnt = (houseBurnt == 0) ? 1 : 2;
					eventTimer = System.currentTimeMillis();
				}
				else if (time == 666) {
					if (System.currentTimeMillis() - eventTimer > eventWait) {
						time = 3;
						houseBurnt = 2;
					}
				}
				else if (time == 3) time = 0;
				else time++;
				// System.out.println(time);
				
				if (raining == 0 && (Math.random() * 100 < 8 || time == 666)) {
					raining = (int)(Math.random() * 6) + 1;
				} else if (raining >= 1 && System.currentTimeMillis() - eventTimer > eventWait) raining--;
				// System.out.println(raining);
			} else {
				changeTime = 0;
				skyI += 158 - (skyI % 158);
				skyDay.moveSky(skyI / 100);
				skyRiseSet.moveSky(skyI / 100);
				skyNight.moveSky(skyI / 100);
			}
			
		}
		// System.out.println(cycle / 4);
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
	
	public void animate() {
		for(;;) {
			try {
				Thread.sleep(15);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			// System.out.println("ran");
			
			if (changeTime == 1) {
				skyDay.moveSky(skyI / 100);
				skyRiseSet.moveSky(skyI / 100);
				skyNight.moveSky(skyI / 100);
				skyI += 2;
			}
			if (skyI % 158 == 0) {
				changeTime = 0;
			}
			System.out.println(skyI);
			
			sun.expand(Math.sin(sunI) * 25 + 325);
			sunI += 0.01;
			
			for (int i = 0; i < 4; i++) {
				cloud[i].moveRight(2);
			}
			for (int i = 0; i < 2; i++) {
				cloud2[i].moveRight(3);
			}
			
			if (raining >= 1) {
				for (int i = 0; i < 24; i++) {
					rain[i].rainDown(15);
					rain2[i].rainDown(25);
				}
			}
			
			for (int i = 0; i < 49; i++) {
				fire[i].update();
			}
			
			repaint();
		}
	}
}

// Version 0.0.05