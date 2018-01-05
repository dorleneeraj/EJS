package pkg.socketprogramming.codes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		int serverPortNumber = 8888;
		Socket soc = new Socket("localhost", serverPortNumber);
		System.out.println("Local Port:" + soc.getLocalPort());
		System.out.println("Port: " + soc.getPort());
		System.out.println("Remote IP address: " + soc.getInetAddress());
		System.out.println("Local socket addres: "
				+ soc.getLocalSocketAddress());
		System.out.println("Remote socket addres: "
				+ soc.getRemoteSocketAddress());
		DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
		dos.writeUTF("Hey, How you doin!?");
		dos.flush();
		dos.close();
		soc.close();
	}
}
