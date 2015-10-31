package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccManage {
	private static String sql = new String();
	private static String name = new String();
	private static String id = new String();
	private static Statement st;
	private static ResultSet rs;

	public AccManage(Statement sts){
		st = sts;
	}
	
	public void fixAccount(){
		FixAccount fx = new FixAccount(st);
	}
	
	public void deleteAccount(){
		DeleteAccount ds = new DeleteAccount(st);
	}
	
	public void addNewAccount(){
		AddNewAccount2 ans = new AddNewAccount2(st);
	}
	
	public void ShowAllAccount() throws SQLException{
		sql = "Select ID_Account, username, password, tenNV, SDT, Dia_Chi from account;";
		ResultSet rs = st.executeQuery(sql);
        TableDatabase stt = new TableDatabase(rs,"Các tài khoản đang sử dụng.");    
	}
}
