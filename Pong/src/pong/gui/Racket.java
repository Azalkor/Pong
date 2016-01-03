package pong.gui;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Racket extends PongItem {

	/**
	 * Speed of racket (in pixels per second)
	 */
	public static final int RACKET_SPEED = 5;

	private int speed;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int racket_speed) {
		this.speed = racket_speed;
	}

	public Racket() {
		super();
		image = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("image/racket.png"));
		super.icon = new ImageIcon(this.image);
		this.width = icon.getIconWidth();
		this.height = icon.getIconHeight();
	}
	
	public void reset(){
		setPosition(new Point(position.x,Pong.SIZE_PONG_Y/2-height/2));
	}

	public void animate() {
		Point pos = this.getPosition();
		pos.y += getSpeed();
		this.setPosition(pos);
		if (pos.y < 0) {
			this.setPosition(new Point(this.getPosition().x, 0));
		}
		if (pos.y > Pong.SIZE_PONG_Y - this.getHeight()) {
			this.setPosition(new Point(this.getPosition().x, Pong.SIZE_PONG_Y - this.getHeight()));
		}
	}
}
