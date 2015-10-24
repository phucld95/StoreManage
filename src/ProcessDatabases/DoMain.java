package ProcessDatabases;

import java.sql.*;
import java.util.Formatter;
import java.util.Scanner;


public class DoMain {
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "123456";
	private static java.sql.Connection con;
	private static Statement st;
	private static ResultSet result;
	private static int i;
	private static String dname = "test3";
	
	
	public static void main(String args[]) {
        try {
        	CreateDatabase cd = new CreateDatabase();
        	cd.frame.setVisible(true);
        	while(cd.check() == 0);
        	//System.out.print("Qua");
        	con = DriverManager.getConnection(url, user, password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            i = st.executeUpdate ("Use test3;");
            run();	
        }
		catch(Exception e){
			System.out.print("Chưa tồn tại database!");
			importdata();
			try {
				i = st.executeUpdate ("Use test3;");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			run();
//			st.executeUpdate ("Use test3;");
			//e.printStackTrace();
		}
	}
	
	public static void run(){
		Layer1Interface ly1 = new Layer1Interface(st);
        ly1.frame.setVisible(true);
        while(ly1.check() == 0);
        ly1.frame.setVisible(false);
        if(ly1.check() == 2){
        	Layer2Interface window = new Layer2Interface(st);
			window.frmStoreManager.setVisible(true);
       	}
	}
	
	
	public static void importdata(){
		try {
			st.executeUpdate("create database if not exists "+dname);
			st.executeUpdate("use "+dname);
			st.executeUpdate("CREATE TABLE if not exists `khuyen_mai` (`Id_KM` int(11) NOT NULL,`Ten_KM` varchar(45) NOT NULL,`TGDR` date NOT NULL,`TGKT` date NOT NULL,PRIMARY KEY (`Id_KM`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `ncc`(`Id_NCC` int(11) NOT NULL,`Ten_NCC` varchar(45) NOT NULL,`SDT` int NOT NULL, `Mail` varchar(40) NOT NULL,	`TTLH` varchar(45) NOT NULL,PRIMARY KEY (`Id_NCC`,`Ten_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `nhomhang` (`ID_NhomHang` int(11) NOT NULL,`Ten_Nhomhang` varchar(60) NOT NULL,PRIMARY KEY (`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `mat_hang` ( `ID_MatHang` int(11) NOT NULL, `Gia_Nhap` int(11) NOT NULL, `Gia_Ban` int(11) NOT NULL,`Ten_MH` varchar(45) NOT NULL,`Soluong` int(11) NOT NULL,PRIMARY KEY (`ID_MatHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `cung_cap` (`ID_MatHang` int(11) NOT NULL,`Id_NCC` int(11) NOT NULL,PRIMARY KEY (`ID_MatHang`,`Id_NCC`), FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`Id_NCC`)REFERENCES `ncc`(`Id_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists`thuoc_nhom` (`ID_MatHang` int(11) NOT NULL, `ID_NhomHang` int(11) NOT NULL, PRIMARY KEY (`ID_MatHang`,`ID_NhomHang`),FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`ID_NhomHang`) REFERENCES `nhomhang`(`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists`duoc_khuyen_mai` ( `Id_KM` int(11) NOT NULL,`ID_MatHang` int(11) NOT NULL, `Gia_KM` int NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists`account` (`ID_Account` int not null,`username` varchar(10) NOT NULL,`password` varchar(10) NOT NULL,PRIMARY KEY (`ID_Account`,`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			
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