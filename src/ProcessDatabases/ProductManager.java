package ProcessDatabases;

import java.sql.*;

public class ProductManager {
	private static Statement st;
	

	public ProductManager(Statement st) throws SQLException{
		this.st = st;
		st.executeUpdate("use lab;");
	}
	/*
	 *	Đưa ra tất cả các sản phẩm và thông tin liên quan. 
	 */
	
	public static ResultSet getAllProduct () throws SQLException{
		ResultSet rs = st.executeQuery("select * from B1_Mat_Hang;");	
		return rs;		
	}

	/*
	 * Tìm kiếm 1 sản phẩm.
	 */
	public static ResultSet getProduct (String NameProduct) throws SQLException{
		String sql = new String();
		sql = "select * from B1_Mat_Hang where TenMH = '" + NameProduct + "';"; 
		ResultSet rs = st.executeQuery(sql);
		return rs;
	}
	
	/*
	 * Lấy id của 1 mặt hàng.
	 * Nếu có > 2 sp cùng tên thì trả về -1
	 * Nếu không, trả về id của sản phẩm.
	 */
	
	public static int getIDProduct (String NameProduct) throws SQLException{
		ResultSet rs = getProduct (NameProduct);
		int i = 0;
		int MSMH = 0;
		if (rs == null){
			return -1;
		}
		while(rs.next()){
			MSMH = rs.getInt("MSMH");
			i++;
        }
		if (i>1){
			return -1;
		}
		return MSMH;		 
	}
	
	/*
	 * Sửa thông tin của 1 mặt hàng.
	 */
	public static int fixProduct (String mode, String newS){
		//UPDATE Supplier SET city = 'Milan'WHERE sid = 'S1';
		return 0;
	}
	
	/*
	 * Thêm 1 mặt hàng mới.
	 */
	
	public static int addProduct(String mode) throws SQLException{
		int i = 0;
		ResultSet rs = st.executeQuery("select MSMH from B1_Mat_Hang where MSMH >= all(select MSMH from B1_Mat_Hang);");
		i = rs.getInt("MSMH")+1;
		String sql = new String();
		sql = "insert into B1_Mat_Hang values ('" + i + "',";
		st.executeUpdate(sql);
		return 0;
	}
	
	/*
	 * Xoa 1 mặt hàng.
	 */
	
	public static int deleteProduct(int id) throws SQLException{
		String sql = "delete from B1_Mat_Hang where MSMH = '" + id +"';";
		st.executeUpdate(sql);
		return 0;
	}
	
	/*
	 *	Đưa ra tất cả các sản phẩm sắp hết hàng. 
	 */
	
	public static ResultSet needSupplementaryProduct () throws SQLException{
		ResultSet rs = st.executeQuery("select * from B1_Mat_Hang where SoLuong <= 10;");	
		return rs;		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}