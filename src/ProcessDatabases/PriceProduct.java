package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PriceProduct {
	private static Statement st;

	public PriceProduct(Statement sts) {
		st = sts;
	}

	public int getPriceProduct(int code, int so_luong, String time) throws SQLException{
		int price =0,dot,sum=0;
		ResultSet result;
		String SQL = new String();
		try{
			SQL = String.format("select * from khuyen_mai where TGDR <= '%s' and TGKT >= '%s';", time, time);
			result = st.executeQuery(SQL);
			result.next();
			dot = result.getInt("Id_KM");
			result.close();
			// láº¥y giÃ¡ máº·t hÃ ng náº¿u náº±m trong máº·t hÃ ng Ä‘Æ°á»£c khuyáº¿n mÃ£i
			SQL = String.format(
					"select Gia_KM from mat_hang natural join duoc_khuyen_mai where ID_Mat_Hang = %d and Id_KM = %d;",
					code,dot);
			result = st.executeQuery(SQL);
			result.next();
			price = result.getInt("Gia_KM");
			sum = sum + price * so_luong;
			SQL = String.format("select Soluong from mat_hang where ID_MatHang=%d", code);
			result = st.executeQuery(SQL);
			result.next();

			SQL = String.format("update mat_hang set Soluong=%d-%d where ID_MatHang=%d", result.getInt("Soluong"),
					so_luong, code);
			st.executeUpdate(SQL);
			return sum;
		} catch (SQLException ex) {
			SQL = String.format("select Gia_Ban from mat_hang where ID_MatHang=%d;", code);
			result = st.executeQuery(SQL);
			result.next();
			price = result.getInt("Gia_Ban");
			sum = sum + price * so_luong;
			// update láº¡i sá»‘ lÆ°á»£ng cÃ²n láº¡i !
			SQL = String.format("select Soluong from mat_hang where ID_MatHang=%d", code);
			result = st.executeQuery(SQL);
			result.next();
			SQL = String.format("update mat_hang set Soluong=%d-%d where ID_MatHang=%d", result.getInt("Soluong"),
					so_luong, code);
			st.executeUpdate(SQL);
			return sum;
		}
	}
}
