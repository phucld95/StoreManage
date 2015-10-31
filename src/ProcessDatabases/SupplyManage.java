package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplyManage {
	private static String sql = new String();
	private static String name = new String();
	private static String id = new String();
	private static Statement st;
	private static ResultSet rs;

	public SupplyManage(Statement sts){
		st = sts;
	}
	
	public void fixInfomationSupply(){
		FixInfomationSupply2 fx = new FixInfomationSupply2(st);
	}
	
	public void deleteSupply(){
		DeleteSupply ds = new DeleteSupply(st);
	}
	
	public void addNewSupply(){
		AddNewSupply2 ans = new AddNewSupply2(st);
	}
	
	public void ShowAllSupply() throws SQLException{
		sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc;";
		ResultSet rs = st.executeQuery(sql);
        TableDatabase stt = new TableDatabase(rs,"Các công ty đang cung cấp sản phẩm cho cửa hàng.");     
	}
}
