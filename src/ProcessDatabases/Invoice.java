package ProcessDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Invoice {
	private static Statement st;
	String danhSach = new String();
	int sumPrice, price;
	//String time = new String();
	String Ten_KM = new String();
	String TGDR = new String();
	String TGKT = new String();
	int[] id_KM = new int[100];
	timeSystem time = new timeSystem();
	private Scanner Input = new Scanner(System.in);

	public Invoice(Statement sts) {
		st = sts;
	}
	// truyen vao str la idmathang va so luong tra ve so tien !
	public int getPriceProduct(String str) throws SQLException {
		SetInfo Info = new SetInfo();
		Info = Info.cat_Chuoi(str);
		return tinh_tien(Info.id[0], Info.sum[0]);
	}

	// in hoa don vao database
	public void setInvoice(String str, int id_Account) throws SQLException {
		SetInfo Info = new SetInfo();
		Info = Info.cat_Chuoi(str);
		sumPrice = 0;
		String sql = new String();
		for (int k = 0; k < Info.temp; k++) {
			sumPrice = sumPrice + tinh_tien(Info.id[k], Info.sum[k]);
		}
		sql = String.format("update hoa_don set Noi_Dung='%s',Tong_Tien=%d,ID_ThuNgan=%d,Thoi_Gian='%s'", str, sumPrice, id_Account,time.fullDate());
		st.executeUpdate(sql);
	}
//truyen vao id tra ve tong so tien tuong ung voi id do
	public int getInvoice(int id) {
		ResultSet rs;
		String sql=new String();
		try{
		sql=String.format("insert Tong_Tien from hoa_don where ID_ThuNgan=%d",id);
		rs=st.executeQuery(sql);
		rs.next();
		return rs.getInt("Tong_Tien");
		
		}catch(SQLException ex){
			System.out.println(ex);
			return 0;
		}
	}

	// method tinh tien cua mot san pham !
	public int tinh_tien(int id, int sum) throws SQLException {
		ResultSet result;
		int i = 0;
		int j;
		String SQL = new String();
		try {
			SQL = String.format("select * from khuyen_mai where TGDR <= '%s' and TGKT >= '%s';", time.Date(), time.Date());
			result = st.executeQuery(SQL);
			// result.next();
			while (!result.next()) {
				id_KM[i] = result.getInt("id_KM");
				i++;
			}
			// kiem tra co bao nhieu dot khuyen mai trung nhau !
			if (i > 1) {
				SQL = String.format("id_KM\tTen_KM\tTGDR\tTGKT");
				System.out.println(SQL);
				while (i > 0) {
					SQL = String.format("select * from khuyen_mai where id_KM=%d", id_KM[i]);
					result = st.executeQuery(SQL);
					Ten_KM = result.getString("Ten_KM");
					TGDR = result.getString("TGDR");
					TGKT = result.getString("TGKT");
					SQL = String.format("%d\t%s\t%s\t%s", id_KM[i], Ten_KM, TGDR, TGKT);
					System.out.println(SQL);
					i--;
				}
				//chon dot khuyen mai!
				System.out.print("moi ban chon dot khuyen mai, nhap id dot khuyen mai:");
				j = Integer.parseInt(Input.nextLine());
				// tinh tien theo dot khuyen moi nguoi dung chon!
				SQL = String.format(
						"select Gia_KM from mat_hang natural join duoc_khuyen_mai where ID_Mat_Hang = %d and Id_KM = %d;",
						id, j);
				result = st.executeQuery(SQL);
				result.next();
				price = result.getInt("Gia_KM");
				sumPrice = price * sum;
				SQL = String.format("select Soluong from mat_hang where ID_MatHang=%d", id);
				result = st.executeQuery(SQL);
				result.next();

				SQL = String.format("update mat_hang set Soluong=%d-%d where ID_MatHang=%d", result.getInt("Soluong"),
						sum, id);
				st.executeUpdate(SQL);
				return sumPrice;
				// mat hang chi co 1 dot khuyen mai!
			} else {
				result.close();
				SQL = String.format(
						"select Gia_KM from mat_hang natural join duoc_khuyen_mai where ID_Mat_Hang = %d and Id_KM = %d;",
						id, id_KM[0]);
				result = st.executeQuery(SQL);
				result.next();
				price = result.getInt("Gia_KM");
				sumPrice = sumPrice + price * sum;
				SQL = String.format("select Soluong from mat_hang where ID_MatHang=%d", id);
				result = st.executeQuery(SQL);
				result.next();
				SQL = String.format("update mat_hang set Soluong=%d-%d where ID_MatHang=%d", result.getInt("Soluong"),
						sum, id);
				st.executeUpdate(SQL);
				return sumPrice;
			}
		} catch (SQLException ex) {
			SQL = String.format("select Gia_Ban from mat_hang where ID_MatHang=%d;", id);
			result = st.executeQuery(SQL);
			result.next();
			price = result.getInt("Gia_Ban");
			sumPrice = price * sum;
			// update lai so luong mat hang con lai !
			SQL = String.format("select Soluong from mat_hang where ID_MatHang=%d", id);
			result = st.executeQuery(SQL);
			result.next();
			SQL = String.format("update mat_hang set Soluong=%d-%d where ID_MatHang=%d", result.getInt("Soluong"), sum,
					id);
			st.executeUpdate(SQL);
			return sumPrice;
		}
	}
}

// class lay thong tin san pham
class SetInfo {
	public int[] sum = new int[100];
	public int[] id = new int[100];
	public int temp;

	public SetInfo cat_Chuoi(String str) {
		SetInfo Info = new SetInfo();
		Info.temp = 0;
		// tach rieng tung san pham ra 1
		String result[] = str.split("[()]");
		for (String r : result) {
			if (r.isEmpty())
				continue;
			else {
				// tach idmat_hang va so luong cua 1 san pham
				String rs[] = r.split("[,]");
				for (int k = 0; k < rs.length; k++) {
					if (k == 0) {
						Info.id[Info.temp] = Integer.parseInt("rs[k]");
					}
					if (k == 1) {
						Info.sum[Info.temp] = Integer.parseInt("rs[k]");
					}
				}
				// Info.sum[temp] = Integer.parseInt(r);

			}
			Info.temp++;
		}
		return Info;

	}
}