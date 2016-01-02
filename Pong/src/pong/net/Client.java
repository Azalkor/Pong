package pong.net;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
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
				DataInputStream dIn = new DataInputStream(s.getInputStream());
				int readInt = dIn.readInt();
				System.out.println("coucou"+readInt);
				Pong.getPlayers()[1].getRacket().setPosition(new Point((int)Pong.getPlayers()[1].getRacket().getPosition().getX(), readInt));
				s.close();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
									
