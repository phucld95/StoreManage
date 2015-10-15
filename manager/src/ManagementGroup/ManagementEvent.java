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
							System.out.println("dot khuyen mai da ton tai");
						} else {
							info.Input_TenKM();
							info.Input_TGDR();
							info.Input_TGKT();
							sql = String.format("insert into khuyen_mai value(%d,'%s','%s','%s');", info.Id_KM,
									info.Ten_KM, info.TGDR, info.TGKT);
							st.executeUpdate(sql);
							System.out.println("Them thanh cong !");
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
							sql = String.format("delete from khuyen_mai where Ten_KM='%s';", info.Ten_KM);
							System.out.println("delete thanh cong !");
							break;
						}
					}
					break;
				}
				case 3: {
					do {
						while (j == 0) {
							info.Input_TenKM();
							sql = String.format("select Ten_KM from khuyen_mai where Ten_KM ='%s';", info.Ten_KM);
							rs = st.executeQuery(sql);
							if (rs.next()) {
								// c=rs.getString("Ten_MH");
								System.out.print(
										"Thong tin muon thay doi !\n1,Id khuyen mai\n2,ten khuyen\n3,thoi gian dien ra va ket thuc\nchon:");
								i = Integer.parseInt(input.nextLine());
								switch (i) {
								case 1: {
									try {
										// System.out.print("nhap id sua doi:");
										info.Input_IdKM();
										// info.Id_KM =
										// Integer.parseInt(input.nextLine());
										sql = String.format("update khuyen_mai set Id_KM = %d where Ten_KM = '%s'",
												info.Id_KM, info.Ten_KM);
										st.executeUpdate(sql);
										System.out.println("update thanh cong !");
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
										info.Input_TGDR();
										info.Input_TGKT();
										sql = String.format(
												"update khuyen_mai set TGDR = '%s' ,TGKT='%s' where Ten_KM = '%s'",
												info.TGDR, info.TGDR, info.Ten_KM);
										st.executeUpdate(sql);
										System.out.println("update thanh cong !");
									} catch (SQLException d) {
										System.out.println(d);
									}
									break;
								}
								}
								break;
							} else {
								System.out.println("Ten dot khuyen mai khong ton tai !");
							}
						}
						System.out.println("tiep tuc sua doi? (y/n)");
						c = input.nextLine();
					} while (c.equals("y"));
				}
				}
			} catch (SQLException ex) {
				System.out.println(ex);
			}
			System.out.println("tiep tuc? (y/n)");
			c=input.nextLine();
		} while (c.equals("y"));

		// public static checkDate(String date1,String date2){
		//
		// }
	}
}
