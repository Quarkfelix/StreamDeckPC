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
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
	private OutputStream out;
	private PrintWriter writer;
	private InputStream in;
	private BufferedReader reader;
	private boolean disconnected = true;
	private boolean soundpad = false;
	private boolean games = false;
	private boolean music = false;

//Constructor-------------------------------------------------------------------
	public Server() {
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		initializeServer();
		waitForClient();
		while (true) {
			if (disconnected) {
				waitForClient();
			} else {
				if (soundpad) {
					waitForSoundActions();
				} else if (games) {
					waitForGamesActions();
				} else if (music) {
					waitForMusicActions();
				} else {
					waitForActions();
				}
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
//			Surface.outprint("Client Accepted");
			System.out.println("Client accepted");
			out = client.getOutputStream();
			writer = new PrintWriter(out);
			in = client.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
		} catch (IOException e) {
			// System.out.println("verbindung verloren");
			e.printStackTrace();
		}
	}

	private void waitForActions() {
		try {
			String key = reader.readLine();
			// key is null if client disconnects
			if (key == null) {
				disconnected = true;
				return;
			}
			switch (key) {
			case "disconnect":
				System.out.println("switch");
				disconnected = true;
				break;
			case "Games":
				games = true;
				break;
			case "Soundpad":
				soundpad = true;
				break;
			case "Music":
				music = true;
				break;
			case "Chrome":
				Process p2 = Runtime.getRuntime().exec("cmd /c start chrome");
				break;
			case "Youtube":
				Process p3 = Runtime.getRuntime().exec("cmd /c start https://www.youtube.com/");
				break;
			case "Screenshot":
				int i = 0;
				while (new File("C://Users//marci//Pictures//extrascreenshots//screen" + i + ".png").exists()) {
					i++;
				}
				Formatter format = new Formatter("C://Users//marci//Pictures//extrascreenshots//screen" + i + ".png");
				try {
					ImageIO.write(
							new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())),
							"png", new File("C://Users//marci//Pictures//extrascreenshots//screen" + i + ".png"));
				} catch (HeadlessException | AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
					// System.out.println("1");
				} else {
					Process p8 = Runtime.getRuntime().exec("nircmd.exe setdefaultsounddevice ultrawide");
					audioswitch = true;
					// System.out.println("2");
				}
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("wrong command");
		}
	}

	private void waitForSoundActions() {
		try {
			String key = reader.readLine();
			// key is null if client disconnects
			if (key == null) {
				soundpad = false;
				disconnected = true;
				return;
			}
			switch (key) {
			case "back":
				soundpad = false;
				break;
			case "stopsound":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_P);
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
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_5);
				break;
			case "sound5":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_6);
				break;
			case "sound6":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_7);
				break;
			case "sound7":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_8);
				break;
			case "sound8":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_9);
				break;
			case "sound9":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_0);
				break;
			case "sound10":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_2);
				break;
			case "sound11":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_3);
				break;
			case "sound12":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_4);
				break;
			case "sound13":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_7);
				break;
			case "sound14":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_9);
				break;
			case "sound15":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_0);
				break;
			case "sound16":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_1);
				break;
			case "sound17":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_8);
				break;
			case "sound18":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_6);
				break;
			case "sound19":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_5);
				break;
			case "sound20":
				press(KeyEvent.VK_CONTROL, KeyEvent.VK_4);
				break;
			
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void waitForGamesActions() {
		try {
			String key = reader.readLine();
			// key is null if client disconnects
			if (key == null) {
				games = false;
				disconnected = true;
				return;
			}
			switch (key) {
			case "back":
				games = false;
				break;
			case "Destiny2":
				Process p = Runtime.getRuntime().exec("cmd /c start steam://rungameid/1085660");
				break;
			case "Hyperscape":
				Process p2 = Runtime.getRuntime().exec("cmd /c start uplay://launch/11957/0");
				break;
			case "MortalKombat":
				Process p3 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/307780");
				break;
			case "PummelParty":
				Process p4 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/880940");
				break;
			case "MonsterHunterWorld":
				Process p5 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/582010");
				break;
			case "CSGO":
				Process p6 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/730");
				break;
			case "dishonored2":
				Process p7 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/403640");
				break;
			case "opusmagnum":
				Process p8 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/558990");
				break;
			case "satisfactory":
				Process p9 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/526870");
				break;
			case "titans":
				Process p10 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/386070");
				break;
			case "squadrons":
				Process p11 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/1222730");
				break;
			case "warframe":
				Process p12 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/230410");
				break;
			case "raft":
				Process p13 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/648800");
				break;
			case "forts":
				Process p14 = Runtime.getRuntime().exec("cmd /c start steam://rungameid/410900");
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void waitForMusicActions() {
		try {
			String key = reader.readLine();
			// key is null if client disconnects
			if (key == null) {
				music = false;
				disconnected = true;
				return;
			}
			Process p;
			switch (key) {
			case "back":
				music = false;
				break;
			case "startYTMusic":
				p = Runtime.getRuntime().exec("cmd /c start https://music.youtube.com/");
				break;
			case "pausePlay":
				p = Runtime.getRuntime().exec("cmd /c start https://music.youtube.com/");
				break;
			case "volume":
				while (true) {
					key = reader.readLine();
					try {
						double volume = Integer.valueOf(key);
						double scaledvolume = volume * 655.35;
						p = Runtime.getRuntime().exec("nircmd.exe setsysvolume " + (int) scaledvolume);
					} catch (NumberFormatException e) {
						break;
					}
				}
			default:
				break;
			}
		} catch (IOException e) {
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
	private void press(int key0, int key1, int key2) {
		try {
			Robot r = new Robot();
			r.keyPress(key0);
			r.keyPress(key1);
			r.keyPress(key2);
			r.keyRelease(key2);
			r.keyRelease(key1);
			r.keyRelease(key0);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
