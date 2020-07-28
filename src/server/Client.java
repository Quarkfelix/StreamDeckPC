package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	Thread t;
	Socket client;

//Constructor-------------------------------------------------------------------
	public Client() {
		t = new Thread(this);
		t.start();
	}
	
//methods-----------------------------------------------------------------------
	private void initialize() {
		try {
			client = new Socket("192.168.178.80", 8143);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		initialize();

	}

}
