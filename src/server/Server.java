package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
			waitForActions();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void waitForActions() {
		try {
			InputStream in;
			BufferedReader reader;
			in = client.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			while (true) {
				String key = reader.readLine();
				if (key != null) {
					switch (key) {
					case "Soundpad":
						Process p = Runtime.getRuntime().exec("notepad");
						break;

					default:
						break;
					}
				}
			}

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
