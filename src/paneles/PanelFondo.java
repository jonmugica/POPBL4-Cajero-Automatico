package paneles;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFondo extends JPanel {

	private Image background;

	public PanelFondo() {
		this.setLayout(new BorderLayout());
	}
	public void paintComponent(Graphics g) {


		if (this.background != null) {
			g.drawImage(this.background, 0, 0, 1920, 1080, null);
		}

		super.paintComponent(g);
	}

	public void setBackground(String imagePath) {
		
		this.setOpaque(false);
		this.background = new ImageIcon(imagePath).getImage();
		repaint();
	}

}