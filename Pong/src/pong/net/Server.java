package pong.net;

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
				ecoute = new ServerSocket(9849);
				while (true) {
					Socket client = ecoute.accept();
					sleep(Pong.timestep);
					DataOutputStream dOut = new DataOutputStream(
							client.getOutputStream());
	
					dOut.writeInt((int) (Pong.getPlayers()[0].getRacket().getPosition().getY()));
					dOut.flush(); // Send off the data
					client.close();
				}
		} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
		}
	}
}
