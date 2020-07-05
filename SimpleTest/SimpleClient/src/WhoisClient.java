
import java.io.*;
import java.net.*;

public class WhoisClient {

	public static void main(String args[]) {
		if (args.length < 1) return;
		
		String domainName = args[0];
		
		String hostname = "whois.internic.net";
		int port = 43;
		
		try (Socket socket = new Socket(hostname, port)) {
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			writer.println(domainName);
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (UnknownHostException e) {
			System.out.println("Server not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
	}
}
