import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class RunServer {
	
	static ServerSocket socket = null;
	static final int PORT = 8080;
	static boolean running = true;
	
	/**
	 * Main entry point for running the server 
	 * @param args
	 */
	public static void main(String[] args) {
		Socket s;
		try {
			socket = new ServerSocket(PORT);
			while (running) {
				s = socket.accept();		//Open the socket
				QueryString qs = processInput(s.getInputStream());	//Read the request
				if (qs == null) continue;	//If the query string is not well-formed, ignore this request
				StateDecider sd = new StateDecider();
				String answer = sd.decide(qs);	//check whether the given point is in any state
				System.out.println("The point is in: " + answer);
			}
			
		} catch(IOException e) {
			e.printStackTrace();
			stopServer();
		}
	}
	
	/**
	 * Kill the server gracefully
	 */
	private static void stopServer() {
		try {
			running = false;
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parse the input HTTP request
	 * @param in
	 * @return
	 */
	private static QueryString processInput(InputStream in) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String firstLine = "", data = "";
		try {
			firstLine = br.readLine();	//Read the first line
			if (firstLine == null) return null;
			String[] list = firstLine.split(" ");
			if (!list[0].equals("POST")) return null;	//If not POST, forget it
			if (list[1].equals("/shutdown")) {			//A little graceful shutdown signal, optional
				running = false;
				return null;
			}
			String line;
			while ((line = br.readLine()) != null && line.trim().length() != 0) {}	//Pass all headers
			while (br.ready()) data += (char) br.read();		//Read the query string char by char
//			System.out.println("Query string: " + data + '\n');
			QueryString qs = null;
			try {
				qs = new QueryString(data);	//Parse the query string and extract longitude / latitude
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			}
			System.out.println('\n' + "Longitude: " + qs.getLong() + " Latitude: " + qs.getLat());
			return qs;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
