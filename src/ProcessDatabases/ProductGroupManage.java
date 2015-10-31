package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProductGroupManage {
	
	private static String sql = new String();
	private static String name = new String();
	private static String id = new String();
	private static Statement st;
	private static ResultSet rs;

	public ProductGroupManage(Statement sts) {
		// TODO Auto-generated constructor stub
		st = sts;
	}
	
	public void showProdutInGroup(){
		ShowProductInGroup spg = new ShowProductInGroup(st);
	}
	
	
	public void deleteSupply(){
		DeleteSupply ds = new DeleteSupply(st);
	}
	
	public void addProductToGroup(){
		AddProductToGroup apg = new AddProductToGroup(st);
	}
	
	public void addNewGroup(){
		AddNewProductGroup a = new AddNewProductGroup(st);
	}
	
	public void ShowAllGroup(){
		sql = "Select Id_NhomHang, Ten_Nhomhang from nhomhang;";
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
