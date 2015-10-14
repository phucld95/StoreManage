package ManagementGroup;

import java.sql.*;
import java.util.Scanner;

import tinh_tien.ConnectToDTB;
import tinh_tien.Data;

public class ChangeInformation {
	static int i = 0, j = 0;
	static java.sql.Connection connect;
	static ResultSet rs;
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		ConnectToDTB con = new ConnectToDTB();
		Data info = new Data();
		con.connectDTB();
		String c = new String();
		String sql = new String();
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sieu_Thi_VN", "root", "hedspik58");
			Statement st = connect.createStatement();
			do {
				System.out.print("1,thêm 1 mặt hàng\n2, xóa 1 mặt hàng\n3,thay đổi thông tin mặt hàng\nChọn:");
				i = Integer.parseInt(input.nextLine());
				switch (i) {
				case 1: {
					while (j == 0) {
						info.InputName();
						sql = String.format("select Ten_MH from mat_hang where Ten_MH ='%s'", info.name);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							c = rs.getString("Ten_MH");
							System.out.println("nhap ten bi trung!");
						} else {
							// System.out.println("test");
							j = 1;
						}
					}
					while (j == 1) {
						info.InputID();
						sql = String.format("select ID_MatHang from mat_hang where ID_MatHang = %d", info.id);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							i = rs.getInt("ID_MatHang");
							System.out.println("nhap id bi trung!");
						} else {
							j = 0;
						}
					}
					info.InputSum();
					rs = st.executeQuery(sql);
					info.Input_GiaBan();
					info.Input_GiaNhap();
					while (j == 0) {
						info.Input_idGroup();
						sql = String.format("select ID_NhomHang from nhomhang where ID_NhomHang = %d ", info.idGroup);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							i = rs.getInt("ID_NhomHang");
							j = 1;
						} else {
							System.out.print("nhom hang chua ton tai!\nThem nhom hang ?(y/n)");
							c = input.nextLine();
							if (c.equals("y")) {
								while (j == 0) {
									info.Input_NameGroup();
									// info.Input_idGroup();
									sql = String.format("select Ten_NhomHang from nhomhang where Ten_Nhomhang = '%s';",
											info.nameGroup);
									rs = st.executeQuery(sql);
									if (rs.next()) {
										c = rs.getString("Ten_NhomHang");
										System.out.println("Ten nhom hang da ton tai !");
									} else {
										j = 1;
									}
								}
								sql = String.format("insert into nhomhang values ('%d','%s'); ", info.idGroup,
										info.nameGroup);
								st.executeUpdate(sql);
								System.out.println("them thanh cong !");
								j = 1;
							}
						}
					}
					try {
						sql = String.format("insert into mat_hang values ('%d','%d','%d','%s','%d'); ", info.id,
								info.Gia_Nhap, info.Gia_Ban, info.name, info.so_luong);
						st.executeUpdate(sql);
						// System.out
						sql = String.format("insert into thuoc_nhom values ('%d','%d');", info.id, info.idGroup);
						st.executeUpdate(sql);
					} catch (SQLException ej) {
						System.out.println(ej);
					}
					break;
				}
				case 2: {
					break;
				}
				case 3: {
					break;
				}
				}
				System.out.print("tiếp tục ? (y/n):");
				c = input.nextLine();
			} while (c.equals("y"));

		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
