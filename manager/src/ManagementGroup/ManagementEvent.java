package ManagementGroup;

import java.sql.*;
import java.util.Scanner;

import tinh_tien.ConnectToDTB;
import tinh_tien.Data;

public class ManagementEvent {
	static int i = 0, j = 0;
	static java.sql.Connection connect;
	static ResultSet rs;
	static Scanner input = new Scanner(System.in);
	static String c = new String();

	public static void main(String[] args) {
		ConnectToDTB con = new ConnectToDTB();
		Data info = new Data();
		con.connectDTB();
		String sql = new String();
		do {
			System.out.print("1,Them dot khuyen mai\n2,Xoa dot khuyen mai\n3,Chinh sua khuyen mai\nchọn:");
			i = Integer.parseInt(input.nextLine());
			try {
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sieu_Thi_VN", "root", "hedspik58");
				Statement st = connect.createStatement();
				switch (i) {
				case 1: {
					while (j == 0) {
						info.Input_IdKM();
						sql = String.format("select Id_KM from khuyen_mai where Id_KM=%d;", info.Id_KM);
						rs = st.executeQuery(sql);
						if (rs.next()) {
							System.out.print("dot khuyen mai da ton tai!\nTiep tu nhap khuyen mai ?(y/n):");
							c = input.nextLine();
							if (c.equals("n"))
								break;
						} else {
							info.Input_TenKM();
							while (j == 0) {
								info.Input_TGDR();
								info.Input_TGKT();
								if (info.TGDR.compareTo(info.TGKT) <0)
									break;
								else {
									System.out.println("Thoi gian nhap khong thoa man!");
								}
							}
							while (j == 0) {
								info.InputID();
								sql = String.format(
										"select ID_MatHang from duoc_khuyen_mai where ID_MatHang=%d and Id_KM=%d",
										info.id, info.Id_KM);
								if (rs.next()) {
									System.out.println("mat hang da ton tai !");
								} else
									break;
							}
							info.Input_GiaKM();
							sql = String.format("insert into duoc_khuyen_mai values(%d,%d,%d)", info.Id_KM, info.id,
									info.Gia_KM);
							st.executeUpdate(sql);
							sql = String.format("insert into khuyen_mai values(%d,'%s','%s','%s');", info.Id_KM,
									info.Ten_KM, info.TGDR, info.TGKT);
							st.executeUpdate(sql);
							System.out.println("Them dot khuyen mai thanh cong !");
							break;
						}
					}
					break;
				}
				case 2: {
					while (j == 0) {
						info.Input_TenKM();
						sql = String.format("select Ten_KM from khuyen_mai where  Ten_KM = '%s';", info.Ten_KM);
						rs = st.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("Dot khuyen mai khong ton tai!");
						} else {
							sql = String.format("select Id_KM from khuyen_mai where Ten_KM='%s'", info.Ten_KM);
							rs = st.executeQuery(sql);
							rs.next();
							info.Id_KM = rs.getInt("Id_KM");
							sql = String.format("delete from khuyen_mai where Id_KM=%d;", info.Id_KM);
							st.executeUpdate(sql);
							sql = String.format("delete from duoc_khuyen_mai where Id_KM=%d;", info.Id_KM);
							st.executeUpdate(sql);
							System.out.println("Xoa dot khuyen mai thanh cong !");
							break;
						}
					}
					break;
				}
				case 3: {
					sql = String.format("SET SQL_SAFE_UPDATES = 0;");
					st.execute(sql);
					do {
						while (j == 0) {
							info.Input_TenKM();
							sql = String.format("select Ten_KM from khuyen_mai where Ten_KM ='%s';", info.Ten_KM);
							rs = st.executeQuery(sql);
							if (rs.next()) {
								// c=rs.getString("Ten_MH");
								// các chức năng chỉnh sửa:
								System.out.print(
										"Thong tin muon thay doi !\n1,Id khuyen mai\n2,ten dot khuyen mai\n3,thoi gian dien ra va ket thuc dot khuyen mai\n4,Mat hang duoc khuyen mai\nchon:");
								i = Integer.parseInt(input.nextLine());
								switch (i) {
								case 1: {
									try {
										info.Input_IdKM();
										sql = String.format(
												"update duoc_khuyen_mai,khuyen_mai set duoc_khuyen_mai.Id_KM =%d , khuyen_mai.Id_KM=%d where khuyen_mai.Id_KM=duoc_khuyen_mai.Id_KM and Ten_KM='%s' ",
												info.Id_KM, info.Id_KM, info.Ten_KM);
										st.executeUpdate(sql);
										System.out.println("update id khuyen mai thanh cong !");
									} catch (SQLException a) {
										System.out.println(a);
									}
									break;
								}
								case 2: {
									try {
										// info.Input_GiaBan();
										System.out.print("nhap ten dot khuyen mai sua doi:");
										c = input.nextLine();
										sql = String.format("update khuyen_mai set Ten_KM = '%s'where Ten_KM = '%s'", c,
												info.Ten_KM);
										st.executeUpdate(sql);
										System.out.println("update thanh cong !");
									} catch (SQLException b) {
										System.out.println(b);
									}
									break;
								}
								case 3: {
									try {
										while (j == 0) {
											info.Input_TGDR();
											info.Input_TGKT();
											if (info.TGDR.compareTo(info.TGKT) < 0)
												break;
											else {
												System.out.println("Thoi gian nhap khong thoa man!");
											}
										}
										sql = String.format(
												"update khuyen_mai set TGDR = '%s' ,TGKT='%s' where Ten_KM = '%s'",
												info.TGDR, info.TGKT, info.Ten_KM);
										st.executeUpdate(sql);
										System.out.println("update thanh cong !");
									} catch (SQLException d) {
										System.out.println(d);
									}
									break;
								}
								case 4: {
									do {
										while (j == 0) {
											info.InputID();
											sql = String.format(
													"select Id_MatHang from duoc_khuyen_mai natural join khuyen_mai where ID_MatHang=%d and Ten_KM='%s'",
													info.id, info.Ten_KM);
											rs = st.executeQuery(sql);
											if (rs.next()) {
												System.out.print("mat hang da ton tai!chinh sua mat hang nay?(y/n):");
												c = input.nextLine();
												if (c.equals("y")) {
													System.out.print(
															"1,Thay doi gia\n2,Xoa mat hang khoi dot khuyen mai\nChon");
													i = Integer.parseInt(input.nextLine());
													switch (i) {
													case 1: {
														info.Input_GiaKM();
														sql = String.format(
																"update duoc_khuyen_mai set Gia_KM=%d where ID_MatHang=%d",
																info.Gia_KM, info.id);
														st.executeUpdate(sql);
														break;
													}
													case 2: {
														sql = String.format(
																"delete from duoc_khuyen_mai where ID_MatHang=%d",
																info.id);
														st.executeUpdate(sql);
														System.out.println(
																"xoa mat hang khoi dot khuyen mai thanh cong !");
														break;
													}
													}
													break;
												} else
													continue;
											} else {
												sql = String.format("select Id_KM from khuyen_mai where Ten_KM ='%s';",
														info.Ten_KM);
												rs = st.executeQuery(sql);
												rs.next();
												info.Input_GiaKM();
												sql = String.format("insert into duoc_khuyen_mai value (%d,%d,%d);",
														rs.getInt("Id_KM"), info.id, info.Gia_KM);
												st.executeUpdate(sql);
												break;
											}
										}
										System.out.print("tiep tuc nhap mat hang khuyen mai?(y/n):");
										c = input.nextLine();
									} while (c.equals("y"));
								}
								}
								break;
							} else {
								System.out.println("Ten dot khuyen mai khong ton tai !");
							}
						}
						System.out.print("tiep tuc chinh sua khuyen mai ? (y/n)");
						c = input.nextLine();
					} while (c.equals("y"));
				}
				}
			} catch (SQLException ex) {
				System.out.println(ex);
			}
			System.out.println("tiep tuc? (y/n)");
			c = input.nextLine();
		} while (c.equals("y"));

		// public static checkDate(String date1,String date2){
		//
		// }
	}
}
