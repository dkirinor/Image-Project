import javax.swing.JFrame;

public class Images {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Scenery");
	
		Scenery sc = new Scenery();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(sc);
		frame.pack();
		frame.setVisible(true);
		
		sc.animate();
	}
}

// Version 0.0.04