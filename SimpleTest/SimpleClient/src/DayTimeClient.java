
import java.io.*;
import java.net.*;

public class DayTimeClient {

	public static void main(String[] args) {
		String hostname = "time.nist.gov";
		int port = 13;
		
		try (Socket socket = new Socket(hostname, port)) {
			
			InputStream input = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(input);
			
			int character;
			StringBuilder data = new StringBuilder();
			
			while ((character = reader.read()) != -1) {
				data.append((char) character);
			}
			
			System.out.println(data);
			
		} catch (UnknownHostException e) {
			System.out.println("Server not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
	}

}
