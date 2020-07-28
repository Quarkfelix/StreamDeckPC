package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import infrastructure.Surface;

public class Server implements Runnable {
	// port: 8143
	Thread t;
	private ServerSocket server;
	private Socket client;

//Constructor-------------------------------------------------------------------
	public Server() {
		t = new Thread(this);
		t.start();
	}

//methods-----------------------------------------------------------------------
	private void initializeServer() {
		try {
			server = new ServerSocket(8143);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void waitForClient() {
		try {
			System.out.println("Waiting for client");
			client = server.accept();
			Surface.outprint("Client Accepted");
			System.out.println("Client accepted");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		initializeServer();
		waitForClient();
	}
}
