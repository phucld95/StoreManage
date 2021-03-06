package ProcessDatabases;

import java.awt.Toolkit;
import java.io.*;
import java.net.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerApp {
	public final static int DEFAULT_PORT = 5000;
	public static String clientSentence;
	public static String capitalizedSentence;
	
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
    	//System.out.println("1");
		try {
			//System.out.println("1");
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
				
		            // The newInstance() call is a work around for some
		            // broken Java implementations

		            Class.forName("com.mysql.jdbc.Driver").newInstance();
		        
				st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				importdata();
				i = st.executeUpdate ("Use " + dname +" ;");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	try {
    	con = DriverManager.getConnection(url, user, password);
        System.out.println("Connect Success!");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.executeUpdate ("Use managedatabase;");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	startServer();
}
	public static void importdata(){
		try {
			st.executeUpdate("CREATE DATABASE IF NOT EXISTS "+dname + ";");
			st.executeUpdate("USE "+dname+";");
			st.executeUpdate("CREATE TABLE if not exists `khuyen_mai` (`Id_KM` int(11)  ,`Ten_KM` varchar(45)  ,`TGDR` date  ,`TGKT` date  ,PRIMARY KEY (`Id_KM`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `ncc`(`Id_NCC` int(11)  ,`Ten_NCC` varchar(45)  ,`SDT` int  , `Mail` varchar(40)  ,	`TTLH` varchar(45)  ,PRIMARY KEY (`Id_NCC`,`Ten_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `nhomhang` (`ID_NhomHang` int(11)  ,`Ten_Nhomhang` varchar(60)  ,PRIMARY KEY (`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `mat_hang` ( `ID_MatHang` int(11)  , `Ten_MH` varchar(45)  , `Gia_Ban` int(11)  ,`Soluong` int(11)  ,PRIMARY KEY (`ID_MatHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `cung_cap` (`ID_MatHang` int(11)  ,`Id_NCC` int(11)  ,`Gia_Nhap` int(11)  ,PRIMARY KEY (`ID_MatHang`,`Id_NCC`), FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`Id_NCC`)REFERENCES `ncc`(`Id_NCC`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `thuoc_nhom` (`ID_MatHang` int(11)  , `ID_NhomHang` int(11)  , PRIMARY KEY (`ID_MatHang`,`ID_NhomHang`),FOREIGN KEY(`ID_MatHang`) REFERENCES `mat_hang`(`ID_MatHang`),FOREIGN KEY(`ID_NhomHang`) REFERENCES `nhomhang`(`ID_NhomHang`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `duoc_khuyen_mai` ( `Id_KM` int(11)  ,`ID_MatHang` int(11)  , `Gia_KM` int  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `account` (`ID_Account` int  ,`username` varchar(10)  ,`password` varchar(10)  ,`tenNV` varchar(30)  ,`SDT` int(10),`Dia_Chi` varchar(60), PRIMARY KEY (`ID_Account`,`username`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `hoa_don` (`ID_HoaDon` int NOT NULL  AUTO_INCREMENT,`Noi_Dung` varchar(100)  ,`Tong_Tien` int  ,`ID_ThuNgan` int  ,`Thoi_Gian` datetime  ,PRIMARY KEY (`ID_HoaDon`),FOREIGN KEY (`ID_ThuNgan`) REFERENCES `account`(`ID_Account`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			st.executeUpdate("CREATE TABLE if not exists `nhap_hang` (`ID_NhanVien` int   ,`Thoi_Gian` datetime  , FOREIGN KEY (`ID_NhanVien`) REFERENCES `account`(`ID_Account`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			ResultSet rs = st.executeQuery("select ID_Account from account ;");
			if(rs.next()==false){
				st.executeUpdate("INSERT INTO `account`(`ID_Account`,`username`,`password`,`tenNV`) VALUES('1','admin','admin123','taikhoanadmin');");
			}
			st.executeUpdate("DELIMITER $$");
			st.executeUpdate("create trigger bf_khuyenmai_update before insert on khuyen_mai for each row begin if(not dayofmonth(new.TGDR) or not dayofmonth(new.TGKT)or new.TGKT is NULL or new.TGDR  is NULL  or new.TGKT <= new.TGDR or new.Id_KM <=0 or new.Ten_KM is NULL) then SIGNAL sqlstate '45001'; end if; end $$");
			st.executeUpdate("CREATE TRIGGER bf_ncc_update BEFORE INSERT ON ncc FOR EACH ROW BEGIN  IF (new.Id_NCC IS NULL OR new.Ten_NCC IS NULL OR new.SDT IS NULL OR new.Mail IS NULL OR new.Mail NOT LIKE '%@%.com%' OR new.TTLH IS NULL OR new.Id_NCC <=0) THEN SIGNAL SQLSTATE '45001'; END IF; END $$");
			st.executeUpdate("CREATE TRIGGER bf_nhomhang_update BEFORE INSERT ON nhomhang FOR EACH ROW BEGIN  IF (new.ID_NhomHang IS NULL OR new.ID_NhomHang <= 0 OR new.Ten_NhomHang IS NULL) THEN SIGNAL SQLSTATE '45001'; END IF; END $$");
			st.executeUpdate("CREATE TRIGGER bf_mathang_update BEFORE INSERT ON mat_hang FOR EACH ROW BEGIN IF(new.ID_MatHang IS NULL OR new.Gia_Ban IS NULL OR new.SoLuong IS NULL OR new.ID_Mathang <= 0 OR new.Ten_MH IS NULL OR new.Gia_Ban <=1000 OR new.Soluong <=0) THEN SIGNAL SQLSTATE '45001'; END IF; END $$");
			st.executeUpdate("CREATE TRIGGER bf_cungcap_update  BEFORE INSERT ON cung_cap FOR EACH ROW BEGIN IF(NOT new.ID_MatHang OR NOT new.Id_NCC OR  new.Gia_Nhap IS NULL OR new.Gia_Nhap < 1000) THEN SIGNAL SQLSTATE '45001'; END IF; END $$");
			st.executeUpdate("CREATE TRIGGER bf_thuocnhom_update BEFORE INSERT ON thuoc_nhom FOR EACH ROW BEGIN IF(new.ID_MatHang IS NULL OR new.ID_NhomHang IS NULL) THEN SIGNAL SQLSTATE '45001'; END IF; END $$");
			st.executeUpdate("CREATE TRIGGER bf_duockhuyenmai_update BEFORE INSERT ON duoc_khuyen_mai FOR EACH ROW BEGIN IF(new.Id_KM IS NULL OR new.ID_MatHang IS NULL OR new.Gia_KM IS NULL OR new.Gia_KM <1000) THEN SIGNAL SQLSTATE '45001'; END IF; END $$");
			st.executeUpdate("CREATE TRIGGER bf_account_update BEFORE INSERT ON account FOR EACH ROW BEGIN IF(new.ID_Account IS NULL OR new.ID_Account <=0 OR new.username IS NULL OR new.password IS NULL OR new.tenNV IS NULL OR new.SDT IS NULL OR new.Dia_Chi IS NULL) THEN SIGNAL SQLSTATE '45001'; END IF; IF(length(new.username) <6 OR length(new.password) <6) THEN SIGNAL SQLSTATE '45001'; END IF; END $$");
			st.executeUpdate("CREATE TRIGGER bf_hoadon_update BEFORE INSERT ON hoa_don FOR EACH ROW BEGIN IF(new.Noi_Dung IS NULL OR new.Tong_Tien IS NULL OR new.ID_ThuNgan IS NULL OR new.Thoi_Gian IS NULL) THEN SIGNAL SQLSTATE '45001'; END IF; END $$");
			st.executeUpdate("DELIMITER ;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public static void startServer() {	
		theme window = new theme();
		window.frame.setVisible(true);
		try(ServerSocket servSocket = new ServerSocket(DEFAULT_PORT)){
			while (true){
				Socket connSocket = servSocket.accept();
				System.out.println("Accepted client:" + connSocket.getInetAddress().getHostAddress());
				try(BufferedReader in = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(connSocket.getOutputStream()))){
					String message;
					while((message = in.readLine()) != null){
					System.out.println("Receive from client:" + message);
					message = getDataInMySQL(message);
					out.println(message);
					out.flush();
				}
				System.out.println("Client has stopped sending data!");
				}
				catch (IOException e){
					System.out.println(e.getMessage());
				}
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static String getDataInMySQL(String sql){
		String str = new String();
		String temp = new String();
		ResultSet rs;
		try {
			// Lấy dữ liệu từ csdl.
			rs = null;
			str = "P";
			if(sql.indexOf("select") >-1){
				rs = st.executeQuery(sql);
			}
			else if(sql.indexOf("Select") >-1){
				rs = st.executeQuery(sql);
			}
			else if(sql.indexOf("SELECT") >-1){
				rs = st.executeQuery(sql);
			}
			else{
				st.executeUpdate(sql);
				return str;
			}		
			str = processData(rs);
			return str;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	private static String processData(ResultSet rs){
		String str = new String();
		String temp = new String();
		// Đọc metaData của resultSet.
		ResultSetMetaData rsMeta;
		try {
			rsMeta = rs.getMetaData();
			// Lấy số cột của bảng.
			int columpCount = rsMeta.getColumnCount();
			// Lặp để lấy dữ liệu sang chuỗi.
			while (rs.next()){
				for (int i = 1; i <= columpCount; i++) {
					//System.out.println(rsMeta.getColumnName(i));
					temp = rs.getString(rsMeta.getColumnName(i));
					str = str + temp + "|";
				}
				str = str + "||";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return str;
	}
}



