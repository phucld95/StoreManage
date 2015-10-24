package ProcessDatabases;



import java.sql.*;

import java.util.*;


public class CreateDatabasesssssss {
	private static final CreateDatabasesssssss CreateDatabasessssssss = null;
	public static String nhapchu(){
		Scanner nhap=new Scanner(System.in);
		return nhap.nextLine();
	}
	public static void connectToDBMS(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Load JDBC Driver Complete ...\n");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection(String url,String username,String passwd){
		try {
			Connection connect= DriverManager.getConnection(url,username,passwd);
			System.out.println("thanh cong");
			return connect;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("xay ra loi.");
			return null;
		}		
	}
	
	public static void importdata(){
		String url = "jdbc:mysql://localhost";
		String username,passwd;
		System.out.print("Nhap username cua mysql: ");
		username = nhapchu();
		System.out.print("Nhap password cua mysql: ");
		passwd = nhapchu();
		System.out.println("ket noi den mysql.");
		//ResultSet result;
		Connection connect = getConnection(url,username,passwd);
		if(connect ==null){
			return;
		}
		try {
			Statement stmt = connect.createStatement();
			System.out.println("nhap ten database ");
			String dname = nhapchu();
			stmt.executeUpdate("create database if not exists "+dname);
			stmt.executeUpdate("use "+dname);
			stmt.executeUpdate("CREATE TABLE if not exists `khuyen_mai` (`Id_KM` int(11) NOT NULL,`Ten_KM` varchar(45) NOT NULL,`TGDR` date NOT NULL,`TGKT` date NOT NULL,PRIMARY KEY (`Id_KM`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			stmt.executeUpdate("CREATE TABLE if not exists `ncc`(`Id_NCC` int(11) NOT NULL,`Ten_NCC` varchar(45) NOT NULL,`SDT` int NOT NULL, `Mail` varchar(40) NOT NULL,	`TTLH` varchar(45) NOT NULL,PRIMARY KEY (`Id_NCC`,`Ten_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			stmt.executeUpdate("CREATE TABLE if not exists `nhomhang` (`ID_NhomHang` int(11) NOT NULL,`Ten_Nhomhang` varchar(60) NOT NULL,PRIMARY KEY (`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			stmt.executeUpdate("CREATE TABLE if not exists `mat_hang` ( `ID_MatHang` int(11) NOT NULL, `Gia_Nhap` int(11) NOT NULL, `Gia_Ban` int(11) NOT NULL,`Ten_MH` varchar(45) NOT NULL,`Soluong` int(11) NOT NULL,PRIMARY KEY (`ID_MatHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			stmt.executeUpdate("CREATE TABLE if not exists `cung_cap` (`ID_MatHang` int(11) NOT NULL,`Id_NCC` int(11) NOT NULL,PRIMARY KEY (`ID_MatHang`,`Id_NCC`), FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`Id_NCC`)REFERENCES `ncc`(`Id_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			stmt.executeUpdate("CREATE TABLE if not exists`thuoc_nhom` (`ID_MatHang` int(11) NOT NULL, `ID_NhomHang` int(11) NOT NULL, PRIMARY KEY (`ID_MatHang`,`ID_NhomHang`),FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`ID_NhomHang`) REFERENCES `nhomhang`(`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			stmt.executeUpdate("CREATE TABLE if not exists`duoc_khuyen_mai` ( `Id_KM` int(11) NOT NULL,`ID_MatHang` int(11) NOT NULL, `Gia_KM` int NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			stmt.executeUpdate("CREATE TABLE if not exists`account` (`ID_Account` int not null,`username` varchar(10) NOT NULL,`password` varchar(10) NOT NULL,PRIMARY KEY (`ID_Account`,`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			ResultSet result;
			result = stmt.executeQuery("select ID_Account from account;");
			
			if(result.next()==false){
				stmt.executeUpdate("INSERT INTO `account` VALUES('1','admin','admin123');");
			}
			System.out.println("hoan thanh");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		CreateDatabasessssssss.importdata();
	}
}
