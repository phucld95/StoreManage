package ProcessDatabases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class searchSuggest {
	public static String sql = new String();
	private static Scanner nhap;
	public static int len, i = 0;
	public static String temp;
	// private static String //subString;

	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "123456";
	
	public static void main(String[] args) {
		Data searchData = new Data();
		// ConnectToDTB conn = new ConnectToDTB();
		String c = new String();
		// subString = new String();
		nhap = new Scanner(System.in);
		// searchData.InputName();
		connectDTB();
		Connection connect;
		ResultSet rs;
		try {
			connect = DriverManager.getConnection(url, user, "123456");
			Statement st = connect.createStatement();
			st.executeUpdate ("Use test2;");
			do {
				searchData.InputName();
				sql = String.format("select ID_MatHang,Ten_MH,Soluong,Gia_Ban from mat_hang where Ten_MH = '%s';",
						searchData.name);
				try {
					rs = st.executeQuery(sql);
					rs.next();
					searchData.id = rs.getInt("ID_MatHang");
					searchData.name = rs.getString("Ten_MH");
					searchData.so_luong = rs.getInt("Soluong");
					searchData.Gia_Ban = rs.getInt("Gia_Ban");
					sql = String.format("||Id_MatHang : %d ||\n||Ten_MH : %s ||\n||Soluong: %d ||\n||Gia_Ban : %d ||",
							searchData.id, searchData.name, searchData.so_luong, searchData.Gia_Ban);
					System.out.println(sql);
				} catch (SQLException e) {
					sql = String.format("select ID_MatHang,Ten_MH,Soluong,Gia_Ban from mat_hang;");
					rs = st.executeQuery(sql);
					len = searchData.name.length();
					while (rs.next()) {
						temp = rs.getString("Ten_MH").substring(0, (len));
						if (searchData.name.compareToIgnoreCase(temp) == 0) {
							searchData.id = rs.getInt("ID_MatHang");
							temp = rs.getString("Ten_MH");
							searchData.so_luong = rs.getInt("Soluong");
							searchData.Gia_Ban = rs.getInt("Gia_Ban");
							sql = String.format(
									"||Id_MatHang : %d ||\n||Ten_MH : %s ||\n||Soluong: %d ||\n||Gia_Ban : %d ||",
									searchData.id, temp, searchData.so_luong, searchData.Gia_Ban);
							System.out.println(sql + "\n\n\n");
						}
					}
				}
				System.out.print("tiep tu nhap mat hang ? (y/n)");
				c = nhap.nextLine();
			} while (c.equals("y"));
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public static void connectDTB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Load JDBC Driver Complete ...\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}