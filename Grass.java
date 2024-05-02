import java.awt.Graphics;
import java.awt.Color;

public class Grass {
	int x;
	int y;
    int w;
    int h;
    double det;
	Color grass;

    public Grass(int _x, int _y, int _w, int _h, double _det) {
        x = _x;
        y = _y;
		w = _w;
        h = _h;
        det = _det;
    }

    public void grassDraw(Graphics g, int mouseX, int mouseY, int amount, int red1, int green1, int blue1, int red2, int green2, int blue2) {
		for (int i = 0; i < h / det; i++) {
            double ratio = (double)i / (double)(h / det);
            int red = (int)(red1 * (1 - ratio) + red2 * ratio);
            int green = (int)(green1 * (1 - ratio) + green2 * ratio);
            int blue = (int)(blue1 * (1 - ratio) + blue2 * ratio);

            grass = new Color(red, green, blue);
            g.setColor(grass);
            g.fillRect(x + (mouseX - 500) / amount, y + (int)(i * det) + (mouseY - 500) / amount, w, (int)det);

            // System.out.println(red + " " + green + " " + blue + " " + i);
        }
    }
}

// Version 0.0.05