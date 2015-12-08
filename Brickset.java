package irakli.ball;

import java.awt.Rectangle;

public class Brickset {
	
	private int xBrick = 15;
	private int yBrick = 15;
	private int heightBrick = 20;
	private int widthBrick = 125;
	
	private int score = 0;
	Rectangle[] bricks;
	private int numBricks;
	
	public Brickset(int num) {
		numBricks = num;
		bricks = new Rectangle[numBricks];
		addBricks();
	}
	
	private void addBricks() {
		for(int i = 0; i < bricks.length; i++) {
			bricks[i] = new Rectangle(xBrick, yBrick, widthBrick, heightBrick);
			if(i == 10) {
				xBrick = -55;
				yBrick = 40;
			}
			if(i == 20) {
				xBrick = -115;
				yBrick = 65;
			}
			if (i == 31) {
				xBrick = -55;
				yBrick = 90;
			}
			xBrick += 130;
		}
	}
	
	public void checkYBrickCollision(Ball ball) {
		for(int i = 0; i < bricks.length; i++) {
			if((ball.getY() - ball.getRadius()) - (bricks[i].y + heightBrick) <= 3) {
				if(ball.getX() > bricks[i].x && ball.getX() < (bricks[i].x + widthBrick)) {
					bricks[i].setLocation(-500, -500);
					ball.setDy(-ball.getDy());
					score += 100;
				}
			}
		}
	}
	
	public String getScore() {
		String points = Integer.toString(score);
		return points;
	}
	
	
	public int getSize() {
		return numBricks;
	}
	
	
	public int getWidth() {
		return widthBrick;
	}
	
	public int getHeight() {
		return heightBrick;
	}
	
	public void setX(int newX) {
		xBrick = newX;
	}
	
	public void setY(int newY) {
		yBrick = newY;
	}
	
	
	
}