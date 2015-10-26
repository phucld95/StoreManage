package ProcessDatabases;
import java.sql.*;
import java.util.*;
public class CreateDatabase {
	private static Statement st;
	private static String databaseName;
	private static ResultSet result;
	
	public CreateDatabase(Statement sts){
		st = sts;
		running();
	}
	
	public String setString(){
		Scanner nhap=new Scanner(System.in);
		return nhap.nextLine();
	}
	
	private void running(){
		System.out.print("Database : ");
		databaseName = setString();
		
		try {
			st.executeUpdate("CREATE DATABASE IF NOT EXISTS "+databaseName);
			st.executeUpdate("USE DATABASE"+databaseName);
			st.executeUpdate("CREATE TABLE if not exists `khuyen_mai` (`Id_KM` int(11) NOT NULL,`Ten_KM` varchar(45) NOT NULL,`TGDR` date NOT NULL,`TGKT` date NOT NULL,PRIMARY KEY (`Id_KM`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `ncc`(`Id_NCC` int(11) NOT NULL,`Ten_NCC` varchar(45) NOT NULL,`SDT` int NOT NULL, `Mail` varchar(40) NOT NULL,	`TTLH` varchar(45) NOT NULL,PRIMARY KEY (`Id_NCC`,`Ten_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `nhomhang` (`ID_NhomHang` int(11) NOT NULL,`Ten_Nhomhang` varchar(60) NOT NULL,PRIMARY KEY (`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `mat_hang` ( `ID_MatHang` int(11) NOT NULL, `Ten_MH` varchar(45) NOT NULL,`Soluong` int(11) NOT NULL,PRIMARY KEY (`ID_MatHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `cung_cap` (`ID_MatHang` int(11) NOT NULL,`Id_NCC` int(11) NOT NULL,`Gia_Nhap` int(11) NOT NULL, `Gia_Ban` int(11) NOT NULL,`So_Luong_Nhap` int (11) NOT NULL,PRIMARY KEY (`ID_MatHang`,`Id_NCC`), FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`Id_NCC`)REFERENCES `ncc`(`Id_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `thuoc_nhom` (`ID_MatHang` int(11) NOT NULL, `ID_NhomHang` int(11) NOT NULL, PRIMARY KEY (`ID_MatHang`,`ID_NhomHang`),FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`ID_NhomHang`) REFERENCES `nhomhang`(`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `duoc_khuyen_mai` ( `Id_KM` int(11) NOT NULL,`ID_MatHang` int(11) NOT NULL, `Gia_KM` int NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `account` (`ID_Account` int not null,`username` varchar(10) NOT NULL,`password` varchar(10) NOT NULL,PRIMARY KEY (`ID_Account`,`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `hoa_don` (`ID_HoaDon` int not null AUTO_INCREMENT,`Noi_Dung` varchar(100) not null,`Tong_Tien` int not null,`ID_ThuNgan` int not null,`Thoi_Gian` datetime not null,PRIMARY KEY (`ID_Hoa_Don`),FOREIGN KEY (`ID_ThuNgan`) REFERENCES `account`(`ID_Account`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `nhap_hang` (`ID_NhanVien` int not null ,`Thoi_Gian` datetime not null, FOREIGN KEY (`ID_NhanVien`) REFERENCES `account`(`ID_Account`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			result = st.executeQuery("select ID_Account from account;");
			if(result.next()==false){
				st.executeUpdate("INSERT INTO `account` VALUES('1','admin','admin123');");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
