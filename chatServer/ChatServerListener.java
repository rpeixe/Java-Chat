package chatServer;

import java.net.*;
import java.io.*;

public class ChatServerListener extends Thread {
	private Socket socket;
	
	public ChatServerListener(Socket socket) {
		super("Chat Server Listener Thread");
		this.socket = socket;
	}
	
	@Override
	public void run() {
		String userMessage;
		ChatServerDistributor distributor = ChatServerDistributor.getInstance();
		try (
		        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			System.out.println("Listening to client");

			while ((userMessage = in.readLine()) != null) {
				distributor.broadcastMessage(userMessage);
				System.out.println("Broadcasting message \"" + userMessage + "\"");
			}
		}
		catch (IOException e) {
			
		}
		finally {
			ChatServerDistributor.getInstance().unsubscribe(socket);
			System.out.println("User left.");
		}
	}
}
