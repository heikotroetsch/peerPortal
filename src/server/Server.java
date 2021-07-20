package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	 static final int PORT = 12345;
	 public static HashMap<String, Socket> clients = new HashMap<>();

	    public static void main(String[] args) {
	        ServerSocket serverSocket = null;
	        Socket socket = null;

	        try {
	            serverSocket = new ServerSocket(PORT);
	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	        while (true) {
	            try {
	                socket = serverSocket.accept();
	                clients.put(socket.getInetAddress().getHostAddress(), socket);
	            } catch (IOException e) {
	                System.out.println("I/O error: " + e);
	            }
	            // new thread for a client
	            new EchoThread(socket).start();
	            System.out.println("Created new Thread");
	        }
	    }
}
