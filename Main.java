package irakli.peter.bouncyballs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements KeyListener, ActionListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 720;
	public static final int HEIGHT = WIDTH/16 * 9;
	public static final int SCALE = 2;
	public static final String NAME = "Peter and Irakli-- BrickBreak";
	
	public boolean movingRight = false;
	public boolean movingLeft = false;
	
	public int xBall = 500;
	public int yBall = 500;
	public int heightBall = 15;
	public int widthBall = 15;
	public int xPaddle = 650;
	public int yPaddle = 780;
	public int heightPaddle = 15;
	public int widthPaddle = 150;
	public int xBrick = 15;
	public int yBrick = 25;
	public int heightBrick = 20;
	public int widthBrick = 125;
	
	
	Rectangle ball = new Rectangle(xBall, yBall, widthBall, heightBall);
	Rectangle paddle = new Rectangle(xPaddle, yPaddle, widthPaddle, heightPaddle);
	Rectangle[] bricks = new Rectangle[42];
	
	public Main() {
		JFrame frame = new JFrame(NAME); 
		JButton b = new JButton("start over");
		
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.add(b, BorderLayout.SOUTH);
		b.addActionListener(this);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(ball.x, ball.y, ball.width, ball.height);
		g.setColor(Color.BLUE);
		g.fill3DRect(paddle.x, paddle.y, paddle.width, paddle.height, true);
		g.setColor(Color.RED);
		for(int i = 0; i < bricks.length; i++) {
			if(bricks[i] != null) {
				g.fill3DRect(bricks[i].x, bricks[i].y, bricks[i].width, bricks[i].height, true);
			}
		}
	}
	
	public void run() {
		for(int i = 0; i < bricks.length; i++) {
			bricks[i] = new Rectangle(xBrick, yBrick, widthBrick, heightBrick);
			if(i == 10) {
				xBrick = -55;
				yBrick = 50;
			}
			if(i == 20) {
				xBrick = -115;
				yBrick = 75;
			}
			if (i == 31) {
				xBrick = -55;
				yBrick = 100;
			}
			xBrick += 130;
		}
		
	}


	
	
	
	public static void main(String[] args) {
		Main game = new Main();
		
		game.addKeyListener(game);
		game.setFocusable(true);
		
		Thread t = new Thread(game);
		t.start();
	}
	
	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void actionPerformed(ActionEvent e) {
	
	
	}
	
	
	
	

}
