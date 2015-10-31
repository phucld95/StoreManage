package ProcessDatabases;
<<<<<<< HEAD

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

=======
import java.sql.*;
import java.util.*;
>>>>>>> 2f26827b896b4331fca707dfcb43dc83f49f8d49
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
<<<<<<< HEAD

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 211);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		frame.setTitle("Đăng nhập tài khoản MySQL");
		frame.setResizable(false);
		
		JLabel lblNhpThngTin = new JLabel("Nh\u1EADp th\u00F4ng tin c\u01A1 s\u1EDF d\u1EEF li\u1EC7u :");
		lblNhpThngTin.setBounds(22, 21, 229, 14);
		frame.getContentPane().add(lblNhpThngTin);
		
		JLabel lblTnQuyn = new JLabel("Tài khoản mysql :");
		lblTnQuyn.setBounds(48, 46, 160, 14);
		frame.getContentPane().add(lblTnQuyn);
		
		JLabel lblCh = new JLabel("Ch\u00FA \u00FD : Ph\u1EA3i c\u00E0i \u0111\u1EB7t ph\u1EA7n m\u1EC1m MySQL tr\u01B0\u1EDBc khi s\u1EED d\u1EE5ng ch\u01B0\u01A1ng tr\u00ECnh!");
		lblCh.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblCh.setBounds(24, 143, 397, 21);
		frame.getContentPane().add(lblCh);
		
		JLabel lblMBoMt = new JLabel("M\u00E3 b\u1EA3o m\u1EADt :");
		lblMBoMt.setBounds(48, 76, 160, 14);
		frame.getContentPane().add(lblMBoMt);
		
		textField = new JTextField();
		textField.setBounds(175, 40, 200, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(175, 70, 200, 20);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("X\u00E1c nh\u1EADn");
		btnNewButton.setBounds(124, 113, 200, 20);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					use = textField.getText();
					pass = textField_1.getText();
					
					if(use.length() == 0 || pass.length() == 0){
						JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
					}				
					else{
						if(check() == 1){
							frame.setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null, "Không kết nối được đến tài khoản MySQL!\nHãy chắc chắn điền đúng tên người dùng và mật khẩu!");
						}
					}
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				use = textField.getText();
				pass = textField_1.getText();
				
				if(use.length() == 0 || pass.length() == 0){
					JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
				}				
				else{
					if(check() == 1){
						frame.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "Không kết nối được đến tài khoản MySQL!\nHãy chắc chắn điền đúng tên người dùng và mật khẩu!");
					}
				}
			}
		});
	}
	
	public static String getUser(){
		return use;
	}
	
	public static String getPassword(){
		return pass;
	}
	public static int check (){
		try {						
			con = DriverManager.getConnection(url, use, pass);
			if(con != null){
				return 1;
			}
		
=======
	
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
>>>>>>> 2f26827b896b4331fca707dfcb43dc83f49f8d49
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
