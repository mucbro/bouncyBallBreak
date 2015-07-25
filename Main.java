package irakli.peter.bouncyballs;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	
	public int xBall;
	public int yBall;
	public int xPaddle;
	public int yPaddle;
	
	Rectangle ball = new Rectangle(xBall, yBall, 5, 5);
	Rectangle paddle = new Rectangle(xPaddle, yPaddle, 40, 5);
	Rectangle[] bricks = new Rectangle[12];
	
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
	
	
	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

	
	public void keyTyped(KeyEvent e) {
		
	}

	public void run() {
		
	}

	public void actionPerformed(ActionEvent e) {
	
	}
	
	
	
	
	public static void main(String[] args) {
		Main game = new Main();
		
		game.addKeyListener(game);
		game.setFocusable(true);
		
		Thread t = new Thread(game);
		t.start();
	}
	
	
	
	
	
	
	

}
