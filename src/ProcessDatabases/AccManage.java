package ProcessDatabases;

import java.sql.ResultSet;
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
		AddNewAccount ans = new AddNewAccount(st);
	}
	
	public void ShowAllAccount(){
		sql = "Select STT, username, password from account;";
		inputData(sql);     
	}
	
	
	public static void inputData(String sql){
		try {
            ResultSet rs = st.executeQuery(sql);
            TableDatabase stt = new TableDatabase(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
