package ProcessDatabases;

import java.io.*;
import java.net.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.Vector;

public class ServerApp {
	public final static int DEFAULT_PORT = 5000;
	public static String clientSentence;
	public static String capitalizedSentence;
	
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "sieunhan";
	private static java.sql.Connection con;
	private static Statement st;
	private static ResultSet rs;
	private static String sql;
	
	
	public static void main(String args[]) {
	try {
    	con = DriverManager.getConnection(url, user, password);
        System.out.println("Connect Success!");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.executeUpdate ("Use managedatabase;");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	startServer();
}
	
	public static void startServer() {
		try(ServerSocket servSocket = new ServerSocket(DEFAULT_PORT)){
			while (true){
				Socket connSocket = servSocket.accept();
				System.out.println("Accepted client:" + connSocket.getInetAddress().getHostAddress());
				try(BufferedReader in = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(connSocket.getOutputStream()))){
					String message;
					while((message = in.readLine()) != null){
					System.out.println("Receive from client:" + message);
					message = getDataInMySQL(message);
					out.println(message);
					out.flush();
				}
				System.out.println("Client has stopped sending data!");
				}
				catch (IOException e){
					System.out.println(e.getMessage());
				}
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
		
		
//		// Tạo socket chờ tại cổng Deffault_port
//		try(ServerSocket servSocket = new ServerSocket(DEFAULT_PORT)){
//			while (true){
//				// Chờ yêu cầu từ client
//				Socket connectionSocket = servSocket.accept();
//				System.out.println("Accepted client:" + connectionSocket.getInetAddress().getHostAddress());
//				//Tạo inputStream kết nối tới socket	
//				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//				//Tạo outputStream kết nối tới socket
//				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//				//Đọc thông tin từ socket
//				clientSentence = inFromClient.readLine(); 
//				System.out.println(clientSentence);
//				capitalizedSentence = getDataInMySQL(clientSentence); 
//				System.out.println(capitalizedSentence);
//				outToClient.writeBytes(capitalizedSentence); 
//			}
//		}
//		catch (IOException e){
//			e.printStackTrace();
//		}
	}
	
	public static String getDataInMySQL(String sql){
		String str = new String();
		String temp = new String();
		
		try {
			// Lấy dữ liệu từ csdl.
			rs = st.executeQuery(sql);
			// Đọc metaData của resultSet.
			ResultSetMetaData rsMeta = rs.getMetaData();
			// Lấy số cột của bảng.
			int columpCount = rsMeta.getColumnCount();
			// Lặp để lấy dữ liệu sang chuỗi.
			while (rs.next()){
				for (int i = 1; i <= columpCount; i++) {
					//System.out.println(rsMeta.getColumnName(i));
					temp = rs.getString(rsMeta.getColumnName(i));
					str = str + temp + "|";
				}
				str = str + "||";
			}
			return str;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}



