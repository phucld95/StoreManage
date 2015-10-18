package tinh_tien;

import java.sql.*;
import java.util.Scanner;

public class cash {
	public static void main(String[] args) {
		getData inputData = new getData();
		inputData.DataProcessing();
	}
}

class getData {
	private Scanner nhap;
	public String c, time, SQL;

	public void DataProcessing() {
		ConnectToDTB data = new ConnectToDTB();
		Data Sale_Data = new Data();
		c = new String();
		time = timeSystem.Date();
		nhap = new Scanner(System.in);
		data.connectDTB();
		Connection connect;
		ResultSet rs;
		ResultSet result;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sieu_Thi_VN", "root", "hedspik58");
			Statement st = connect.createStatement();
			// nhập mặt hàng và đưa ra số tiền phải trả
			do {
				Sale_Data.InputName();
				Sale_Data.InputSum();
				// kiểm tra xem có đang trong đợt khuyến mãi hay không ?
				try {
					SQL = String.format("select * from khuyen_mai where TGDR <= '%s' and TGKT >= '%s';", time, time);
					result = st.executeQuery(SQL);
					result.next();
					Sale_Data.dot = result.getInt("Id_KM");
					result.close();
					// lấy giá mặt hàng nếu nằm trong mặt hàng được khuyến mãi
					SQL = String.format(
							"select Gia_KM from mat_hang natural join duoc_khuyen_mai where Ten_MH = '%s' and Id_KM = %d;",
							Sale_Data.name, Sale_Data.dot);
					rs = st.executeQuery(SQL);
					rs.next();
					Sale_Data.Gia_Ban = rs.getInt("Gia_KM");
					Sale_Data.sum = Sale_Data.sum + Sale_Data.Gia_Ban * Sale_Data.so_luong;
					SQL = String.format("select Soluong from mat_hang where Ten_MH='%s'", Sale_Data.name);
					rs = st.executeQuery(SQL);
					rs.next();
					//update lại số lượng còn lại !
					SQL = String.format("update mat_hang set Soluong=%d-%d where Ten_MH='%s'", rs.getInt("Soluong"),
							Sale_Data.so_luong, Sale_Data.name);
					st.executeUpdate(SQL);
					System.out.println("mat hang thuoc dot khuyen mai thu: " + Sale_Data.dot
							+ "\nCó giá bán khuyến mãi:" + Sale_Data.Gia_Ban);
					// tính tiền mặt hàng nếu không thuộc đợt khuyến mãi !
				} catch (SQLException ex) {
					SQL = String.format("select Gia_Ban from mat_hang where Ten_MH = '%s';", Sale_Data.name);
					rs = st.executeQuery(SQL);
					rs.next();
					try {
						Sale_Data.Gia_Ban = rs.getInt("Gia_Ban");
						Sale_Data.sum = Sale_Data.sum + Sale_Data.Gia_Ban * Sale_Data.so_luong;
						//update lại số lượng còn lại !
						SQL = String.format("select Soluong from mat_hang where Ten_MH='%s'", Sale_Data.name);
						rs = st.executeQuery(SQL);
						rs.next();
						SQL = String.format("update mat_hang set Soluong=%d-%d where Ten_MH='%s'", rs.getInt("Soluong"),
								Sale_Data.so_luong, Sale_Data.name);
						st.executeUpdate(SQL);
					} catch (SQLException e) {
						System.out.println("Không tồn tạo mặt hàng");
					}
				}
				System.out.print("tiep tu nhap mat hang ? (y/n)");
				c = nhap.nextLine();
			} while (c.equals("y"));
			// in thời gian mua hàng
			timeSystem.PrintDate();
			System.out.println("so tien can tra:" + Sale_Data.sum + " VNĐ");
			connect.close();
			st.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}