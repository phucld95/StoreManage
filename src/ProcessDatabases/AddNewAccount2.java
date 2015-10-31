package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddNewAccount2 {

	private JFrame frmThmTiKhon;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private static String name;
	private static String pass;
	private static String nameUser;
	private static String sdt;
	private static String address;
	private static int idS;
	private static String sql;
	private static Statement st;
	private static ResultSet rs;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public AddNewAccount2(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThmTiKhon = new JFrame();
		frmThmTiKhon.setTitle("Th\u00EAm t\u00E0i kho\u1EA3n.");
		frmThmTiKhon.setResizable(false);
		frmThmTiKhon.setBounds(100, 100, 450, 300);
		frmThmTiKhon.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmThmTiKhon.getContentPane().setLayout(null);
		frmThmTiKhon.setVisible(true);
		
		JLabel lblNhpThngTin = new JLabel("Nh\u1EADp th\u00F4ng tin t\u00E0i kho\u1EA3n mu\u1ED1n th\u00EAm :");
		lblNhpThngTin.setBounds(24, 23, 244, 14);
		frmThmTiKhon.getContentPane().add(lblNhpThngTin);
		
		JLabel lblTnTiKhon = new JLabel("T\u00EAn t\u00E0i kho\u1EA3n :");
		lblTnTiKhon.setBounds(45, 50, 96, 14);
		frmThmTiKhon.getContentPane().add(lblTnTiKhon);
		
		textField = new JTextField();
		textField.setBounds(171, 47, 222, 20);
		frmThmTiKhon.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblMBoMt = new JLabel("M\u00E3 b\u1EA3o m\u1EADt :");
		lblMBoMt.setBounds(45, 80, 96, 14);
		frmThmTiKhon.getContentPane().add(lblMBoMt);
		
		textField_1 = new JTextField();
		textField_1.setBounds(171, 74, 222, 20);
		frmThmTiKhon.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTnNgiDng = new JLabel("T\u00EAn ng\u01B0\u1EDDi d\u00F9ng :");
		lblTnNgiDng.setBounds(45, 110, 96, 14);
		frmThmTiKhon.getContentPane().add(lblTnNgiDng);
		
		textField_2 = new JTextField();
		textField_2.setBounds(171, 104, 222, 20);
		frmThmTiKhon.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i :");
		lblSinThoi.setBounds(45, 140, 96, 14);
		frmThmTiKhon.getContentPane().add(lblSinThoi);
		
		textField_3 = new JTextField();
		textField_3.setBounds(171, 134, 222, 20);
		frmThmTiKhon.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblaChLin = new JLabel("\u0110\u1ECBa ch\u1EC9 li\u00EAn h\u1EC7 :");
		lblaChLin.setBounds(45, 169, 96, 14);
		frmThmTiKhon.getContentPane().add(lblaChLin);
		
		textField_4 = new JTextField();
		textField_4.setBounds(171, 163, 222, 20);
		frmThmTiKhon.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnThmTiKhon = new JButton("Th\u00EAm t\u00E0i kho\u1EA3n");
		btnThmTiKhon.setBounds(148, 214, 149, 23);
		frmThmTiKhon.getContentPane().add(btnThmTiKhon);
		JButton btnThmNhCung = new JButton("Th\u00EAm nh\u00E0 cung c\u1EA5p");
		btnThmTiKhon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickButtom();
			}
		});
		btnThmTiKhon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					clickButtom();
				}
			}
		});
	}
	
	private void clickButtom(){
		name = textField.getText();
		pass = textField_1.getText();
		nameUser = textField_2.getText();
		sdt = textField_3.getText();
		address = textField_4.getText();
		idS = 0;
		
		if(name.length() != 0){
			sql = "Select username, password from account where username = '" + name + "';";
			try {
				rs = null;
				rs = st.executeQuery(sql);
				rs.last();
				if(rs.getRow() != 0){
					JOptionPane.showMessageDialog(null, "Tên tài khoản đã được sử dụng!");
				}
				else{
					if(name.length() == 0 || address.length() == 0 || pass.length() == 0 || nameUser.length() == 0 || sdt.length() == 0 ){
						JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
					}				
					else if(checkString(sdt)== 0){
						JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ!");
					}
					else{
						try {					
							sql = "select ID_Account from account order by ID_Account ASC;";
							rs = st.executeQuery(sql);
							while( rs.next()) {
								idS = rs.getInt("ID_Account");
							}		
							idS = idS+1;
							//System.out.println("Đã nhận dc id = " + idS);
							sql = "INSERT INTO account(ID_Account, username, password, tenNV, SDT, Dia_Chi)VALUES ('" + idS + "','" + name + "','" + pass + "','" + nameUser + "','" + sdt + "','" + address + "');";
							st.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Người dùng đã được thêm!");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống!");
		}
	}
	
	public static int checkString(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) == '+') continue;
			if(s.charAt(j) > '9' || s.charAt(j) < '0') return 0;
		}
		return 1;
	}
}
