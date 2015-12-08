package irakli.ball;

public class Paddle {

	private int xPaddle = 650;
	private int yPaddle = 775;
	private int heightPaddle = 15;
	private int widthPaddle = 150;
	
	public Paddle() {	
		
	}

	
	public int getX() {
		return xPaddle;
	}
	
	public int getY() {
		return yPaddle;
	}
	
	public int getWidth() {
		return widthPaddle;
	}
	
	public int getHeight() {
		return heightPaddle;
	}
	
	public void setX(int newX) {
		xPaddle = xPaddle += newX;
	}
	
	public void setY(int newY) {
		yPaddle = xPaddle += newY;
	}



	



	
}
