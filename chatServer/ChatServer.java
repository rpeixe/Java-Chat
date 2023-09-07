package chatServer;

import java.net.*;
import java.io.*;

public class ChatServer {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java -cpp .. chatServer.ChatServer <port>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);
		
		try(
			ServerSocket serverSocket = new ServerSocket(portNumber);
		) {
			ChatServerDistributor distributor = ChatServerDistributor.getInstance();
			System.out.println("Server started.");
			while (true) {
				System.out.println("Waiting for client...");
				Socket clientSocket = serverSocket.accept();
				new ChatServerListener(clientSocket).start();
				distributor.subscribe(clientSocket);
				System.out.println("Accepted client.");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
