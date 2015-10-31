package ProcessDatabases;

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

public class CreateDatabase {

	private static final String url = "jdbc:mysql://localhost";
	public static java.sql.Connection con = null;
	private static Statement st;
	public static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static String use;
	private static String pass;
	private static int checkk = 0;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public CreateDatabase() {
		initialize();
	}

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
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//
		}
		return 0;
	}
	public Connection getConnection (){
		return con;
	}
}
