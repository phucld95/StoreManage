package ProcessDatabases;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowAllProduct {

	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "123456";
	private static String sql = new String();
	
	public static void main(String [] args){
		sql = "Select MH_ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, nhomhang nh where mh.MH_ID_NCC = ncc.ID_NCC and mh.MH_ID_NhomHang = nh.ID_NhomHang;";
		inputData(sql);
      
  }
	
	public static void inputData(String sql){
		try {
        	// Connect to database and creat statement.
            java.sql.Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connect Success!");
            
            Statement st = con.createStatement();
            st.executeUpdate ("Use lab;");
            
            ResultSet rs = st.executeQuery(sql);
            SimpleTableTest stt = new SimpleTableTest(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}