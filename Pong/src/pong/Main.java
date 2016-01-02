package pong;

import pong.gui.Pong;
import pong.gui.Window;
import pong.net.Client;
import pong.net.Server;

/**
 * Starting point of the Pong application
 */
public class Main  {
	public static void main(String[] args) {
		Pong pong = new Pong();
		if(args.length!=0){
			Client client = new Client(args[0]);
			client.start();
		}
		else{
			Server server = new Server();
			server.start();
		}
		
		Window window = new Window(pong);
		window.displayOnscreen();
	}
}
