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

public class AddNewProductGroup {

	private JFrame frame;
	private JTextField textField;
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
	public AddNewProductGroup(Statement sts) {
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
		
		JLabel lblNewLabel = new JLabel("Nhập thông tin nhóm :");
		lblNewLabel.setBounds(10, 33, 227, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTnHngCung = new JLabel("Tên nhóm hàng:");
		lblTnHngCung.setBounds(29, 78, 131, 14);
		frame.getContentPane().add(lblTnHngCung);
		
		textField = new JTextField();
		textField.setBounds(151, 75, 213, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Th\u00EAm nh\u00E0 cung \u1EE9ng");
		btnNewButton.setBounds(122, 119, 154, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameS = textField.getText();
				//addressS = textField_1.getText();
				idS = 0;
				if(nameS.length() == 0){
					JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
				}				
				else{
	
					try {						
						sql = "select ID_NhomHang from nhomhang	 order by Id_NhomHang ASC;";
						rs = st.executeQuery(sql);
						while( rs.next()) {
							idS = rs.getInt("ID_NhomHang");
						}		
						idS = idS+1;
						//System.out.println("Đã nhận dc id = " + idS);
						sql = "INSERT INTO nhomhang(ID_NhomHang, Ten_Nhomhang)VALUES ('" + idS + "','" + nameS + "');";
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Nhóm hàng đã được thêm!");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

}
