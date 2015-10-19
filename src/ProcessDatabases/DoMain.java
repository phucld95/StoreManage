package ProcessDatabases;

import java.sql.*;
import java.util.Formatter;
import java.util.Scanner;




public class DoMain {
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "123456";
	private static java.sql.Connection con;
	private static Statement st;
	
	
	public static void main(String args[]) {
        try {
        	con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connect Success!");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeUpdate ("Use test2;");
            Layer1Interface ly1 = new Layer1Interface(st);
            
            ly1.frame.setVisible(true);
            while(ly1.check() == 0);
            ly1.frame.setVisible(false);
            if(ly1.check() == 2){
            	Layer2Interface window = new Layer2Interface(st);
    			window.frmStoreManager.setVisible(true);
           	}
			
        }
		catch(Exception e){
			e.printStackTrace();
		}
	}
}