
import java.io.*;
import java.net.*;

public class ReverseServerSingle {

	public static void main(String args[]) {
		if (args.length < 1) return;
		
		int port = Integer.parseInt(args[0]);
		
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server is listening on port " + port);
			
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New Client connected");
				
				InputStream input = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output, true);
				
				String text;
				
				do {
					text = reader.readLine();
					String reverseText = new StringBuilder(text).reverse().toString();
					writer.println("Server: " + reverseText);
				} while (!text.contentEquals("bye"));
				
				socket.close();
			}
			
		} catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
