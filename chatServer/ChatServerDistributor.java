package chatServer;

import java.net.*;
import java.util.LinkedList;
import java.io.*;

public class ChatServerDistributor {
	private static ChatServerDistributor instance;
	private LinkedList<Socket> clientSockets = new LinkedList<Socket>();
	
	private ChatServerDistributor() {
		
	}
	
	public synchronized static ChatServerDistributor getInstance() {
		if (instance == null) {
			instance = new ChatServerDistributor();
		}
		return instance;
	}
	
	public synchronized void subscribe(Socket socket) {
		clientSockets.add(socket);
	}
	
	public synchronized void unsubscribe(Socket socket) {
		clientSockets.remove(socket);
	}
	
	public synchronized void broadcastMessage(String message) {
		for (Socket socket : clientSockets) {
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println(message);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
