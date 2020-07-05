import java.io.*;
import java.net.*;

public class TimeClient {
	
	public static void main(String args[]) {
		if (args.length < 2) {
			System.out.println("##### Need More Arguments #####");
			return;
		}
		
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		try (Socket socket = new Socket(hostname, port)) {
//			OutputStream out = socket.getOutputStream();
//			String result = "This is SimpleClient Code Test";
//			out.write(result.getBytes());
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String time = reader.readLine();
			System.out.println(time);

		} catch (UnknownHostException e) {
			System.out.println("Server not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O error: " + e.getMessage());
		}
	}
}
