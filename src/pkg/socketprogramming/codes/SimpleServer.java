package pkg.socketprogramming.codes;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author neeraj
 *
 *         A Simple client- server chat example
 */

/*
 * Create a server. Attach it to a certain port and bind it to a socket for
 * incoming requests.
 */
public class SimpleServer {
	static int portNumber = 8888;
	static ServerSocket serverSocket = null;

	public static void main(String[] args) throws IOException {
		serverSocket = new ServerSocket(portNumber);

		System.out.println("Printing details for server socket...");
		System.out.println("Local Port:" + serverSocket.getLocalPort());
		System.out.println("Remote IP address: "
				+ serverSocket.getInetAddress());
		System.out.println("Local socket addres: "
				+ serverSocket.getLocalSocketAddress());

		System.out
				.println("***********************************************************");
		System.out.println("Entering accept connection......");

		acceptConnections();
	}

	private static void acceptConnections() throws IOException {

		Socket soc = serverSocket.accept();

		System.out.println("Local Port:" + soc.getLocalPort());
		System.out.println("Port: " + soc.getPort());
		System.out.println("Remote IP address: " + soc.getInetAddress());
		System.out.println("Local socket addres: "
				+ soc.getLocalSocketAddress());
		System.out.println("Remote socket addres: "
				+ soc.getRemoteSocketAddress());

		DataInputStream dis = new DataInputStream(soc.getInputStream());
		String msg = dis.readUTF();
		System.out.println("Message: " + msg);
		soc.close();

	}
}
