package pong.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Thread{
	private Socket s;
	private String sName;
	public Client(String serverName){
		sName = serverName;
	}
	public void run(){
		try {
			s = new Socket(sName, 4444);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			os.write(1);
			System.out.println(is.read());
			s.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

