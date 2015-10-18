package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class PriceProduct {
	Statement st;

	public PriceProduct(Statement st) {
		this.st = st;
	}

	public int getPriceProduct(int code, int so_luong, String time) throws SQLException {
		int price = 0, dot, sum = 0;
		ResultSet result;
		String SQL = new String();
		try {
			SQL = String.format("select * from khuyen_mai where TGDR <= '%s' and TGKT >= '%s';", time, time);
			result = st.executeQuery(SQL);
			result.next();
			dot = result.getInt("Id_KM");
			result.close();
			// lấy giá mặt hàng nếu nằm trong mặt hàng được khuyến mãi
			SQL = String.format(
					"select Gia_KM from mat_hang natural join duoc_khuyen_mai where ID_Mat_Hang = %d and Id_KM = %d;",
					code, dot);
			result = st.executeQuery(SQL);
			result.next();
			price = result.getInt("Gia_KM");
			sum = sum + price * so_luong;
			SQL = String.format("select Soluong from mat_hang where ID_MatHang=%d", code);
			result = st.executeQuery(SQL);
			result.next();
			// update lại số lượng còn lại !
			SQL = String.format("update mat_hang set Soluong=%d-%d where ID_MatHang=%d", result.getInt("Soluong"),
					so_luong, code);
			st.executeUpdate(SQL);
			return sum;
			// tính tiền mặt hàng nếu không thuộc đợt khuyến mãi !
		} catch (SQLException ex) {
			SQL = String.format("select Gia_Ban from mat_hang where ID_MatHang=%d;", code);
			result = st.executeQuery(SQL);
			result.next();
			price = result.getInt("Gia_Ban");
			sum = sum + price * so_luong;
			// update lại số lượng còn lại !
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
