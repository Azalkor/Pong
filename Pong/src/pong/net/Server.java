package pong.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	
	private static ServerSocket ecoute;
	public static ServerSocket getEcoute() {
		return ecoute;
	}
	public Server(){}
	public void run(){
		try {
			ServerSocket ecoute = new ServerSocket(4444);
			System.out.println("coucou");
			while(true) {
				Socket client = ecoute.accept();
				OutputStream os = client.getOutputStream();
				InputStream is = client.getInputStream();
				os.write(2);
				System.out.println(is.read());
				client.close();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
