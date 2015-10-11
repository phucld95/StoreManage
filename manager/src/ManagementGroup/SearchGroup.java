package ManagementGroup;

import java.sql.*;
import tinh_tien.*;
import java.util.Scanner;

public class SearchGroup {
	public static void main(String[] agrs) {
		search MatHang = new search();
		MatHang.getData();
	}
}

class search {
	public void getData() {
		// kết nối database
		ConnectToDTB data = new ConnectToDTB();
		Data Sale_Data = new Data();
		String c = new String();
		String SQL = new String();
		int i;
		Scanner nhap = new Scanner(System.in);
		data.connectDTB();
		Connection connect;
		ResultSet rs = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sieu_Thi_VN", "root", "hedspik58");
			Statement st = connect.createStatement();
			// nhập tên nhóm hàng và đưa ra tất cả các mặt hàng
			do {
				System.out.print("1,tim theo nhóm hàng\n2,tìm theo ID\n3,thêm nhóm hàng\n4,xóa nhóm hàng\nchọn:");
				i = nhap.nextInt();
				c = nhap.nextLine();
				switch (i) {
				case 1: {
					Sale_Data.InputName();
					SQL = String.format(
							"select distinct Ten_MH,Gia_Ban from mat_hang natural join nhomhang where Ten_Nhomhang = '%s';",
							Sale_Data.name);
					rs = st.executeQuery(SQL);
					while (rs.next()) {
						String Ten_MH = rs.getString("Ten_MH");
						int Gia_Ban = rs.getInt("Gia_Ban");
						System.out.print("||" + Ten_MH + "||");
						System.out.println(Gia_Ban + "||");
					}
					rs.close();
					break;
				}
				case 2: {
					Sale_Data.InputID();
					SQL = String.format("select distinct Ten_MH from mat_hang where ID_NhomHang = '%d';", Sale_Data.id);
					rs = st.executeQuery(SQL);
					while (rs.next()) {
						String Ten_MH = rs.getString("Ten_MH");
						System.out.println(Ten_MH);
					}
					rs.close();
					break;
				}
				case 3: {
					Sale_Data.InputName();
					Sale_Data.InputID();
					SQL= String.format("insert into nhomhang values ('%d','%s'); ",Sale_Data.id,Sale_Data.name);
					i = st.executeUpdate(SQL);
					break;
				}
				case 4:{
					Sale_Data.InputName();
					SQL = String.format("delete from nhomhang where Ten_NhomHang='%s'",Sale_Data.name);
					i=st.executeUpdate(SQL);
					break;
				}
				}
				System.out.print("tiếp tục ? (y/n):");
				c = nhap.nextLine();
			} while (c.equals("y"));
			st.close();
			
			connect.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}
}
