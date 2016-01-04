package pong.net;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import pong.gui.Ball;
import pong.gui.Pong;

public class Client extends Thread {
	private Socket s;
	private String sName;

	public Client(String serverName) {
		sName = serverName;
	}

	public void run() {
		while (true) {
			try {
				s = new Socket(sName, 5316);
				DataInputStream dis = new DataInputStream(s.getInputStream());
				Pong.getPlayers()[1].getRacket().setPosition(new Point((int)Pong.getPlayers()[1].getRacket().getPosition().getX(), dis.readInt()));
				for(Ball b : Pong.getBalls()){
					b.setBall_speed(new Point(-dis.readInt(), dis.readInt()));
					b.setPosition(new Point(Pong.SIZE_PONG_X-b.getWidth()-dis.readInt(), dis.readInt()));
				}
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt((int) (Pong.getPlayers()[0].getRacket().getPosition().getY()));
				dos.flush();
				sleep(Pong.timestep);
				s.close();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
									
