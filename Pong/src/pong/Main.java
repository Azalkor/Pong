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
		if(args.length!=0){
			Client client = new Client(args[0]);
			client.run();
		}
		else{
			Server server = new Server();
			server.run();
		}
		Pong pong = new Pong();
		Window window = new Window(pong);
		window.displayOnscreen();
	}
}
