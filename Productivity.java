package ProcessDatabases;
import java.sql.*;
import java.lang.String;

public class moneyEvent {
	
	Statement myStmt;
	double sum;
	int i;
	ResultSet myRs;
	
	public void Productivity (Statement Stmt){
		
		myStmt = Stmt;
		
	}	
	
	public double averageMoney(int id) throws SQLException {
		
		String TGDR = new String();
		String TGKT = new String();
		
		String time[] =  new String[30];
		String sql = new String();
		
		sql = String.format(" SELECT TGDR,TGKT FROM khuyen_mai where Id_KM = %d ",id);
		myRs = myStmt.executeQuery(sql);
		
		myRs.next();		
		
		time = myRs.getString("TGDR").split("[-]");
		
		for (String s : time ){
			TGDR = TGDR .concat(s);
		}
		
		time = myRs.getString("TGKT").split("[-])");
		
		for (String s : time ){
			TGKT = TGKT .concat(s);
		}
		
		sql = String.format("SELECT Tong_Tien FROM hoa_don where %s <= Thoi_Gian and %s >= Thoi_Gian ",TGDR,TGKT);
		myRs = myStmt.executeQuery(sql);
		
		
			
		while(myRs.next()){
			sum += myRs.getDouble("Tong_Tien");
			i++;
		}
		return (sum/i);
		
	}
		
	public double incomeAccountant(String begin_time,String end_time,int id_Account) throws SQLException{
		
		String sql = String.format ("SELECT Tong_Tien FROM hoa_don WHERE ID_ThuNgan = %d and Thoi_Gian >= '%s',Thoi_Gian <='%s'",
				id_Account,begin_time,end_time);
		
		myRs = myStmt.executeQuery(sql);
		
		
		while(myRs.next()){
			
			sum += myRs.getDouble("Tong_Tien");
			
			i++;		
		}
			
		return (sum/i);
		
		
		
		
		
	}
	}
