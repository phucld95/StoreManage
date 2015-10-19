package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddNewAccount {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static String nameS;
	private static String addressS;
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
	public AddNewAccount(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 411, 213);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Nhập thông tin người dùng :");
		lblNewLabel.setBounds(10, 23, 227, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTnHngCung = new JLabel("Tài khoản người dùng :");
		lblTnHngCung.setBounds(31, 53, 131, 14);
		frame.getContentPane().add(lblTnHngCung);
		
		JLabel lblaChLin = new JLabel("Mã bảo mật :");
		lblaChLin.setBounds(31, 83, 131, 14);
		frame.getContentPane().add(lblaChLin);
		
		textField = new JTextField();
		textField.setBounds(151, 47, 213, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(151, 77, 213, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Thêm tài khoản");
		btnNewButton.setBounds(122, 119, 154, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameS = textField.getText();
				addressS = textField_1.getText();
				idS = 0;
				if(nameS.length() == 0 || addressS.length() == 0){
					JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
				}				
				else{
	
					try {						
						sql = "select STT from account order by STT ASC;";
						rs = st.executeQuery(sql);
						while( rs.next()) {
							idS = rs.getInt("STT");
						}		
						idS = idS+1;
						//System.out.println("Đã nhận dc id = " + idS);
						sql = "INSERT INTO account(STT, username, password)VALUES ('" + idS + "','" + nameS + "','" + addressS + "');";
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Người dùng đã được thêm!");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

}
