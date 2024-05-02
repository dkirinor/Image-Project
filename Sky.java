import java.awt.Graphics;
import java.awt.Color;

public class Sky {
	int y;
	int sy;
    int w;
    int h;
    double det;
	Color sky;

    public Sky(int _sy, int _w, int _h, double _det) {
        sy = _sy;
		y = sy;
		w = _w;
        h = _h;
        det = _det;
    }

    public void skyDraw(Graphics g, int red1, int green1, int blue1, int alpha1, int red2, int green2, int blue2, int alpha2) {
        for (int i = 0; i < h / det; i++) {
            double ratio = (double)i / (double)(h / det);
            int red = (int)(red1 * (1 - ratio) + red2 * ratio);
            int green = (int)(green1 * (1 - ratio) + green2 * ratio);
            int blue = (int)(blue1 * (1 - ratio) + blue2 * ratio);
			int alpha = (int)(alpha1 * (1 - ratio) + alpha2 * ratio);

            sky = new Color(red, green, blue, alpha);
            g.setColor(sky);
            g.fillRect(0, (int)(i * det) + y, w, (int)det);

            // System.out.println(red + " " + green + " " + blue + " " + i);
        }
    }
	
	public void moveSky(double i) {
		y = (int)(Math.cos(i) * h) + sy - h;
	}
}

// Version 0.0.05