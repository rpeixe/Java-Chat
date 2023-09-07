package chatClient;

import java.net.*;
import java.io.*;

public class ChatClient {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java -cpp .. chatClient.ChatClient <ip> <port> <username>");
			System.exit(1);
		}

		String ip = args[0];
		int portNumber = Integer.parseInt(args[1]);
		String username = args[2];
		
		try(
			Socket chatSocket = new Socket(ip, portNumber);
		) {
			System.out.println("Client started.");
			ChatClientSendThread outputThread = new ChatClientSendThread(chatSocket, username);
			ChatClientReceiveThread inputThread = new ChatClientReceiveThread(chatSocket);
			outputThread.start();
			inputThread.start();
			outputThread.join();
			inputThread.join();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
