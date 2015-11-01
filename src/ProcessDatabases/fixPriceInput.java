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

public class fixPriceInput {

	private JFrame frame;
	private JTextField textField;

	private static String sql;
	private static Statement st;
	private static ResultSet rs;
	private static String newIn;
	private static String idP;
	private static String a = "0";
	
	public fixPriceInput(Statement sts, String ids, String newIns) {
		st = sts;
		newIn = newIns;
		idP = ids;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.setResizable(false);
		frame.setBounds(100, 100, 316, 155);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChnMNh = new JLabel("Ch\u1ECDn m\u00E3 nh\u00E0 cung c\u1EA5p :");
		lblChnMNh.setBounds(25, 28, 154, 14);
		frame.getContentPane().add(lblChnMNh);
		
		textField = new JTextField();
		textField.setBounds(180, 25, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ch\u1EC9nh s\u1EEDa");
		btnNewButton.setBounds(81, 71, 147, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickButtom();
			}
		});
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					clickButtom();
				}
			}
		});
	}
	private void clickButtom(){
		String temp = textField.getText();
		if(checkString(temp) == 0){
			JOptionPane.showMessageDialog(null, "Mã nhà sản xuất phải là 1 số!");
		}
		else{
			sql = "select * from cung_cap where ID_MatHang = '" + idP + "' and Id_NCC = '" + temp +"';";
			rs = null;
			try {
				rs = st.executeQuery(sql);
				rs.last();
				//System.out.print(rs.getRow());
				if(rs.getRow() == 0){
					JOptionPane.showMessageDialog(null, "Nhà cung cấp này không cung cấp mặt hàng trên!");						
				}	
				else{
					sql = "UPDATE cung_cap SET Gia_Nhap = '" + newIn + "' WHERE ID_MatHang = '" + idP + "' and Id_NCC = '" + temp + "';";
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Giá nhập đã được chỉnh sửa!");
					frame.setVisible(false);
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static int checkString(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) > '9' || s.charAt(j) < '0') return 0;
		}
		return 1;
	}
}
