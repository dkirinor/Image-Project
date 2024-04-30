import java.awt.Graphics;
import java.awt.Color;

public class Sky {
    int w;
    int h;
    double det;

    public Sky(int _w, int _h, double _det) {
        w = _w;
        h = _h;
        det = _det;
    }

    public void skyDraw(Graphics g, int red1, int green1, int blue1, int red2, int green2, int blue2) {
        // Color sky1 = new Color(red1, green1, blue1);
        // Color sky2 = new Color(red2, green2, blue2);

        for (int i = 0; i < h / det; i++) {
            double ratio = (double)i / (double)(h / det);
            int red = (int)(red1 * (1 - ratio) + red2 * ratio);
            int green = (int)(green1 * (1 - ratio) + green2 * ratio);
            int blue = (int)(blue1 * (1 - ratio) + blue2 * ratio);

            Color sky = new Color(red, green, blue);
            g.setColor(sky);
            g.fillRect(0, (int)(i * det), w, (int)det);

            // System.out.println(red + " " + green + " " + blue + " " + i);
        }
    }
}

// Version 0.0.04