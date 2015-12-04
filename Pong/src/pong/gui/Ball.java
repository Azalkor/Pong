package pong.gui;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Ball extends PongItem {

	/**
	 * Speed of ball (in pixels per second)
	 */
	public static final int BALL_SPEED = -2;
	public static final int MAX_SPEED = 10;
	public static final int MIN_SPEED = 2;
	
	private Point ball_speed = new Point(BALL_SPEED, BALL_SPEED);

	public Point getBall_speed() {
		return ball_speed;
	}

	public void setBall_speed(Point ball_speed) {
		//Contrôle de x
		if(Math.abs(ball_speed.getX())<MIN_SPEED || Math.abs(ball_speed.getX())>MAX_SPEED){
			if(Math.abs(ball_speed.getX())<MIN_SPEED){
				if(ball_speed.getX()<0){
					ball_speed.setLocation(-MIN_SPEED, ball_speed.getY());
				}
				else{
					ball_speed.setLocation(MIN_SPEED, ball_speed.getY());
				}
			}
			else{
				if(ball_speed.getX()<0){
					ball_speed.setLocation(-MAX_SPEED, ball_speed.getY());
				}
				else{
					ball_speed.setLocation(MAX_SPEED, ball_speed.getY());
				}
			}
		}
		//Contrôle de y
		if(Math.abs(ball_speed.getY())<MIN_SPEED || Math.abs(ball_speed.getY())>MAX_SPEED){
			if(Math.abs(ball_speed.getY())<MIN_SPEED){
				if(ball_speed.getY()<0){
					ball_speed.setLocation(ball_speed.getX(),-MIN_SPEED);
				}
				else{
					ball_speed.setLocation(ball_speed.getX(),MIN_SPEED);
				}
			}
			else{
				if(ball_speed.getY()<0){
					ball_speed.setLocation(ball_speed.getX(),-MAX_SPEED);
				}
				else{
					ball_speed.setLocation(ball_speed.getX(),MAX_SPEED);
				}
			}
		}
		this.ball_speed = ball_speed;
	}

	public Ball() {
		super();
		setPosition(new Point(Pong.SIZE_PONG_X / 2, Pong.SIZE_PONG_Y / 2));
		image = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("image/ball.png"));
		super.icon = new ImageIcon(this.image);
		this.width = icon.getIconWidth();
		this.height = icon.getIconHeight();
	}

	public void animate() {
		Point pos = this.getPosition();
		pos.translate(getBall_speed().x, getBall_speed().y);
		this.setPosition(pos);
//		if (pos.x < 0)
//		{
//			GAME OVER LOST
//		}
//		if (pos.x > Pong.SIZE_PONG_X - this.getWidth())
//		{
//			GAME OVER WIN
//		}
		if (pos.y < 0) {
			this.setPosition(new Point(this.getPosition().x, 0));
			setBall_speed(new Point(getBall_speed().x, -getBall_speed().y));
		}
		if (pos.y > Pong.SIZE_PONG_Y - this.getHeight()) {
			this.setPosition(new Point(this.getPosition().x, Pong.SIZE_PONG_Y
					- this.getHeight()));
			setBall_speed(new Point(getBall_speed().x, -getBall_speed().y));
		}
		racketCollision(pos);
	}

	public void racketCollision(Point initialPos) {
		for(int i=0; i<Pong.NB_PLAYERS; i++){
			Point pos = new Point((int)(initialPos.getX()+getWidth()/2), (int)(initialPos.getY()+getHeight()/2));
			if(i%2==0){
				racketCollisionPair(Pong.getPlayers()[i].getRacket(), pos);
			}
			else{
				racketCollisionImpair(Pong.getPlayers()[i].getRacket(), pos);
			}
		}
	}
	
	public void racketCollisionPair(Racket r, Point pos){
		if (pos.x <= r.getWidth()
				&& pos.x >= r.getPosition().getX()
				&& pos.y >= r.getPosition().getY()
				&& pos.y <= r.getPosition().getY()+r.getHeight()
				&& getBall_speed().getX()<0)
		{
			if(pos.y <= r.getPosition().getY()+(r.getHeight()*0.15)){
				if(getBall_speed().getY()>0){
					setBall_speed(new Point(-getBall_speed().x, -getBall_speed().y));
				}
				else{
					setBall_speed(new Point(-getBall_speed().x-1, getBall_speed().y+1));
				}
			}
			else if(pos.y >= r.getPosition().getY()+(r.getHeight()*0.85)){
				if(getBall_speed().getY()<0){
					setBall_speed(new Point(-getBall_speed().x, -getBall_speed().y));
				}
				else{
					setBall_speed(new Point(-getBall_speed().x-1, getBall_speed().y+1));
				}
			}
			else{
				setBall_speed(new Point(-getBall_speed().x+1, getBall_speed().y-1));
			}
		}
	}
	
	public void racketCollisionImpair(Racket r, Point pos){
		if (pos.x > r.getPosition().getX()
				&& pos.x < Pong.SIZE_PONG_X
				&& pos.y >= r.getPosition().getY()
				&& pos.y <= r.getPosition().getY()+r.getHeight()
				&& getBall_speed().getX()>0)
		{
			if(pos.y <= r.getPosition().getY()+(r.getHeight()*0.15)){
				if(getBall_speed().getY()>0){
					setBall_speed(new Point(-getBall_speed().x, -getBall_speed().y));
				}
				else{
					setBall_speed(new Point(-getBall_speed().x+1, getBall_speed().y+1));
				}
			}
			else if(pos.y >= r.getPosition().getY()+(r.getHeight()*0.85)){
				if(getBall_speed().getY()<0){
					setBall_speed(new Point(-getBall_speed().x, -getBall_speed().y));
				}
				else{
					setBall_speed(new Point(-getBall_speed().x+1, getBall_speed().y+1));
				}
			}
			else{
				setBall_speed(new Point(-getBall_speed().x-1, getBall_speed().y-1));
			}
		}
	}
}
