package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteEven {

	private JFrame frame;
	private JTextField textField;
	private static String sql;
	private static Statement st;
	private static ResultSet rs;
	private static String idG;
	private static String idP;
	private static String nameG;
	private static String temp;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public DeleteEven(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 181);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		frame.setTitle("Các sản phẩm khuyến mại.");
		frame.setResizable(false);
		
		JLabel lblNhmHngCn = new JLabel("Chọn sự kiện cần xem :");
		lblNhmHngCn.setBounds(23, 23, 148, 14);
		frame.getContentPane().add(lblNhmHngCn);
		
		JLabel lblHocNhpM = new JLabel("Hoặc nhập mã sự kiện :");
		lblHocNhpM.setBounds(23, 65, 148, 14);
		frame.getContentPane().add(lblHocNhpM);
		
		textField = new JTextField();
		textField.setBounds(198, 62, 199, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(198, 20, 199, 20);
		frame.getContentPane().add(comboBox);
		sql = "select Ten_KM from khuyen_mai";
		try {
			rs = st.executeQuery(sql);
			while( rs.next()) {
				comboBox.addItem( rs.getString("Ten_KM"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton btnNewButton = new JButton("Xóa");
		btnNewButton.setBounds(162, 104, 111, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idP = idG = temp = "";
				idG = textField.getText();
				
				if(idG.length() == 0){
					nameG = comboBox.getSelectedItem().toString();
					sql = "select ID_KM from khuyen_mai where Ten_KM = '" + nameG + "';";
					try {
						rs = st.executeQuery(sql);
						while( rs.next()) {
							idG = rs.getString("ID_KM");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				//System.out.println(nameG);
				
					try {
						sql = "select Ten_KM from khuyen_mai where ID_KM = '" + idG + "';";
						rs = st.executeQuery(sql);
						while (rs.next()){
							temp = rs.getString("Ten_KM");
						}
						
						if(temp.length() == 0){
							JOptionPane.showMessageDialog(null, "Không có đợt khuyến mại có mã cần xóa!");
						}
						else{
							//
							try {						
								sql = "DELETE FROM khuyen_mai WHERE Id_KM = '"+ idG +"';";
								st.executeUpdate(sql);
								sql = "DELETE FROM duoc_khuyen_mai WHERE Id_KM = '"+ idG +"';";
								st.executeUpdate(sql);
								JOptionPane.showMessageDialog(null, "Đã xóa thành công đợt khuyến mại!");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
	}

}
