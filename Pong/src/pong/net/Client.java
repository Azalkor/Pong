package pong.net;

import java.awt.Point;
import java.io.DataInputStream;
import java.net.Socket;

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
				s = new Socket(sName, 9849);
				DataInputStream dis = new DataInputStream(s.getInputStream());
				int tmp = dis.readInt();
				Pong.getPlayers()[1].getRacket().setPosition(new Point((int)Pong.getPlayers()[1].getRacket().getPosition().getX(), tmp));
				s.close();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
									
