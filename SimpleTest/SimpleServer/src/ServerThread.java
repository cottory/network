
import java.io.*;
import java.net.*;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket socket) {
		super();
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			
			String threadName = currentThread().getName();
			String text;
			
			do {
				text = reader.readLine();
				String reverseText = new StringBuilder(text).reverse().toString();
				writer.println("Server[ " + threadName + "]: " + reverseText);
			} while (!text.contentEquals("bye"));
			
			this.socket.close();
			
		} catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
