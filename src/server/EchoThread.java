package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoThread extends Thread {
    protected Socket socket;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
    	System.out.println("Thread started");
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
            	System.out.println("Incoming data:" +line);

                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                	String data[] = line.split(";");
                	String sourceIP = data[0];
                	String destinationIP = data[1];
                	String port = data[3];
                	
                	DataOutputStream desout = new DataOutputStream(Server.clients.get(line).getOutputStream());
                    desout.writeBytes(sourceIP+";"+port);
                    desout.flush();
                    
                    out.writeBytes(destinationIP+";"+port);
                    out.flush();
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}