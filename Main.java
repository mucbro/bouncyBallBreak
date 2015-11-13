package irakli.peter.bouncyballs;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends Applet implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1460;
	public static final int HEIGHT = 810;
	
	public boolean movingRight = false;
	public boolean movingLeft = false;
	
	public int xPaddle = 650;
	public int yPaddle = 775;
	public int heightPaddle = 15;
	public int widthPaddle = 150;

	public int xBrick = 15;
	public int yBrick = 15;
	public int heightBrick = 20;
	public int widthBrick = 125;
	
	public int xBall = 150;
	public int yBall = 450;
	public int radius = 20;
	public int dx = 8;
	public int dy = -8;
	
	private Image i;
	private Graphics doubleG;
	
	Rectangle paddle = new Rectangle(xPaddle, yPaddle, widthPaddle, heightPaddle);
	Rectangle[] bricks = new Rectangle[42];
	Rectangle ball = new Rectangle(xBall, yBall, radius * 2, radius * 2);
	
	public Main() {
		addBricks();
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
	
	public void init() {
		setSize(WIDTH, HEIGHT);
		setBackground(Color.BLACK);
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		Main main = new Main();
		while(true) {
			move();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void move() {
		if(xBall + dx > this.getWidth() - radius || xBall + dx < radius) {
			dx = -dx;
		} 
		if(yBall + dy > this.getHeight() - radius || yBall + dy < radius) {
			dy = -dy;
		} 
		xBall += dx;
		yBall += dy;
		if(movingRight == true) {
			paddle.x += 10;
		}
		if(movingLeft == true) {
			paddle.x -= 10;
		}
	}
	
	public void stop() {
	}
	
	public void destroy() {
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
	
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(xBall - radius, yBall - radius, 2 * radius, 2 * radius);
		
		g.setColor(Color.BLUE);
		g.fill3DRect(paddle.x, paddle.y, paddle.width, paddle.height, true);
		g.setColor(Color.GREEN);
		for(int i = 0; i < bricks.length; i++) {
			g.fill3DRect(bricks[i].x, bricks[i].y, bricks[i].width, bricks[i].height, true);
		}
	}
}
