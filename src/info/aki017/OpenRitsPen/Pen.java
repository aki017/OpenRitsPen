package info.aki017.OpenRitsPen;

import java.awt.Color;

public class Pen {
	public enum PenColorKind
	{
		BLACK(Color.black),
		RED(Color.red),
		BLUE(Color.blue),
		GREEN(Color.green),
		ORANGE(Color.orange),
		PINK(Color.pink),
		YELLOW(Color.yellow),
		GRAY(Color.gray);

		private final Color color;

		private PenColorKind(Color color)
		{
			this.color = color;
		}

		public Color toColor()
		{
			return color;
		}
	}

	private double x;
	private double y;
	private double angle;
	private Color color;
	private int size;
	private int speed;

	public Pen(){
		reset();
	}

	/**
	 * 初期化する
	 */
	public void reset(){
		x = 350.0D;
		y = 250.0D;
		angle = 0.0D;
		color = getInitialColor();
		size = 1;
		speed = 200;
	}

	public Color getInitialColor()
	{
		return PenColorKind.BLACK.toColor();
	}

	/**
	 * ペンのx座標を指定する
	 * @param x x座標
	 */
	public void setX(double x){
		this.x = x;
	}

	/**
	 * ペンのy座標を指定する
	 * @param y y座標
	 */
	public void setY(double y){
		this.y = y;
	}

	/**
	 * ペンの角度を指定する
	 * @param angle 角度
	 */
	public void setAngle(double angle){
		this.angle = angle;
	}

	/**
	 * ペンを回転する
	 * @param angle 度数法の角度
	 */
	public void addAngle(int angle) {
		this.angle += Math.toRadians(angle);
	}

	/**
	 * ペンの位置を指定する
	 * @param x x座標
	 * @param y y座標
	 */
	public void setLocate(double x,double y){
		setX(x);
		setY(y);
	}

	/**
	 * ペンの位置を進める
	 * @param len 進める長さ
	 */
	public void forward(int len){
		x += len * Math.cos(angle);
		y += len * Math.sin(angle);
	}

	/**
	 * ペンの色を指定する
	 * @param color Color
	 */
	public void setColor(Color color){
		this.color = color;
	}

	/**
	 * ペンの色を指定する
	 * @param index 色番号
	 */
	public void setColor(int index){
		PenColorKind[] values = PenColorKind.values();
		if(index >= 0 && index < values.length){
			setColor(values[index].toColor());
		}
	}

	/**
	 * ペンの大きさを指定する
	 * @param size サイズ
	 */
	public void setSize(int size){
		this.size = size;
	}

	/**
	 * ペンの速さを指定する
	 * @param speed 速さ
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * ペンのx座標を取得する
	 * @return x座標
	 */
	public double getX(){
		return x;
	}

	/**
	 * ペンのy座標を取得する
	 * @return y座標
	 */
	public double getY(){
		return y;
	}

	/**
	 * ペンの色を取得する
	 * @return 色
	 */
	public Color getColor(){
		return color;
	}

	/**
	 * ペンの大きさを取得する
	 * @return 大きさ
	 */
	public int getSize(){
		return size;
	}

	/**
	 * ペンの速さを取得する
	 * @return 速さ
	 */
	public int getSpeed() {
		return speed;
	}

	public double getAngle() {
		return angle;
	}
}
