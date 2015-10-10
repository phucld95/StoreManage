package tinh_tien;

import java.sql.*;
import java.util.Scanner;

public class cash {
	public static void main(String[] args ){
		getData inputData = new getData();
		inputData.DataProcessing();
	}
}
class getData{
	public void  DataProcessing(){
		ConnectToDTB data = new ConnectToDTB();
		Data Sale_Data = new Data();
		String c;
		Scanner nhap = new Scanner(System.in);
		data.connectDTB();
		Connection connect;
		ResultSet rs;
		try{
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sieu_Thi_VN","root","hedspik58");
			//System.out.println("Get Connect To Database Complete ...\n");	
			//stmt = connect.createStatement();
        Statement st = connect.createStatement();
        //nhập mặt hàng và đưa ra số tiền phải trả
            do {
                Sale_Data.InputName();
                Sale_Data.InputSum();
                String SQL = String.format("select Gia_Ban from mat_hang where Ten_MH = '%s';", Sale_Data.name);
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    int Gia_Ban = rs.getInt("Gia_Ban");
                    Sale_Data.sum = Sale_Data.sum + Gia_Ban * Sale_Data.so_luong;
                }
                System.out.print("tiep tu nhap mat hang ? (y/n)");
                c = nhap.nextLine();
            } while (c.equals("y"));
            System.out.println("so tien can tra:" + Sale_Data.sum+"VNĐ");
			connect.close();
			st.close();
			rs.close();
	}catch(SQLException ex){
		System.out.println(ex);
	}
}
}
//tạo dữ liệu cho các mặt hàng
