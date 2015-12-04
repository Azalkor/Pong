package pong.gui;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class PongItem {
	ImageIcon icon;
	protected Image image;
	protected int width;
	protected int height;
	protected Point position = new Point(0, 0);
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Image getImage() {
		return image;
	}

	public PongItem() {}

	public PongItem(Image image, int width, int height, Point position) {
		this.image = image;
		this.width = width;
		this.height = height;
		this.position = position;
	}

	public void animate(){}
}