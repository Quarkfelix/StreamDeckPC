package server;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;

import javax.imageio.ImageIO;

import infrastructure.Surface;

public class Server implements Runnable {
	// port: 8143
	Thread t;
	private ServerSocket server;
	private Socket client;
	private boolean audioswitch = true;
	private InputStream in;
	private BufferedReader reader;
	private boolean disconnected = true;
	private boolean soundpad = false;
	
//Constructor-------------------------------------------------------------------
	public Server() {
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		initializeServer();
		waitForClient();
		while(true) {
			while(disconnected) {
				waitForClient();
			}
			if(soundpad) {
				waitForSoundActions();
			} else {
				waitForActions();
			}	
		}		
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
			disconnected = false;
			Surface.outprint("Client Accepted");
			System.out.println("Client accepted");
			in = client.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
		} catch (IOException e) {
			System.out.println("verbindung verloren");
			e.printStackTrace();
		}
	}

	private void waitForActions() {
		try {
			String key;
			if ((key = reader.readLine()) != null) {
				System.out.println("key: " + key);
				switch (key) {
				case "disconnect": 
					System.out.println("switch");
					disconnected = true;
					break;
				case "Destiny2":
					Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Destiny 2\\destiny2.exe");
					break;
				case "Chrome":
					Process p2 = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
					break;
				case "Youtube":
					Process p3 = Runtime.getRuntime().exec("cmd /c start https://www.youtube.com/");
					break;
				case "Soundpad":
					soundpad = true;
					break;
				case "Screenshot":
					for (int i = 0; !new File("C://Users//marci//Pictures//extrascreenshots//screen" + i + ".png").exists(); i++) {
						Formatter format = new Formatter("C://Users//marci//Pictures//extrascreenshots//screen" + i + ".png");
						try {
							ImageIO.write(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())), "png", new File("C://Users//marci//Pictures//extrascreenshots//screen" + i + ".png"));
						} catch (HeadlessException | AWTException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					break;
				case "Mute System":
					Process p5 = Runtime.getRuntime().exec("nircmd.exe mutesysvolume 2");
					break;					
				case "Power off":
					Process p6 = Runtime.getRuntime().exec("nircmd.exe mutesysvolume 2");
					break;								
				case "Power Off":  
					Process p7 = Runtime.getRuntime().exec("nircmd.exe exitwin poweroff"); 
					break;
					case "switch audiooutput":
					if (audioswitch) {
						Process p8 = Runtime.getRuntime().exec("nircmd.exe setdefaultsounddevice Astro");
						audioswitch = false;
						System.out.println("1");
					} else {
						Process p8 = Runtime.getRuntime().exec("nircmd.exe setdefaultsounddevice ultrawide");
						audioswitch = true;
						System.out.println("2");
					}
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
	
	private void waitForSoundActions() {
		try {
			System.out.println("test");
			String key;
			if ((key = reader.readLine()) != null) {
				switch (key) {
				case "back":
					soundpad = false;
					break;
				case "sound1":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_1);
					break;
				case "sound2":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_2);
					break;
				case "sound3":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_3);
					break;
				case "sound4":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_4);
					break;
				case "sound5":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_5);
					break;
				case "sound6":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_6);
					break;
				case "sound7":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_7);
					break;
				case "sound8":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_8);
					break;
				case "sound9":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_9);
					break;
				case "sound10":
					press(KeyEvent.VK_CONTROL, KeyEvent.VK_0);
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
	
	private void press(int key1, int key2) {
		try {
			Robot r = new Robot();
			r.keyPress(key1);
			r.keyPress(key2);
			r.keyRelease(key2);
			r.keyRelease(key1);			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
