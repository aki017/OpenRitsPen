package info.aki017.OpenRitsPen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class Protractor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7643375526500827466L;
	private Image image;
	private OpenRitsPen openRitsPen;
	
	public Protractor(OpenRitsPen openRitsPen) {
		this.openRitsPen = openRitsPen;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		image = toolkit.getImage("images/bundokis.gif");
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d= (Graphics2D) g;
		g2d.clearRect(0, 0, 500, 500);
		AffineTransform af = new AffineTransform();
		double[] flatmatrix = new double[6];
		af.setToRotation(openRitsPen.getAngle(), 90.0D, 40.0D);
		af.getMatrix(flatmatrix);
		g2d.setTransform(af);
		g2d.drawImage(image, 10, 20, image.getWidth(this),image.getHeight(this), this);
	}
}