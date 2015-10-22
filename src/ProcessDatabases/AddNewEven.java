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

public class AddNewEven {

	private JFrame frame;
	private JTextField textField;
	private static String nameS;
	private static String timeS;
	private static String timeE;
	private static int idS;
	private static String sql;
	private static Statement st;
	private static ResultSet rs;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public AddNewEven(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 411, 282);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Nhập thông tin sự kiện :");
		lblNewLabel.setBounds(10, 33, 227, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTnHngCung = new JLabel("Tên sự kiện:");
		lblTnHngCung.setBounds(29, 78, 131, 14);
		frame.getContentPane().add(lblTnHngCung);
		
		textField = new JTextField();
		textField.setBounds(151, 75, 213, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Thêm sự kiện");
		btnNewButton.setBounds(124, 191, 154, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblThiGianDin = new JLabel("Thời gian diễn ra");
		lblThiGianDin.setBounds(29, 112, 131, 14);
		frame.getContentPane().add(lblThiGianDin);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(151, 109, 213, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(151, 144, 213, 20);
		frame.getContentPane().add(textField_2);
		
		JLabel lblThiGianKt = new JLabel("Thời gian kết thúc");
		lblThiGianKt.setBounds(29, 147, 131, 14);
		frame.getContentPane().add(lblThiGianKt);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameS = textField.getText();
				timeS = textField_1.getText();
				timeE = textField_2.getText();
				idS = 0;
				if(nameS.length() == 0 || timeS.length() == 0 || timeE.length() == 0){
					JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
				}				
				else{
	
					try {						
						sql = "select ID_KM from khuyen_mai	 order by Id_KM ASC;";
						rs = st.executeQuery(sql);
						while( rs.next()) {
							idS = rs.getInt("ID_KM");
						}		
						idS = idS+1;
						//System.out.println("Đã nhận dc id = " + idS);
						sql = "INSERT INTO khuyen_mai(ID_KM, Ten_Nhomhang)VALUES ('" + idS + "','" + nameS + "');";
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
