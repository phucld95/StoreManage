package tinh_tien;

public class ConnectToDTB{
	public void connectDTB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Load JDBC Driver Complete ...\n");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}