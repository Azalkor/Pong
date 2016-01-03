package pong.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * An Pong is a Java graphical container that extends the JPanel class in
 * order to display graphical elements.
 */
public class Pong extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Constant (c.f. final) common to all Pong instances (c.f. static)
	 * defining the background color of the Pong
	 */
	private static final Color backgroundColor = new Color(0xFF, 0x40, 0);

	/**
	 * Width of pong area
	 */
	public static final int SIZE_PONG_X = 800;
	/**
	 * Height of pong area
	 */
	public static final int SIZE_PONG_Y = 600;
	/**
	 * Time step of the simulation (in ms)
	 */
	public static final int timestep = 10;
	/**
	 * Pixel data buffer for the Pong rendering
	 */
	private Image buffer = null;
	/**
	 * Graphic component context derived from buffer Image
	 */
	private Graphics graphicContext = null;
	private Label l0,l1;
	private static ArrayList<PongItem> items;
	private static Player players[];
	private static int NB_PLAYERS = 2;
	private static int SCORE_WIN = 1;
	private static String WINNER;
	
	public static String getWINNER() {
		return WINNER;
	}

	public static int getNB_PLAYERS() {
		return NB_PLAYERS;
	}

	public static int getSCORE_WIN() {
		return SCORE_WIN;
	}

	public static Player[] getPlayers() {
		return players;
	}

	public Pong() {
		players=new Player[NB_PLAYERS];
		items = new ArrayList<PongItem> ();
		Racket r0 = new Racket();
		r0.setPosition(new Point(0,Pong.SIZE_PONG_Y/2- r0.getHeight()/2));
		players[0]=new Player("Gertrude", r0);
		Racket r1 = new Racket();
		r1.setPosition(new Point(SIZE_PONG_X-r1.getWidth(), SIZE_PONG_Y/2 - r1.getHeight()/2));
		players[1]=new Player("Jean-Eude", r1);
		items.add(players[0].getRacket());
		items.add(players[1].getRacket());
		items.add(new Ball());
		Panel p = new Panel();
		l0 = new Label (players[0].getName()+" : 10");
		l1 = new Label (players[1].getName()+" : 10");
		p.add(l0);
		p.add(l1);
		p.setBackground(backgroundColor);
		add(p);
		this.setPreferredSize(new Dimension(SIZE_PONG_X, SIZE_PONG_Y));
		this.addKeyListener(this);
	}
	
	public static void goal(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		for(PongItem i : items){
			i.reset();
		}
	}
	
	public static void gameOver(String winner){
		WINNER = winner;
	}

	/**
         * Proceeds to the movement of the ball and updates the screen
	 */
	public void animate() {
		for(PongItem item : items){
			item.animate();
		}
		/* update output */
		updateScreen();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				players[0].getRacket().setSpeed(-Racket.RACKET_SPEED);
				break;
			case KeyEvent.VK_DOWN:
				players[0].getRacket().setSpeed(Racket.RACKET_SPEED);
				break;
			case KeyEvent.VK_Z:
				players[1].getRacket().setSpeed(-Racket.RACKET_SPEED);
				break;
			case KeyEvent.VK_S:
				players[1].getRacket().setSpeed(Racket.RACKET_SPEED);
				break;
			default:
				System.out.println("got press "+e);
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			players[0].getRacket().setSpeed(0);
			break;
		case KeyEvent.VK_DOWN:
			players[0].getRacket().setSpeed(0);
			break;
		case KeyEvent.VK_Z:
			players[1].getRacket().setSpeed(0);
			break;
		case KeyEvent.VK_S:
			players[1].getRacket().setSpeed(0);
			break;
			default:
				System.out.println("got release "+e);
		}
	}
	public void keyTyped(KeyEvent e) { }

	/*
	 * (non-Javadoc) This method is called by the AWT Engine to paint what
	 * appears in the screen. The AWT engine calls the paint method every time
	 * the operative system reports that the canvas has to be painted. When the
	 * window is created for the first time paint is called. The paint method is
	 * also called if we minimize and after we maximize the window and if we
	 * change the size of the window with the mouse.
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	/**
	 * Draw each Pong item based on new positions
	 */
	public void updateScreen() {
		if (buffer == null) {
			/* First time we get called with all windows initialized */
			buffer = createImage(SIZE_PONG_X, SIZE_PONG_Y);
			if (buffer == null)
				throw new RuntimeException("Could not instanciate graphics");
			else
				graphicContext = buffer.getGraphics();
		}
		/* Fill the area with blue */
		graphicContext.setColor(backgroundColor);
		graphicContext.fillRect(0, 0, SIZE_PONG_X, SIZE_PONG_Y);

		/* Draw items */
		for(PongItem i : items){
			graphicContext.drawImage(i.image, i.getPosition().x, i.getPosition().y, i.getWidth(), i.getHeight(), null);
		}
		l0.setText(getPlayers()[0].getName()+" : "+getPlayers()[0].getScore());
		l1.setText(getPlayers()[1].getName()+" : "+getPlayers()[1].getScore());
		this.repaint();
	}
}
