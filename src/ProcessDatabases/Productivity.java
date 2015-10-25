package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Productivity {
	static Statement st;
	String sql = new String();
	int sum, i;
	ResultSet rs;
	Scanner Input = new Scanner(System.in);

	public Productivity(Statement sts) {
		st = sts;
	}

	// tinh tien trung binh trong dot khuyen mai
	public double AvgEvent(int id) throws SQLException {
		String TGDR = new String();
		String TGKT = new String();
		String result[] = new String[100];
		sum = 0;
		i = 0;
		sql = String.format("select TGDR,TGKT from khuyen_mai where Id_KM=%d", id);
		rs = st.executeQuery(sql);
		rs.next();
		result = rs.getString("TGDR").split("[-]");
		for (String s : result) {
			TGDR = TGDR.concat(s);
		}
		result = rs.getString("TGKT").split("[-]");
		for (String r : result) {
			TGKT = TGKT.concat(r);
		}
		TGDR = TGDR.concat("000000");
		TGKT = TGKT.concat("235959");
		// System.out.println(TGDR+" "+TGKT);
		// tinh so tien trung binh trong dot khuyen mai
		sql = String.format("select Tong_Tien from hoa_don where %s <= Thoi_Gian and %s >=Thoi_Gian", TGDR, TGKT);
		rs = st.executeQuery(sql);
		while (rs.next()) {
			sum = sum + rs.getInt("Tong_Tien");
			i++;
		}
		try {
			return (sum / i);
		} catch (Exception ex) {
			return 0;
		}
	}

	// ting tien trung binh cua 1 nhan vien ban duoc trong 1 khoang thoi gian
	// nhat dinh !
	public double AvgMember(String timeStart, String timeEnd, int id_Account) throws SQLException {
		sum = i = 0;
		timeStart = timeStart.concat("000000");
		timeEnd = timeEnd.concat("235959");
		// System.out.println(timeStart+" "+timeEnd);
		sql = String.format(
				"select Tong_Tien from hoa_don where ID_ThuNgan=%d and Thoi_Gian >='%s' and Thoi_Gian <='%s'",
				id_Account, timeStart, timeEnd);
		rs = st.executeQuery(sql);
		while (rs.next()) {
			sum = sum + rs.getInt("Tong_Tien");
			i++;
		}
		try {
			return sum / i;
		} catch (Exception ex) {
			return 0;
		}
	}
}
