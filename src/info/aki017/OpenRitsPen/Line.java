package info.aki017.OpenRitsPen;

import java.awt.Color;

public class Line {
	private double[] start;
	private double[] end;
	private int size;
	private Color color;

	/**
	 * 線を作る
	 * @param startX 始点のx座標
	 * @param startY 始点のy座標
	 * @param endX 終点のx座標
	 * @param endY 終点のy座標
	 * @param color 線の色
	 * @param size 線の大きさ
	 */
	public Line(double startX,double startY,double endX,double endY, Color color, int size) {
		start = new double[]{startX,startY};
		end = new double[]{endX,endY};
		this.color = color;
		this.size = size;
	}

	/**
	 * 線の色を取得する
	 * @return 色
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 線のx始点を取得する
	 * @return x始点
	 */
	public int getStartX() {
		return (int) start[0];
	}

	/**
	 * 線のy始点を取得する
	 * @return y始点
	 */
	public int getStartY() {
		return (int) start[1];
	}

	/**
	 * 線のx終点を取得する
	 * @return x終点
	 */
	public int getEndX() {
		return (int) end[0];
	}
	
	/**
	 * 線のy終点を取得する
	 * @return y終点
	 */
	public int getEndY() {
		return (int) end[1];
	}

	/**
	 * 線のサイズを取得する
	 * @return 線のサイズ
	 */
	public int getSize() {
		return size;
	}
}