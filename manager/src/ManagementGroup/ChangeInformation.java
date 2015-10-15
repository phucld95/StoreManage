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
							break;
						}
					}
					while (j == 0) {
						info.InputID();
						sql = String.format("select ID_MatHang from mat_hang where ID_MatHang = %d", info.id);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							i = rs.getInt("ID_MatHang");
							System.out.println("nhap id bi trung!");
						} else {
							break;
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
							// break;
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
										break;
									}
								}
								sql = String.format("insert into nhomhang values ('%d','%s'); ", info.idGroup,
										info.nameGroup);
								st.executeUpdate(sql);
								System.out.println("them thanh cong !");
								break;
							}
						}
						break;
					}
					try {
						sql = String.format("insert into mat_hang values ('%d','%d','%d','%s','%d'); ", info.id,
								info.Gia_Nhap, info.Gia_Ban, info.name, info.so_luong);
						st.executeUpdate(sql);
						// System.out
						sql = String.format("insert into thuoc_nhom values ('%d','%d');", info.id, info.idGroup);
						st.executeUpdate(sql);
						System.out.println("them thanh cong !");
					} catch (SQLException ej) {
						System.out.println(ej);
					}
					break;
				}
				case 2: {
					while (j == 0) {
						info.InputName();
						sql = String.format("select Ten_MH from mat_hang where Ten_MH = '%s';", info.name);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							c = rs.getString("Ten_MH");
							sql = String.format("delete from mat_hang where Ten_MH='%s'", info.name);
							System.out.println("Xoa thanh cong !");
							break;
						} else {
							System.out.println("mat hang khong ton tai !");
						}
					}
					break;
				}
				case 3: {
					while (j == 0) {
						info.InputName();
						sql = String.format("select Ten_MH from mat_hang where Ten_MH ='%s';", info.name);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							//c=rs.getString("Ten_MH");
							System.out.print(
									"Thong tin muon thay doi !\n1,Ten mat hang\n2,Gia ban\n3,Gia nhap\n4,nhom hang\n5,so luong\nchon:");
							i = Integer.parseInt(input.nextLine());
							switch (i) {
							case 1: {
								try {
									System.out.print("Nhap ten sua doi:");
									c = input.nextLine();
									sql = String.format("update mat_hang set Ten_MH = '%s' where Ten_MH = '%s'", c,
											info.name);
									st.executeUpdate(sql);
									System.out.println("update thanh cong !");
								} catch (SQLException a) {
									System.out.println(a);
								}
								break;
							}
							case 2: {
								try {
									info.Input_GiaBan();
									sql = String.format("update mat_hang set Gia_Ban = %d where Ten_MH = '%s'",
											info.Gia_Ban, info.name);
									st.executeUpdate(sql);
									System.out.println("update thanh cong !");
								} catch (SQLException b) {
									System.out.println(b);
								}
								break;
							}
							case 3: {
								try {
									info.Input_GiaNhap();
									sql = String.format("update mat_hang set Gia_Nhap = %d where Ten_MH = '%s'",
											info.Gia_Nhap, info.name);
									st.executeUpdate(sql);
									System.out.println("update thanh cong !");
								} catch (SQLException d) {
									System.out.println(d);
								}
								break;
							}
							case 4: {
								try {
									info.Input_idGroup();
									sql = String.format(
											"update thuoc_nhom,mat_hang set ID_NhomHang=%d where thuoc_nhom.ID_Mathang = mat_hang.ID_matHang and Ten_MH='%s'; ",
											info.idGroup, info.name);
									st.executeUpdate(sql);
									System.out.println("update thanh cong !");
								} catch (SQLException ab) {
									System.out.println(ab);
								}
								break;
							}
							case 5: {
								try {
									info.InputSum();
									sql = String.format("update mat_hang set Soluong = %d where Ten_MH = '%s';",info.so_luong, info.name);
									st.executeUpdate(sql);
									System.out.println("update thanh cong !");
								} catch (SQLException ad) {
									System.out.println(ad);
								}
								break;
							}
							}
							break;
						}
						else{
							System.out.println("Ten mat hang khong ton tai !");
						}
					}
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
