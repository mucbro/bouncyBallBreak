package irakli.ball;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends Applet implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 1460;
	private static final int HEIGHT = 810;

	public boolean movingRight = false;
	public boolean movingLeft = false;
	public boolean gameOver = false;
	
	private Image i;
	private Graphics doubleG;
	
	private Paddle paddle;
	private Ball ball;
	private Brickset brickSet;
	
	private int numBricks = 42;
	
	public Main() {
		paddle = new Paddle();
		ball = new Ball();
		brickSet = new Brickset(numBricks);
	
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
				movingRight = false;
				movingLeft = false;
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					movingRight = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					movingLeft = true;
				}
			}	
		});
	}
	
	
	private void move() {
		//cant figure out how to add these first two if statements in the ball class, since i cant use this.getWidth in teh ball class, 
		//i cant check to see if the ball hits the edge of the screen.
		if(ball.getX() + ball.getDx() > this.getWidth() - ball.getRadius() || ball.getX() + ball.getDx() < ball.getRadius()) {
			ball.setDx(-ball.getDx());
		}
		if(ball.getY() + ball.getDy() < ball.getRadius()) {
			ball.setDy(-ball.getDy());
		}
		if(ball.getY() + ball.getDy() > this.getHeight() - ball.getRadius()) {
			gameOver = true;
		}
		ball.move();
		if(movingRight == true) {
			paddle.setX(10);
		}
		if(movingLeft == true) {
			paddle.setX(-10);
		}
		
	}
	
	public void init() {
		setSize(WIDTH, HEIGHT);
		setBackground(Color.BLACK);
	
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		while(gameOver == false) {
			move();
			checkYCollision();
			brickSet.checkYBrickCollision(ball);
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkYCollision() {
		if((paddle.getY()) - (ball.getY() + ball.getRadius()) <= 3) {
			if(checkXCollision()) {
				ball.setDy(-ball.getDy());
			}
		}
	}
	
	public boolean checkXCollision() {
		if(ball.getX() > paddle.getX() && ball.getX() < paddle.getX() + paddle.getWidth()) {
			return true;
		}
		return false;
	}

	
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(), 2 * ball.getRadius(), 2 * ball.getRadius());
		g.setColor(Color.BLUE);
		g.fill3DRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), true);
		g.setColor(Color.GREEN);
		for(int i = 0; i < brickSet.bricks.length; i++) {
			g.fill3DRect(brickSet.bricks[i].x, brickSet.bricks[i].y, brickSet.getWidth(), brickSet.getHeight(), true);
		}
		g.setColor(Color.YELLOW);
		g.drawString(brickSet.getScore(), 1400, 700);
		
	
	}
	
	public void update(Graphics g) {
		if(i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);
		g.drawImage(i, 0, 0, this);
		
	}
	
	
}
