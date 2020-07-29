package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	Thread t;
	Socket client;
	OutputStream out; 
	PrintWriter writer;
	
//Constructor-------------------------------------------------------------------
	public Client() {
		t = new Thread(this);
		t.start();
	}
	
//methods-----------------------------------------------------------------------
	private void initialize() {
		try {
			client = new Socket("192.168.178.80", 8143);
			out = client.getOutputStream();
			writer = new PrintWriter(out);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMSG() {
		writer.write("nachicht\n");
		writer.flush();
	}

	@Override
	public void run() {
		initialize();
	}

}
