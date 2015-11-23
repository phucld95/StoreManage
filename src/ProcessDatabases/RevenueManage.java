package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RevenueManage {
	private static String sql = new String();
	private static String name = new String();
	private static String id = new String();
	private static Statement st;
	private static ResultSet rs;

	public RevenueManage(Statement sts){
		st = sts;
	}
	
	public void showRevenueOf1Account(){
		ShowRevenucOf1Account sr = new ShowRevenucOf1Account(st);
		sr.frmDoanhSCa.setVisible(true);
	}
	
	public void showRevenueOfStore(){
		ShowRevenueOfStore sr = new ShowRevenueOfStore(st);
		sr.frmDoanhSCa.setVisible(true);
	}
	
	public void showOneEven(){
		ShowOneEven soe = new ShowOneEven(st);
	}
	
	public void showBestAccount(){
		ShowBestAccount sb = new ShowBestAccount(st);
		sb.frmDoanhSCa.setVisible(true);
	}
	
	
	public void createEven (){
		CreateEven ce = new CreateEven(st);
	}
}
