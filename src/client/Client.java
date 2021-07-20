package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	public static void main(String[]args) {
		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 12345);
			OutputStream output = socket.getOutputStream();
			
			output.write("TestingSocket".getBytes());
			output.write("QUIT".getBytes());
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	

}
