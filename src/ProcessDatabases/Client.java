package ProcessDatabases;

import java.io.*;
import java.net.*;

public class Client {
	public static BufferedReader in;
	public static Socket clientSocket;
	private static PrintWriter out;
	
	private static void createConnect() throws UnknownHostException, IOException{	
		clientSocket = new Socket("localhost", 5000); 
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));							
	}
	
	public String getData(String sql) throws IOException{
		String reply;
		System.out.println("Send to server: " + sql);
		out.println(sql);
		out.flush();
		reply = in.readLine();
		System.out.println("Reply from Server:" + reply);		
		return reply;
	}
	
	public void stopConnect() throws IOException{
		clientSocket.close();
	}
	
	public Client() throws UnknownHostException, IOException{
		createConnect();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
		String message;
		Client cl = new Client();
		while(true){
			message = user.readLine();
			System.out.println(cl.getData(message));
		}
	}		
}