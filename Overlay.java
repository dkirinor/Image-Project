import java.awt.Graphics;
import java.awt.Color;

public class Overlay {
    Color[] colors; // day, setRise, night, blood;
    int prevColor;
    double percent;

    public Overlay() {
        colors = new Color[1000];
        colors[0] = new Color(255, 255, 0, 20);
        colors[1] = new Color(50, 0, 50, 90);
        colors[2] = new Color(0, 0, 0, 180);
		colors[3] = new Color(50, 0, 50, 90);
		colors[665] = new Color(255, 245, 245, 225);
        colors[666] = new Color(100, 0, 0, 160);

        percent = 0;
    }

    public void overlayDraw(Graphics g, int time, int cycle, int changeTime) {
		Color temp;
		if (cycle == 0) {
			temp = colors[0];
		} else {
			if (time > 0) prevColor = time - 1;
			if (time == 0) prevColor = 3;
			if (time == 666 && changeTime == 0) {
				prevColor = 665;
				// System.out.println("e");
			}
			if (time == 666 && changeTime == 1) {
				time = 2;
				prevColor = 1;
			}
			
			int red = (int)(colors[prevColor].getRed() + (colors[time].getRed() - colors[prevColor].getRed()) * percent);
			int green = (int)(colors[prevColor].getGreen() + (colors[time].getGreen() - colors[prevColor].getGreen()) * percent);
			int blue = (int)(colors[prevColor].getBlue() + (colors[time].getBlue() - colors[prevColor].getBlue()) * percent);
			int alpha = (int)(colors[prevColor].getAlpha() + (colors[time].getAlpha() - colors[prevColor].getAlpha()) * percent);

			temp = new Color(red, green, blue, alpha);
		}

        g.setColor(temp);
        g.fillRect(0, 0, 1000, 1000);
    }

    public void update(double speed) {
        if (percent < 1) percent += speed;
    }
	
	public void resetPercent() {
		percent = 0;
	}
}

// Version 0.0.06