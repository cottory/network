
import java.io.*;
import java.net.*;

public class ReverseServerMulti {

	public static void main(String args[]) {
		if (args.length < 1) return;
		
		int port = Integer.parseInt(args[0]);
		
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			
			System.out.println("Server is listening ");
			
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New Client connected");
				
				new ServerThread(socket).start();
			}
			
		} catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
