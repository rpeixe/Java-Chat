package chatClient;

import java.net.*;
import java.io.*;

public class ChatClientSendThread extends Thread {
	private Socket socket;
	private String username;
	
	public ChatClientSendThread(Socket socket, String username) {
		super("Chat Client Send Thread");
		this.socket = socket;
		this.username = username;
	}
	
	@Override
	public void run() {
		String userMessage;
		try (
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			out.println(username + " joined");

			while ((userMessage = stdIn.readLine()) != null) {
				out.println(username + ": " + userMessage);
			}

			out.println(username + " quit");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		finally {
			try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
}
