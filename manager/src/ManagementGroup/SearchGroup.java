package ManagementGroup;
import java.sql.*;
import tinh_tien.*;
import java.util.Scanner;
public class SearchGroup {
	public static void main(String[] agrs){
		search mathang = new search();
		mathang.getData();
	}
}
class search{
	public void getData(){
		//kết nối database
		ConnectToDTB data = new ConnectToDTB();
		Data Sale_Data = new Data();
		String c;
		Scanner nhap = new Scanner(System.in);
		data.connectDTB();
		Connection connect;
		ResultSet rs;
		try{
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sieu_Thi_VN","root","hedspik58");
        Statement st = connect.createStatement();
        //nhập tên nhóm hàng và đưa ra tất cả các mặt hàng
            do {
                Sale_Data.InputName();
                String SQL = String.format("select Ten_MH from mat_hang natural join nhomhang where Ten_Nhomhang = '%s';", Sale_Data.name);
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    String Ten_MH = rs.getString("Ten_MH");
                    System.out.println(Ten_MH);
                }
                System.out.print("tiep tu nhap mat hang ? (y/n)");
                c = nhap.nextLine();
            } while (c.equals("y"));
			connect.close();
			st.close();
			rs.close();
	}catch(SQLException ex){
		System.out.println(ex);
	}
	}
}
