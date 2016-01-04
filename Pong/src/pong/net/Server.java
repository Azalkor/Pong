package pong.net;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import pong.gui.Player;
import pong.gui.Pong;
import pong.gui.Ball;


public class Server extends Thread {

	private static ServerSocket ecoute;
	private boolean isConnected=false;

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
					if(!isConnected){
						isConnected=true;
						Pong.goal();
						for(Player p : Pong.getPlayers()){
							p.setScore(0);
						}
					}
					sleep(Pong.timestep);
					DataOutputStream dos = new DataOutputStream(client.getOutputStream());
					dos.writeInt((int) (Pong.getPlayers()[0].getRacket().getPosition().getY()));
					for(Ball b : Pong.getBalls()){
						dos.writeInt((int)b.getBall_speed().getX());
						dos.writeInt((int)b.getBall_speed().getY());
						dos.writeInt((int)b.getPosition().getX());
						dos.writeInt((int)b.getPosition().getY());
					}
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
