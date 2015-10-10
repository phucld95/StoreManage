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
	public int dot, Gia_Ban, id;

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
					SQL = String.format("select * from khuyen_mai where TGDR <= '%s' and '%s'<=TGKT", time, time);
					result = st.executeQuery(SQL);
					result.next();
					dot = result.getInt("Id_KM");
					result.close();
					// lấy giá mặt hàng nếu nằm trong mặt hàng được khuyến mãi
					SQL = String.format(
							"select Gia_KM from mat_hang natural join duoc_khuyen_mai where Ten_MH = '%s' and Id_KM = %d;",
							Sale_Data.name, dot);
					rs = st.executeQuery(SQL);
					rs.next();
					Gia_Ban = rs.getInt("Gia_KM");
					Sale_Data.sum = Sale_Data.sum + Gia_Ban * Sale_Data.so_luong;
					System.out.println(
							"mat hang thuoc dot khuyen mai thu: " + dot + "\nCó giá bán khuyến mãi:" + Gia_Ban);
				} catch (SQLException ex) {
					SQL = String.format("select Gia_Ban from mat_hang where Ten_MH = '%s';", Sale_Data.name);
					rs = st.executeQuery(SQL);
					rs.next();
					try {
						Gia_Ban = rs.getInt("Gia_Ban");
						Sale_Data.sum = Sale_Data.sum + Gia_Ban * Sale_Data.so_luong;
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