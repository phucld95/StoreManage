package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
		JLabel lblNhpThngTin = new JLabel("Nh\u1EADp th\u00F4ng tin c\u01A1 s\u1EDF d\u1EEF li\u1EC7u :");
		lblNhpThngTin.setBounds(22, 21, 229, 14);
		frame.getContentPane().add(lblNhpThngTin);
		
		JLabel lblTnQuyn = new JLabel("T\u00EAn ng\u01B0\u1EDDi d\u00F9ng mysql :");
		lblTnQuyn.setBounds(48, 46, 160, 14);
		frame.getContentPane().add(lblTnQuyn);
		
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
						JOptionPane.showMessageDialog(null, "Không kết nối được đến tài khoản mysql!\nHãy chắc chắn điền đúng tên người dùng và mật khẩu!");
					}
				}
			}
		});
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
