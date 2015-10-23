package irakli.peter.bouncyballs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1440;
	public static final int HEIGHT = 810;
	public static final String NAME = "Peter and Irakli-- BrickBreak";
	
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
	
	public int xBall = 100;
	public int yBall = 100;
	public int radius = 8;
	public int dx = 8;
	public int dy = 8;
	
	Rectangle paddle = new Rectangle(xPaddle, yPaddle, widthPaddle, heightPaddle);
	Rectangle[] bricks = new Rectangle[42];
	Rectangle ball = new Rectangle(xBall, yBall, radius * 2, radius * 2);
	
	public Main() {
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
		setFocusable(true);	
		addBricks();
		int delay = 20;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move();
				repaint();
			}
		};
		new Timer(delay, taskPerformer).start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillOval(xBall, yBall, radius * 2, radius * 2);
		g.setColor(Color.BLUE);
		g.fill3DRect(paddle.x, paddle.y, paddle.width, paddle.height, true);
		g.setColor(Color.GREEN);
		for(int i = 0; i < bricks.length; i++) {
			g.fill3DRect(bricks[i].x, bricks[i].y, bricks[i].width, bricks[i].height, true);
		}
	}
	
	public void move() {
		if(xBall < 0 || xBall > WIDTH) {
			dx = -dx;
		}
		if(yBall < 0 || yBall > HEIGHT) {
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
	
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame(NAME);
				frame.add(new Main());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.getContentPane().setBackground(Color.BLACK);   //this isn't working, don't know why.
			}
		});
	}
}
