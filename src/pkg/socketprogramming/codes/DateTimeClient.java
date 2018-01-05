package pkg.socketprogramming.codes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class DateTimeClient {
	private static final int SERVER_PORT = 6666;

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);
		InetAddress ip = InetAddress.getByName("localhost");
		Socket s = new Socket(ip, SERVER_PORT);
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		while (true) {
			System.out.println(dis.readUTF());
			String strToSend = scn.nextLine();
			dos.writeUTF(strToSend);
			if (strToSend.equalsIgnoreCase("Exit")) {
				System.out.println("Closing the connection: " + s);
				s.close();
				System.out.println("Connection closed...");
				break;
			}
		}

		scn.close();
		dis.close();
		dos.close();
	}
}
