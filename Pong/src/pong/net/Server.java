package pong.net;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import pong.gui.Pong;


public class Server extends Thread {

	private static ServerSocket ecoute;

	public static ServerSocket getEcoute() {
		return ecoute;
	}

	public Server() {
	}

	public void run() {
		try {
				ecoute = new ServerSocket(5316);
				while (true) {
					Socket client = ecoute.accept();
					sleep(Pong.timestep);
					DataOutputStream dos = new DataOutputStream(client.getOutputStream());
					dos.writeInt((int) (Pong.getPlayers()[0].getRacket().getPosition().getY()));
					dos.flush();
					DataInputStream dis = new DataInputStream(client.getInputStream());
					int tmp = dis.readInt();
					Pong.getPlayers()[1].getRacket().setPosition(new Point((int)Pong.getPlayers()[1].getRacket().getPosition().getX(), tmp));
					client.close();
				}
		} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
		}
	}
}
