
import java.io.*;
import java.net.*;

public class SimpleClientSingle {

	public static void main(String args[]) {
		if (args.length < 2) return;
		
		String hosthome = args[0];
		int port = Integer.parseInt(args[1]);
		
		try (Socket socket = new Socket(hosthome, port)) {
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			
			Console console = System.console();
			String text;
			
			do {
				text = console.readLine("Enter text: ");
				
				writer.println(text);
				
				InputStream input = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				String time = reader.readLine();
				System.out.println(time);
				
			} while (!text.contains("bye"));
			
			socket.close();
			
		} catch (UnknownHostException e) {
			System.out.println("Server not found: " + e.getMessage());
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("I/O Error: " + e.getMessage());
		}
	}
}
