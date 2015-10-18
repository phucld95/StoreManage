package ProcessDatabases;

import java.sql.ResultSet;
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
	
	public void deleteSupply(){
		DeleteSupply ds = new DeleteSupply(st);
	}
	
	public void showOneEven(){
		ShowOneEven soe = new ShowOneEven(st);
	}
	
	public void ShowAllEven(){
		sql = "Select ID_KM, Ten_KM, TGDR, TGKT from khuyen_mai;";
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
