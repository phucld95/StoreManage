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
		SearchAndFixSupply fx = new SearchAndFixSupply(st);
		fx.frmTmKimChnh.setVisible(true);
	}
	
	
	public void addNewSupply(){
		AddNewSupply2 ans = new AddNewSupply2(st);
	}
}
