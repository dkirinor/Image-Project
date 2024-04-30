import java.awt.Graphics;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Moon { 
	private int x;
	private int y;
	private BufferedImage moonImg;
	private BufferedImage bloodMoonImg;

	public Moon(int _x, int _y) {
		x = _x;
		y = _y;
		
		try {
			moonImg = ImageIO.read(new File("Moon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bloodMoonImg = ImageIO.read(new File("BloodMoon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void moonDraw(Graphics g, int time) {
		if (time == 2) g.drawImage(moonImg, x, y, null);
		else if (time == 666) g.drawImage(bloodMoonImg, x, y, null);
	}
}

// Version 0.0.04