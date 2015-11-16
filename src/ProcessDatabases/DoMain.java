package ProcessDatabases;

import java.sql.*;
import java.util.Formatter;
import java.util.Scanner;


public class DoMain {
	private static final String url = "jdbc:mysql://localhost";
	private static String user = ""; 
	private static String password = "";
	private static java.sql.Connection con;
	private static Statement st;
	private static ResultSet result;
	private static int i;
	private static String dname = "ManageDatabase";
	
	
	public static void main(String args[]) {
		CreateDatabase cd = new CreateDatabase();
    	cd.frame.setVisible(true);
    	while(cd.check() == 0);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
        	user = cd.getUser();
        	password = cd.getPassword();
        	con = DriverManager.getConnection(url, user, password);
        	//System.out.print("Qua 2.");
        	st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	//System.out.print("Qua 3.");
        	st.executeUpdate ("Use " + dname +" ;");     	
        }
		catch(Exception e){
			System.out.print("Chưa tồn tại database!");
			try {
				st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				importdata();
				i = st.executeUpdate ("Use " + dname +" ;");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		run();
	}
	
	public static void run(){
		Layer1Interface ly1 = new Layer1Interface(st);
        ly1.frame.setVisible(true);        
	}
	
	
	public static void importdata(){
		try {
			st.executeUpdate("CREATE DATABASE IF NOT EXISTS "+dname + ";");
			st.executeUpdate("USE "+dname+";");
			st.executeUpdate("CREATE TABLE if not exists `khuyen_mai` (`Id_KM` int(11) NOT NULL,`Ten_KM` varchar(45) NOT NULL,`TGDR` date NOT NULL,`TGKT` date NOT NULL,PRIMARY KEY (`Id_KM`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `ncc`(`Id_NCC` int(11) NOT NULL,`Ten_NCC` varchar(45) NOT NULL,`SDT` int NOT NULL, `Mail` varchar(40) NOT NULL,	`TTLH` varchar(45) NOT NULL,PRIMARY KEY (`Id_NCC`,`Ten_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `nhomhang` (`ID_NhomHang` int(11) NOT NULL,`Ten_Nhomhang` varchar(60) NOT NULL,PRIMARY KEY (`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `mat_hang` ( `ID_MatHang` int(11) NOT NULL, `Ten_MH` varchar(45) NOT NULL, `Gia_Ban` int(11) NOT NULL,`Soluong` int(11) NOT NULL,PRIMARY KEY (`ID_MatHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `cung_cap` (`ID_MatHang` int(11) NOT NULL,`Id_NCC` int(11) NOT NULL,`Gia_Nhap` int(11) NOT NULL,PRIMARY KEY (`ID_MatHang`,`Id_NCC`), FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`Id_NCC`)REFERENCES `ncc`(`Id_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `thuoc_nhom` (`ID_MatHang` int(11) NOT NULL, `ID_NhomHang` int(11) NOT NULL, PRIMARY KEY (`ID_MatHang`,`ID_NhomHang`),FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`ID_NhomHang`) REFERENCES `nhomhang`(`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `duoc_khuyen_mai` ( `Id_KM` int(11) NOT NULL,`ID_MatHang` int(11) NOT NULL, `Gia_KM` int NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `account` (`ID_Account` int not null,`username` varchar(10) NOT NULL,`password` varchar(10) NOT NULL,`tenNV` varchar(30) not null,`SDT` int(10),`Dia_Chi` varchar(60), PRIMARY KEY (`ID_Account`,`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `hoa_don` (`ID_HoaDon` int not null AUTO_INCREMENT,`Noi_Dung` varchar(100) not null,`Tong_Tien` int not null,`ID_ThuNgan` int not null,`Thoi_Gian` datetime not null,PRIMARY KEY (`ID_HoaDon`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `nhap_hang` (`ID_NhanVien` int not null ,`Thoi_Gian` datetime not null) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			ResultSet rs = st.executeQuery("select ID_Account from account ;");
			if(rs.next()==false){
				st.executeUpdate("INSERT INTO `account`(`ID_Account`,`username`,`password`,`tenNV`) VALUES('1','admin','admin123','taikhoanadmin');");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}