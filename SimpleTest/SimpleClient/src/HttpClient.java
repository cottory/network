
import java.io.*;
import java.net.*;

public class HttpClient {
	public static void main(String[] args) {
		if (args.length < 1) return;
		
		URL url;
		
		try {
			url = new URL(args[0]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		
		String hostname = url.getHost();
		int port = 80;
		
		try (Socket socket = new Socket(hostname, port)) {
			
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			
			writer.println("HEAD " + url.getPath() + " HTTP/1.1");
			writer.println("Host: " + hostname);
			writer.println("User-Agent: Simple Http Client");
			writer.println("Accept: text/html");
			writer.println("Accept-Language: en-Us");
			writer.println("Connection: close");
			writer.println();
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line;
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (UnknownHostException e) {
			System.out.println("Server not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O error: " + e.getMessage());
		}
	}
}
