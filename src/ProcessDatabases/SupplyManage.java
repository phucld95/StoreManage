package ProcessDatabases;

import java.sql.ResultSet;
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
		FixInfomationSupply fx = new FixInfomationSupply(st);
	}
	
	public void deleteSupply(){
		DeleteSupply ds = new DeleteSupply(st);
	}
	
	public void addNewSupply(){
		AddNewSupply ans = new AddNewSupply(st);
	}
	
	public void ShowAllSupply(){
		sql = "Select Id_NCC, Ten_NCC, TTLH from ncc;";
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
