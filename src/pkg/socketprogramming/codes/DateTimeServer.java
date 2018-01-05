package pkg.socketprogramming.codes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeServer {

	static final int SERVER_PORT = 6666;
	private static ServerSocket serverSocket = null;
	private static Object lock = new Object();
	private static DateTimeServer dts;

	private DateTimeServer() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DateTimeServer init() {
		if (null == serverSocket) {
			synchronized (lock) {
				dts = new DateTimeServer();
			}
		}
		return dts;
	}

	private static void acceptClientConnections() throws IOException {
		while (true) {
			System.out.println("Waiting for new Connection....");
			Socket socket = serverSocket.accept();
			System.out.println("A new client is connected: " + socket);
			Thread t = new ClientHandler(socket);
			t.start();
		}
	}

	public static void main(String[] args) {
		DateTimeServer.init();
		try {
			acceptClientConnections();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ClientHandler extends Thread {

	DateFormat forDate = new SimpleDateFormat("yyyy/MM/dd");
	DateFormat forTime = new SimpleDateFormat("hh:mm:ss");
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket soc;

	public ClientHandler(Socket socket) throws IOException {
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		soc = socket;
	}

	@Override
	public void run() {
		String received = "";
		String toReturn = "";

		while (true) {
			try {
				dos.writeUTF("What do you want? [Date | Time]..\n Type \"EXIT\" to end the connection");
				received = dis.readUTF();
				if (received.equalsIgnoreCase("EXIT")) {
					System.out.println("Client " + this.soc + " sends exit...");
					System.out.println("Closing this connection...");
					this.soc.close();
					System.out.println("Connection closed...");
					break;
				}

				Date date = new Date();
				switch (received.toLowerCase()) {
				case "date":
					toReturn = forDate.format(date);
					dos.writeUTF(toReturn);
					break;
				case "time":
					toReturn = forTime.format(date);
					dos.writeUTF(toReturn);
					break;
				default:
					dos.writeUTF("Invalid Input...");
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		try {
			dis.close();
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
