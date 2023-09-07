package chatClient;

import java.net.*;
import java.io.*;

public class ChatClientReceiveThread extends Thread {
	private Socket socket;
	
	public ChatClientReceiveThread(Socket socket) {
		super("Chat Client Receive Thread");
		this.socket = socket;
	}
	
	@Override
	public void run() {
		String serverMessage;
		try (
		        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			while ((serverMessage = in.readLine()) != null) {
				System.out.println(serverMessage);
			}
		}
		catch (IOException e) {
			System.out.println("Connection closed. Exiting...");
		}
	}
}
