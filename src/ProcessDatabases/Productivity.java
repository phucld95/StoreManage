package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Productivity {
	static Statement st;
	String sql= new String();
	int sum,i;
	ResultSet rs;
	Scanner Input = new Scanner(System.in);
	public Productivity(Statement sts){
		st=sts;
	}
	// tinh tien trung binh trong dot khuyen mai
	public int AvgEvent(int id)throws SQLException{
		String TGDR = new String();
		String TGKT = new String();
		sql=String.format("select TGDR,TGKT from khuyen_mai where Id_KM=%d",id);
		rs=st.executeQuery(sql);
		rs.next();
		TGDR=rs.getString("TGDR").concat("000000");
		TGKT=rs.getString("TGKT").concat("235959");
		sum=i=0;
		//time[0].concat("000000");
		//time[1].concat("235959");
		sql=String.format("select Tong_Tien from hoa_don where %s <= Thoi_Gian and %s >=TGKT", TGDR,TGKT);
				//SQL = String.format("select * from khuyen_mai where TGDR <= '%s' and TGKT >= '%s';", time.Date(), time.Date());
		rs=st.executeQuery(sql);
		while(!rs.next()){
			sum=sum+rs.getInt("Tong_Tien");
			i++;
		}
		return sum=sum/i;
	}
	// ting tien trung binh cua 1 nhan vien ban duoc trong 1 khoang thoi nhan nhat dinh !
	public int AvgMember(String timeStart , String timeEnd,int id_Account)throws SQLException{
		sum=i=0;
		sql=String.format("select Tong_Tien from hoa_don where ID_ThuNgan=%d and Thoi_Gian >=%s and Thoi_Gian <=%s", id_Account,timeStart.concat("000000"),timeEnd.concat("235959"));
		rs=st.executeQuery(sql);
		while(!rs.next()){
			sum=sum+rs.getInt("Tong_Tien");
			i++;
		}
		return sum/i;
	}
}
