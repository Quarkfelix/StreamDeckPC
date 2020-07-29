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
	private boolean audioswitch = true;
	private InputStream in;
	private BufferedReader reader;
	
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
			in = client.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void waitForActions() {
		try {
			String key;
			if ((key = reader.readLine()) != null) {
				System.out.println("key: " + key);
				switch (key) {
				case "Soundpad":
					Process p = Runtime.getRuntime().exec("E:\\Steam Games\\steamapps\\common\\Soundpad\\Soundpad.exe");
					System.out.println("2");
					break;
				case "Destiny2":
					Process p2 = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Destiny 2\\destiny2.exe");
					System.out.println("2");
					break;
				case "switch audiooutput":
					if (audioswitch) {
						Process p3 = Runtime.getRuntime().exec("nircmd.exe setdefaultsounddevice Astro");
						audioswitch = false;
						System.out.println("1");
					} else {
						Process p4 = Runtime.getRuntime().exec("nircmd.exe setdefaultsounddevice ultrawide");
						audioswitch = true;
						System.out.println("2");
					}
				case "nachicht":  
					System.out.println("nachicht");
					break;
				default:
					break;
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
		while(true) {
			waitForActions();
		}
	}
}
