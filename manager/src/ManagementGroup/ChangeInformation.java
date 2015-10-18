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
					// nhập tên mặt hàng
					while (j == 0) {
						info.InputName();
						sql = String.format("select Ten_MH from mat_hang where Ten_MH ='%s'", info.name);
						rs = st.executeQuery(sql);
						// kiểm tra tên mặt hàng đã tồn tại hay chưa
						if (rs.next()) {
							c = rs.getString("Ten_MH");
							System.out.println("nhap ten bi trung!");
						} else {
							break;
						}
					}
					// nhập ID mặt hàng
					while (j == 0) {
						info.InputID();
						sql = String.format("select ID_MatHang from mat_hang where ID_MatHang = %d;", info.id);
						rs = st.executeQuery(sql);
						// kiểm tra xem đã tồn tại ID chưa?
						if (rs.next()) {
							i = rs.getInt("ID_MatHang");
							System.out.println("nhap id bi trung!");
						} else {
							break;
						}
					}
					//nhập số lượng
					info.InputSum();
					rs = st.executeQuery(sql);
					info.Input_GiaBan();
					info.Input_GiaNhap();
					// nhập ID nhóm hàng
					while (j == 0) {
						info.Input_idGroup();
						sql = String.format("select ID_NhomHang from nhomhang where ID_NhomHang = %d ", info.idGroup);
						rs = st.executeQuery(sql);
						// kiểm tra xem đã tồn tại hay chưa
						if (rs.next()) {
							// i = rs.getInt("ID_NhomHang");
							break;
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
										// c = rs.getString("Ten_NhomHang");
										System.out.println("Ten nhom hang da ton tai !");
									} else {
										sql = String.format("insert into nhomhang values ('%d','%s'); ", info.idGroup,
												info.nameGroup);
										st.executeUpdate(sql);
										System.out.println("them nhom hang thanh cong !");
										break;
									}
								}
								break;
							} else
								continue;
						}
						// break;
					}
					while (j == 0) {
						info.Input_IdNCC();
						sql = String.format("select Id_NCC from ncc where Id_NCC=%d", info.Id_NCC);
						rs = st.executeQuery(sql);
						// kiểm tra đã tồn tại ID NCC hay chưa ?
						if (rs.next()) {
							break;
						} else {
							System.out.print("Id chua ton tai! \nThem thong tin ?(y/n):");
							c = input.nextLine();
							if (c.equals("y")) {
								while (j == 0) {
									info.Input_TenNCC();
									sql = String.format("select Ten_NCC from ncc where Ten_NCC ='%s'", info.Ten_NCC);
									rs = st.executeQuery(sql);
									if (rs.next()) {
										System.out.println("Ten NCC da ton tai !");
									} else
										break;
								}
								info.Input_TTLH();
								info.Input_SDT();
								info.Input_Mail();
								sql = String.format("insert into ncc values(%d,'%s',%d,'%s','%s')", info.Id_NCC,
										info.Ten_NCC, info.SDT, info.Mail, info.TTLH);
								st.executeUpdate(sql);
								System.out.println("Them thong tin NCC thanh cong !");
								break;
							} else
								continue;
						}
					}
					// info.Input_TTLH();
					// thêm thông tin sản phẩm
					try {
						sql = String.format("insert into mat_hang values ('%d','%d','%d','%s','%d'); ", info.id,
								info.Gia_Nhap, info.Gia_Ban, info.name, info.so_luong);
						st.executeUpdate(sql);
						// System.out
						sql = String.format("insert into cung_cap values(%d,%d)", info.id, info.Id_NCC);
						st.executeUpdate(sql);
						sql = String.format("insert into thuoc_nhom values ('%d','%d');", info.id, info.idGroup);
						st.executeUpdate(sql);
						System.out.println("them mat hang thanh cong !");
					} catch (SQLException ej) {
						System.out.println(ej);
					}
					break;
				}
					// Xóa mặt hàng
				case 2: {
					sql = String.format("SET SQL_SAFE_UPDATES = 0;");
					st.execute(sql);
					while (j == 0) {
						info.InputName();
						sql = String.format("select Ten_MH from mat_hang where Ten_MH = '%s';", info.name);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							sql = String.format("select ID_MatHang from mat_hang where Ten_MH='%s';", info.name);
							rs = st.executeQuery(sql);
							rs.next();
							info.id = rs.getInt("ID_MatHang");
							sql = String.format("delete from cung_cap where ID_MatHang=%d;", info.id);
							st.executeUpdate(sql);
							sql = String.format("delete from thuoc_nhom where ID_MatHang=%d;", info.id);
							st.executeUpdate(sql);
							sql = String.format("delete from mat_hang where ID_MatHang=%d", info.id);
							st.executeUpdate(sql);
							System.out.println("Xoa mat hang thanh cong !");
							break;
						} else {
							System.out.println("mat hang khong ton tai !");
						}
					}
					break;
				}
					// thay đổi thông tin mặt hàng
				case 3: {
					sql = String.format("SET SQL_SAFE_UPDATES = 0;");
					st.execute(sql);
					while (j == 0) {
						info.InputName();
						sql = String.format("select Ten_MH from mat_hang where Ten_MH ='%s';", info.name);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							// c=rs.getString("Ten_MH");
							System.out.print(
									"Thong tin muon thay doi !\n1,Ten mat hang\n2,Gia ban\n3,Gia nhap\n4,nhom hang\n5,so luong\nchon:");
							i = Integer.parseInt(input.nextLine());
							switch (i) {
							// thay đổi tên mặt hàng
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
								// thay đổi giá bán mặt hàng
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
								// thay đổi giá nhập mặt hàng
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
								// thay đổi nhóm hàng
							case 4: {
								try {
									while (j == 0) {
										info.Input_idGroup();
										sql = String.format("select ID_NhomHang from nhomhang where ID_NhomHang=%d",
												info.idGroup);
										rs = st.executeQuery(sql);
										if (!rs.next()) {
											System.out.print("nhom hang chua ton tai!\nThem nhom hang ?(y/n)");
											c = input.nextLine();
											if (c.equals("y")) {
												while (j == 0) {
													info.Input_NameGroup();
													// info.Input_idGroup();
													sql = String.format(
															"select Ten_NhomHang from nhomhang where Ten_Nhomhang = '%s';",
															info.nameGroup);
													rs = st.executeQuery(sql);
													if (rs.next()) {
														// c =
														// rs.getString("Ten_NhomHang");
														System.out.println("Ten nhom hang da ton tai !");
													} else {
														sql = String.format("insert into nhomhang values ('%d','%s'); ",
																info.idGroup, info.nameGroup);
														st.executeUpdate(sql);
														System.out.println("them nhom hang thanh cong !");
														break;
													}
												}
												break;
											} else
												continue;
										} else
											break;
									}
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
								// thay đổi số lượng
							case 5: {
								try {
									info.InputSum();
									sql = String.format("update mat_hang set Soluong = %d where Ten_MH = '%s';",
											info.so_luong, info.name);
									st.executeUpdate(sql);
									System.out.println("update thanh cong !");
								} catch (SQLException ad) {
									System.out.println(ad);
								}
								break;
							}
							}
							break;
						} else {
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
