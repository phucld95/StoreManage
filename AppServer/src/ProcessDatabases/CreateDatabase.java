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
import javax.swing.ImageIcon;
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
		
		JLabel lblNhpThngTin = new JLabel("Nhập thông tin cơ sở dữ liệu :");
		lblNhpThngTin.setBounds(22, 21, 229, 14);
		frame.getContentPane().add(lblNhpThngTin);
		
		JLabel lblTnQuyn = new JLabel("Tài khoản MySQL :");
		lblTnQuyn.setBounds(48, 46, 160, 14);
		frame.getContentPane().add(lblTnQuyn);
		
		JLabel lblCh = new JLabel("Chú ý : Phải cài đặt phần mềm MySQL trước khi sử dụng chương trình!");
		lblCh.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblCh.setBounds(24, 143, 397, 21);
		frame.getContentPane().add(lblCh);
		
		JLabel lblMBoMt = new JLabel("Mã bảo mật :");
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
		
		JButton btnNewButton = new JButton("Xác nhận");
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
							JOptionPane.showMessageDialog(null, "Tài khoản chưa tồn tại hoặc mật khẩu sai!");
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
						JOptionPane.showMessageDialog(null, "Tài khoản chưa tồn tại hoặc mật khẩu sai!");
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
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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