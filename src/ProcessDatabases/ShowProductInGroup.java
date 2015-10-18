package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ShowProductInGroup {

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
	public ShowProductInGroup(Statement sts) {
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
		
		JLabel lblNhmHngCn = new JLabel("Ch\u1ECDn nh\u00F3m h\u00E0ng c\u1EA7n xem :");
		lblNhmHngCn.setBounds(23, 23, 148, 14);
		frame.getContentPane().add(lblNhmHngCn);
		
		JLabel lblHocNhpM = new JLabel("Ho\u1EB7c nh\u1EADp m\u00E3 nh\u00F3m h\u00E0ng :");
		lblHocNhpM.setBounds(23, 51, 148, 14);
		frame.getContentPane().add(lblHocNhpM);
		
		textField = new JTextField();
		textField.setBounds(198, 48, 199, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(198, 20, 199, 20);
		frame.getContentPane().add(comboBox);
		sql = "select Ten_Nhomhang from nhomhang";
		try {
			rs = st.executeQuery(sql);
			while( rs.next()) {
				comboBox.addItem( rs.getString("Ten_Nhomhang"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton btnNewButton = new JButton("Xem\r\n");
		btnNewButton.setBounds(161, 92, 111, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idP = idG = temp = "";
				idG = textField.getText();
				
				if(idG.length() == 0){
					nameG = comboBox.getSelectedItem().toString();
					sql = "select ID_NhomHang from nhomhang where Ten_Nhomhang = '" + nameG + "';";
					try {
						rs = st.executeQuery(sql);
						while( rs.next()) {
							idG = rs.getString("ID_NhomHang");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				//System.out.println(nameG);
				
					try {
						sql = "select Ten_Nhomhang from nhomhang where ID_NhomHang = '" + idG + "';";
						rs = st.executeQuery(sql);
						while (rs.next()){
							temp = rs.getString("Ten_Nhomhang");
						}
						
						if(temp.length() == 0){
							JOptionPane.showMessageDialog(null, "Không có nhóm hàng có mã cần xem!");
						}
						else{
							//
							try {						
								sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and tn.ID_NhomHang = '" + idG + "';";
								rs = st.executeQuery(sql);
								TableDatabase stt = new TableDatabase(rs);
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
