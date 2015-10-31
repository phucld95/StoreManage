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

public class DeleteSupply {

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
	public DeleteSupply(Statement sts) {
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
		frame.setTitle("Xóa 1 nhà cung cấp.");
		
		JLabel lblXaMt = new JLabel("Xóa thông tin 1 nhà cung cấp.");
		lblXaMt.setBounds(25, 22, 291, 14);
		frame.getContentPane().add(lblXaMt);
		
		JLabel lblNhpTnSn = new JLabel("Nhập tên NCC :");
		lblNhpTnSn.setBounds(43, 55, 115, 14);
		frame.getContentPane().add(lblNhpTnSn);
		
		textField = new JTextField();
		textField.setBounds(167, 52, 189, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNhpMSn = new JLabel("Nhập mã số NCC :");
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
            	
            	if(name.length() != 0){
            	
            		sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc where Ten_NCC = '" + name + "';";
            		
					try {
						rs = st.executeQuery(sql);
						//System.out.println("Qua");
						rs.last();
						
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có nhà cung cấp có tên cần tìm!");
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
            	else if(id.length() != 0){
            		sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc where Ten_NCC = '" + id + "';";
            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có sản phẩm có mã cần tìm!");
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
            	if(id.length() == 0 && name.length() == 0){
            		JOptionPane.showMessageDialog(null, "Hãy nhập tên nhà cung cấp hoặc mã nhà cung cấp trước khi bấm kiểm tra!");
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
            	
            	if(id.length() != 0){
            		sql = "Select Id_NCC, Ten_NCC, TTLH from ncc where Id_NCC = '" + id + "';";
            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có nhà sản xuất có mã cần tìm!");
						}
						else{
							rs.beforeFirst();
							sql = "DELETE FROM ncc WHERE Id_NCC = '"+ id +"';";
							st.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Đã xóa thành công mặt hàng!!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
            	}
            	if(id.length() == 0){
            		JOptionPane.showMessageDialog(null, "Hãy nhập mã sản phẩm trước khi bấm xóa!");
            	}
            }
        });
	}

}
