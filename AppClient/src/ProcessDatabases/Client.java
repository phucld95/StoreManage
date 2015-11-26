package ProcessDatabases;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Client {
	public static BufferedReader in;
	public static Socket clientSocket;
	private static PrintWriter out;
	
	public static int getData (String sql, int columnCount, String[][] out) throws IOException{
		String dataS = executeServer(sql);
		//System.out.println(dataS);
		String temp;
		int j = 0;
		int start;
		int end;
		int count = 0;
		while(j < dataS.length()-3){
			for (int i = 0; i < columnCount; i++) {
				start = j;
				while(dataS.charAt(j++) != '|');
				end = j-1;
				temp = dataS.substring(start, end);		
				//String unicode = new String(asciiBytes, "windows-1252");
				System.out.print(temp + "*");		
				out[count][i] = temp;
				if(dataS.charAt(end+2) == '|' && dataS.charAt(end+1) == '|') break;
			}
			count ++ ;
			j = j + 2;
			System.out.println();	
		}
		return count;
	}
	
	private static void createConnect() throws UnknownHostException, IOException{	
		clientSocket = new Socket("localhost", 5000); 
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));							
	}
	
	public static String executeServer(String sql) throws IOException{
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
	
	public static void main(String[] args) throws UnknownHostException, IOException, SQLException{
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
		String message;
		Client cl = new Client();
		//while(true){
			message = "select * from mat_hang where ID_MatHang < 5;";
			//String ab = user.readLine();
			//message = user.readLine();
			String[][] a = new String[100][4];
			int count = getData(message, 4, a);
			TableDatabase(a, count);
			//System.out.println(cl.getData(message));
		//}
	}		
	
	public static void TableDatabase(String[][] a, int count) throws SQLException{
		JFrame frame = new JFrame();
		frame.setTitle("T\u00EDnh ti\u1EC1n");
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		Object[] colump = {"ID mặt hàng","Tên sản phẩm","Giá sản phẩm", "Số còn lại"};
		Object[] row = new Object[4];
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable();
		table.setModel(model);
		table.setBounds(45, 290, 1288, 301);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(41, 282, 1239, 292);	
		frame.getContentPane().add(jsp);
		model.setColumnIdentifiers(colump);
		for (int i = 0; i < count; i++) {
			row[0] = a[i][0];
			row[1] = a[i][1];
			row[2] = a[i][2];
			row[3] = a[i][3];
			model.addRow(row);
		}
		
		
	    }
}