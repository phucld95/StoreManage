package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EvenManage {
	private static String sql = new String();
	private static String name = new String();
	private static String id = new String();
	private static Statement st;
	private static ResultSet rs;

	public EvenManage(Statement sts){
		st = sts;
	}
	
	public void deleteEven(){
		DeleteEven de = new DeleteEven(st);
	}
	
	public void addProduceInEven(){
		AddProduceInEven ape = new AddProduceInEven(st);
	}
	
	public void showOneEven(){
		ShowOneEven soe = new ShowOneEven(st);
	}
	
	public void ShowAllEven() throws SQLException{
		sql = "Select ID_KM, Ten_KM, TGDR, TGKT from khuyen_mai;";
		ResultSet rs = st.executeQuery(sql);
        TableDatabase stt = new TableDatabase(rs,"Các đợt khuyến mại.");    
	}
	
	public void createEven (){
		CreateEven ce = new CreateEven(st);
	}
}
