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

public class DeleteAccount {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static String id;
	private static String name;
	private static String sql;
	private static ResultSet rs;
	private static Statement st;
	private static Statement st1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public DeleteAccount(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 192);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblXaMt = new JLabel("Xóa thông tin 1 tài khoản.");
		lblXaMt.setBounds(25, 22, 291, 14);
		frame.getContentPane().add(lblXaMt);
		
		JLabel lblNhpTnSn = new JLabel("Nhập tên tài khoản :");
		lblNhpTnSn.setBounds(43, 55, 115, 14);
		frame.getContentPane().add(lblNhpTnSn);
		
		textField = new JTextField();
		textField.setBounds(167, 52, 189, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNhpMSn = new JLabel("Nhập mã bảo mật :");
		lblNhpMSn.setBounds(43, 90, 115, 14);
		frame.getContentPane().add(lblNhpMSn);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 87, 189, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btSearch = new JButton("Ki\u1EC3m tra");
		btSearch.setBounds(390, 51, 102, 23);
		frame.getContentPane().add(btSearch);
		
		btSearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	name = textField.getText();
            	id = textField_1.getText();
            	
            	//System.out.println("|"+name+"|"+id+"|");
            	
            	if(name.length() != 0 && id.length() != 0){
            		sql = "Select STT, username, password from account where username = '" + name + "' and password = '" + id +"';";
            		
					try {
						rs = st.executeQuery(sql);
						//System.out.println("Qua");
						rs.last();
						
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có tài khoản giống cần tìm!");
						}
						else{
							rs.beforeFirst();
							TableDatabase stt = new TableDatabase(rs);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
            	}
            	else{
            		JOptionPane.showMessageDialog(null, "Hãy nhập đủ thông tin trước khi bấm kiểm tra!");
            	}
            }
        });
		
		
		JButton btnNewButton_1 = new JButton("X\u00F3a");
		btnNewButton_1.setBounds(390, 86, 102, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	name = textField.getText();
            	id = textField_1.getText();
            	
            	if(id.length() != 0 && name.length() != 0){
            		sql = "Select STT, username, password from account where username = '" + name + "' and password = '" + id +"';";
            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có tài khoản giống cần tìm!");
						}
						else{
							rs.beforeFirst();
							sql = "DELETE FROM account WHERE username = '"+ name +"' and password = '" + id + "';";
							st.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Đã xóa thành công tài khoản!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
            	}
            	if(id.length() == 0 || name.length() == 0){
            		JOptionPane.showMessageDialog(null, "Hãy nhập đủ thông tin trước khi bấm xóa!");
            	}
            }
        });
	}

}
