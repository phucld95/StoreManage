package ProcessDatabases;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class testPrice {
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "123456";
	private static java.sql.Connection con;
	private static Statement st;
	
	public static void main(String[] args) {
		try {
        	con = DriverManager.getConnection(url, user, password);
            System.out.println("Connect Success!");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeUpdate ("Use test2;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PriceProduct pp = new PriceProduct(st);
			
	}
}
