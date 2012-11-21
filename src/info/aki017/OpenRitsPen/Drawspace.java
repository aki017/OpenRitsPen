package info.aki017.OpenRitsPen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JPanel;

class Drawspace extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7241852600517829935L;
	private Image image;
	private OpenRitsPen openRitsPen;

	public Drawspace(OpenRitsPen openRitsPen) {
		this.openRitsPen = openRitsPen;
		setBackground(Color.white);
		setMinimumSize(new Dimension(250, 250));
		setPreferredSize(new Dimension(620, 700));

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		this.image = toolkit.getImage("images/picture.gif");
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawLines(g);
		g.setColor(Color.black);
		for (int j = 0; j < 5; j++) {
			g.drawLine(100 + j * 100, 650, 200 + j * 100, 650);
			g.drawLine(100 + j * 100, 650, 100 + j * 100, 635);
			g.drawLine(150 + j * 100, 650, 150 + j * 100, 640);
			g.drawLine(100 + j * 100, 650, 100 + j * 100, 635);
			for (int ii = 0; ii < 10; ii++)
				g.drawLine(100 + ii * 10 + j * 100, 650, 100 + ii * 10 + j
						* 100, 645);
			g.drawLine(200 + j * 100, 650, 200 + j * 100, 635);
		}
		g.drawString("0", 96, 663);
		for (int j = 1; j < 6; j++) {
			g.drawString(Integer.toString(j * 100), 90 + j * 100, 663);
		}
	}

	/**
	 * 線をすべて描画します
	 * @param g Graphics
	 */
	private void drawLines(Graphics g) {
		LinkedList<Line> lines = openRitsPen.getLines();
		for (Line line : lines) {
			g.setColor(line.getColor());

			drawWline(line, g);
		}
		if (!lines.isEmpty()) {
			Line lastline = lines.getLast();
			g.drawImage(
					this.image,
					(int) lastline.getEndX() - 3,
					(int) lastline.getEndY() - this.image.getHeight(this) + 3,
					this.image.getWidth(this), this.image.getHeight(this), this);
		}
	}

	/**
	 * 指定したLineの線を引く
	 * @param line 線
	 * @param g Graphics
	 */
	private void drawWline(Line line,Graphics g){
		drawWline(
				(int) line.getStartX(),
				(int) line.getStartY(),
				(int) line.getEndX(),
				(int) line.getEndY(),
				line.getSize(),
				g
				);
	}

	/**
	 * 指定した太さの線を引く
	 * @param x0 始点のx座標
	 * @param y0 始点のy座標
	 * @param x1 終点のx座標
	 * @param y1 終点のy座標
	 * @param size 線の太さ
	 * @param g Graphics
	 */
	private void drawWline(int x0, int y0, int x1, int y1, int size, Graphics g) {
		System.out.println(""+x0+":"+y0+":"+x1+":"+y1+":"+size);
		if( size == 1) {
			g.drawLine(x0, y0, x1, y1);
		}
		int[] x = new int[4];
		int[] y = new int[4];

		int dx = x1 - x0;
		int dy = y1 - y0;
		//長さ
		double d = Math.sqrt(dx * dx + dy * dy);
		
		double sin = dy / d;
		double cos = -dx / d;
		
		//
		int nx = (int) Math.rint(sin * size / 2.0D);
		int ny = (int) Math.rint(cos * size / 2.0D);
		x[0] = (x0 - nx);	x[3] = (x0 + nx);
		y[0] = (y0 - ny);	y[3] = (y0 + ny);
		x[1] = (x1 - nx);	x[2] = (x1 + nx);
		y[1] = (y1 - ny);	y[2] = (y1 + ny);

		//始点の円
		g.fillOval((x[0] + x[3]) / 2 - size / 2, (y[0] + y[3]) / 2 - size / 2, size, size);
		//途中の塗りつぶし
		g.fillPolygon(x, y, 4);
		//終点の円
		g.fillOval((x[1] + x[2]) / 2 - size / 2, (y[1] + y[2]) / 2 - size / 2, size, size);
	}
}