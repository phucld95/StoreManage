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
	private Scanner nhap;

	public void getData() {
		// kết nối database
		ConnectToDTB con = new ConnectToDTB();
		Data Sale_Data = new Data();
		String c = new String();
		String SQL = new String();
		int i, j = 1;
		nhap = new Scanner(System.in);
		con.connectDTB();
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
							"select distinct Ten_MH,Gia_Ban from mat_hang natural join nhomhang natural join thuoc_nhom where Ten_Nhomhang = '%s';",
							Sale_Data.name);
					try {
						rs = st.executeQuery(SQL);
						rs.next();
						do {
							String Ten_MH = rs.getString("Ten_MH");
							int Gia_Ban = rs.getInt("Gia_Ban");
							System.out.print("||" + Ten_MH + "||");
							System.out.println(Gia_Ban + "||");
						} while (rs.next());
						rs.close();
					} catch (SQLException ex) {
						System.out.println("không tồn tại nhóm hàng");
					}
					break;
				}
				case 2: {
					Sale_Data.InputID();
					SQL = String.format(
							"select distinct Ten_MH from mat_hang natural join thuoc_nhom where ID_NhomHang = '%d';",
							Sale_Data.id);
					try {
						rs = st.executeQuery(SQL);
						rs.next();
						do {
							String Ten_MH = rs.getString("Ten_MH");
							System.out.println(Ten_MH);
						} while (rs.next());
						rs.close();
					} catch (SQLException ex) {
						System.out.println("không tồn tại ID");
					}
					break;
				}
				case 3: {
					while (j == 1) {
						Sale_Data.Input_NameGroup();
						Sale_Data.Input_idGroup();
						SQL = String.format(
								"select Ten_Nhomhang,ID_NhomHang from nhomhang where Ten_Nhomhang = '%s' and ID_NhomHang = %d",
								Sale_Data.nameGroup, Sale_Data.idGroup);
						rs=st.executeQuery(SQL);
						if(rs.next()){
							c=rs.getString("Ten_Nhomhang");
							System.out.println("thong tin da ton tai");
						}
						else j=0;
					}
					SQL = String.format("insert into nhomhang values ('%d','%s'); ", Sale_Data.id, Sale_Data.name);
					i = st.executeUpdate(SQL);
					System.out.println("them thanh cong !");
					break;
				}
				case 4: {
					Sale_Data.InputName();
					SQL = String.format("delete from nhomhang where Ten_NhomHang='%s'", Sale_Data.name);
					i = st.executeUpdate(SQL);
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
