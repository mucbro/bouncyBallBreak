package irakli.peter.bouncyballs.main;


import irakli.peter.bouncyballs.entities.Paddle;
import irakli.peter.bouncyballs.graphics.Colors;
import irakli.peter.bouncyballs.graphics.Screen;
import irakli.peter.bouncyballs.graphics.SpriteSheet;
import irakli.peter.bouncyballs.level.Level;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH/12 * 9;
	public static final int SCALE = 2;
	public static final String NAME = "Game";

	private JFrame frame;
	public int tickCount = 0;
	
	public boolean running = false;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colors = new int[6*6*6];
	
	public Level level;
	public InputHandler input; 
	public Paddle paddle;
	public Screen screen;
	
	public Game() {
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void init() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for(int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5); 
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					
					colors[index++] = rr << 16 | gg << 8 | bb; 
				}
			}
		}
		
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite page.png"));
		input = new InputHandler(this);
		level = new Level("/ adfsa"); //once level is created insert here
		paddle = new Paddle(0, 0, input);   //JOptionPane.showInputDialog(this, "Enter a Username")   can be used to have the user input a name
		level.addEntity(paddle);
	}
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0/60.0;
	
		int ticks = 0;
		int frames = 0;
	
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init(); 
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (shouldRender) {
				frames++;
				render();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0; 
			}
		}
	}

	public void tick() {
		tickCount++; 
		level.tick();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		int xOffset = paddle.x - (screen.width / 2);
		int yOffset = paddle.y - (screen.height / 2);
		
		level.renderTiles(screen, xOffset, yOffset);
		
		for (int x = 0; x < level.width; x++) {
			int color = Colors.get(-1, -1, -1, 000);
			if (x % 10 == 0 && x != 0) {
				color = Colors.get(-1, -1, -1, 500);
			}
		}
	
		level.renderEntities(screen);
		
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255) {
					pixels[x + y * WIDTH] = colors[colorCode];
				}
			}
		}	
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); 
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game().start();
	}

	
}
