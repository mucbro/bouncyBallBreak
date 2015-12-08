package irakli.ball;

public class Ball {

	private int xBall = 725;
	private int yBall = 425;
	private int radius = 15;
	private int dx = 8;
	private int dy = -8;
	
	public Ball() {
		
	}
	
	public void move() {
		xBall += dx;
		yBall += dy;
	}
	
	public int getX() {
		return xBall;
	}
	
	public int getY() {
		return yBall;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}

	public void setX(int newX) {
		xBall += newX;
	}
	
	public void setY(int newY) {
		yBall += newY;
	}
	
	public void setDx(int newDx) {
		dx = newDx;
	}
	
	public void setDy(int newDy) {
		dy = newDy;
	}
	
	public void setRadius(int newRadius) {
		radius = newRadius;
	}
	
	
	
}
