package ProcessDatabases;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JDialog;

public class Invoice {
	private static Statement st;
	String danhSach = new String();
	public int sumPrice = 0;
	public int price=0;
	// String time = new String();
	String Ten_KM = new String();
	String TGDR = new String();
	String TGKT = new String();
	int[] id_KM = new int[100];
	timeSystem time = new timeSystem();
	private Scanner Input = new Scanner(System.in);
	private int ids;
	private int nums;
	private String SQL;
	private ResultSet rs;
	
	
	public Invoice(Statement sts) {
		st = sts;
	}

	// in hoa don vao database
	// Truyền vào chuỗi theo cấu trúc quy định.
	public void setInvoice(String str, int id_Account) throws SQLException {
		SetInfo Info = new SetInfo();
		Info = Info.cat_Chuoi(str);
		sumPrice = 0;
		String sql = new String();
		for (int k = 0; k < Info.temp; k++) {
			sumPrice = sumPrice + tinh_tien(Info.id[k], Info.sum[k]);
		}
		sql = String.format("insert into hoa_don(Noi_Dung,Tong_Tien,ID_ThuNgan,Thoi_Gian) values('%s',%d,%d,'%s')", str,
				sumPrice, id_Account, time.fullDate());
		st.executeUpdate(sql);
	}

	// truyen vao id tra ve tong so tien tuong ung voi id do
	public int getInvoice(int id) {
		ResultSet rs;
		sumPrice=0;
		String sql = new String();
		try {
			sql = String.format("select Tong_Tien from hoa_don where ID_ThuNgan=%d",id);
			rs = st.executeQuery(sql);
			//rs.next();
			while(rs.next()){
				sumPrice=sumPrice+rs.getInt("Tong_Tien");
			}
			return sumPrice;

		} catch (SQLException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	// method tinh tien cua mot san pham !
	
	public int tinh_tien(int id, int sum) throws SQLException {
		ids = id;
		nums = sum;
		sumPrice = 0;
		ResultSet result;
		int i = 0;
		int j = 0,k;
		SQL = String.format("select Soluong from mat_hang where ID_MatHang=%d", id);
		result = st.executeQuery(SQL);
		result.next();
		k = result.getInt("Soluong");
		System.out.print(k);
		if(k < sum){
			return -1;
		}
		else{
			try {
				i=0;
				SQL = String.format("select dkm.Id_KM, Ten_KM, ID_MatHang, Gia_KM from khuyen_mai km, duoc_khuyen_mai dkm where TGDR <= '%s' and TGKT >= '%s' and dkm.Id_KM = km.Id_KM and ID_MatHang = '%d';",
						time.Date(),time.Date(),id);
//				SQL = String.format("select dkm.Id_KM, Ten_KM, ID_MatHang, Gia_KM from khuyen_mai km, duoc_khuyen_mai dkm where TGDR <= '%s' and TGKT >= '%s' and dkm.Id_KM = km.Id_KM and ID_MatHang = '%d';",
//						"2015-10-11","2015-10-11",id);
				result = st.executeQuery(SQL);
				// result.next();
				while (result.next()) {
					id_KM[i] = result.getInt("id_KM");
					i++;
				}
				System.out.print("i = |" + i + "|");
				// kiem tra co bao nhieu dot khuyen mai trung nhau !
				
				if(i==1) {
					result.close();
					SQL = String.format(
							"select Gia_KM from mat_hang natural join duoc_khuyen_mai where ID_MatHang = %d and Id_KM = %d;",
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
				else if(i==0){
					SQL = String.format("select Gia_Ban from mat_hang where ID_MatHang=%d;", id);
					result = st.executeQuery(SQL);
					result.next();
					price = result.getInt("Gia_Ban");
					sumPrice = price * sum;
					// update lai so luong mat hang con lai !
					
					SQL = String.format("update mat_hang set Soluong=%d where ID_MatHang=%d",
							(k - sum),id);
					st.executeUpdate(SQL);
					return sumPrice;
				}
				else {
					return 0;
					// tinh tien theo dot khuyen mai nguoi dung chon!							
				}
			} catch (SQLException ex) {	
			}
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
						Info.id[Info.temp] = Integer.parseInt(rs[k]);
					}
					if (k == 1) {
						Info.sum[Info.temp] = Integer.parseInt(rs[k]);
					}
				}
				// Info.sum[temp] = Integer.parseInt(r);

			}
			Info.temp++;
		}
		return Info;

	}
}