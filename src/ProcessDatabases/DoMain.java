package ProcessDatabases;

import java.sql.*;
import java.util.Formatter;
import java.util.Scanner;

public class DoMain {
	private static final String url = "jdbc:mysql://localhost";
	 
    private static final String user = "root";
 
    private static final String password = "123456";
	
	private static Statement st;
	
	static Scanner input = new Scanner(System.in);
	static String id = new String();
	static String name = new String();
	static String color = new String();
	static Formatter fmt = new Formatter();
	
	public static void main(String args[]) {
        try {
        	// Connect to database and creat statement.
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connect Success!");
            st = con.createStatement();
            
			ResultSet re;
			ProductManager pm = new ProductManager(st);
			//Layer2Interface lr2 = new Layer2Interface();
			//lr2.upInterface();
			int i = 0;
			String inp = new String();
			while (i != 0){
				System.out.println("Input chose: ");
				inp = input.nextLine();
				i = Integer.parseInt(inp);
				if(i == 1){
					ResultSet rs = pm.getAllProduct();
					while(rs.next()){
						id = rs.getString("MSMH");
						name = rs.getString("TenMH");
						color = rs.getString("Mau_Sac");

						System.out.printf("|%-20s|%-20s|%-20s|\n",id,name,color);
					}
				}
				if(i == 2){
					System.out.println("Input name product: ");
					inp = input.nextLine();
					i = pm.getIDProduct(inp);
					
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
