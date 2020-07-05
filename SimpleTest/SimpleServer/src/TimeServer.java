import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer {

	public static void main(String[] args) {
	
		if (args.length < 1) {
			System.out.println("##### Need More Arguments #####");
			return;
		}
		
		//portNumber setting
		int port = Integer.parseInt(args[0]);
		
		//Create a Server Socket
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			
			System.out.println("Server is listening on port " + port);
			
			while (true) {
				
				//1. Listen for a connection
				Socket socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + " ] client connected");
								
				//2. Read data from the Client
				//2-1. Socket 객체가 반환 되면 (연결이 되면) InputStream을 사용하여 클라이언트에서 보낸 데이터를 읽을 수 있습니다.
//				InputStream input = socket.getInputStream();
				
				//2-2. InputStream은 데이터를 byte 배열로 읽기 때문에, 상위 레벨의 데이터를 읽으려면 InputStreamReader로 읽어줍니다.
				//2-3. 또한, BufferedReader에 InputStream을 래핑하여 데이터를 String으로 읽어옵니다.
//				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//				System.out.println("##### msg : " + reader.readLine());
				
				//3. Send Data to the Client
				//3-1. Socket과 연결된 클라이언트에게 OutputStream을 사용하여 데이터를 보냅니다.
				OutputStream output = socket.getOutputStream();
				
				//3-2. PrintWriter를 사용하여 텍스트 형식으로 데이터를 보낼 수 있습니다
				PrintWriter writer = new PrintWriter(output, true);
				writer.println(new Date().toString());				
			}
		} catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
			e.printStackTrace();
		}		
	}
}

