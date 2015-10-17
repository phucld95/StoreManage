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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddProductToGroup {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
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
	public AddProductToGroup(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 544, 264);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		
		JLabel lblThmSnPhm = new JLabel("Th\u00EAm s\u1EA3n ph\u1EA9m v\u00E0o nh\u00F3m h\u00E0ng :");
		lblThmSnPhm.setBounds(25, 23, 240, 14);
		frame.getContentPane().add(lblThmSnPhm);
		
		JLabel lblChnNhmHng = new JLabel("Ch\u1ECDn nh\u00F3m h\u00E0ng: ");
		lblChnNhmHng.setBounds(49, 50, 130, 14);
		frame.getContentPane().add(lblChnNhmHng);
		
		JLabel lblHocNhpM = new JLabel("Ho\u1EB7c nh\u1EADp m\u00E3 nh\u00F3m h\u00E0ng :");
		lblHocNhpM.setBounds(49, 79, 166, 14);
		frame.getContentPane().add(lblHocNhpM);
		
		JLabel lblThmSnPhm_1 = new JLabel("Th\u00EAm m\u00E3 s\u1EA3n ph\u1EA9m v\u00E0o nh\u00F3m :");
		lblThmSnPhm_1.setBounds(49, 113, 172, 14);
		frame.getContentPane().add(lblThmSnPhm_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(240, 47, 233, 20);
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
		
		textField = new JTextField();
		textField.setBounds(240, 79, 233, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(240, 107, 233, 20);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Th\u00EAm s\u1EA3n ph\u1EA9m");
		btnNewButton.setBounds(160, 159, 192, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idP = idG = temp = "";
				idG = textField.getText();
				idP = textField_1.getText();
				
				if(idG.length() == 0){
					nameG = comboBox.getSelectedItem().toString();
				}
				if(idP.length() == 0){
					JOptionPane.showMessageDialog(null, "Mã mặt hàng không được để trống!");
				}
				//System.out.println(nameG);
				try {
					sql = "select Ten_MH from mat_hang where ID_MatHang = '" + idP + "';";
					rs = st.executeQuery(sql);
					while (rs.next()){
						temp = rs.getString("Ten_MH");
					}
					
					if(temp.length() == 0){
						JOptionPane.showMessageDialog(null, "Không có mặt hàng có tên cần thêm!");
					}
					else{
						//
						try {						
							sql = "select ID_NhomHang from nhomhang where Ten_Nhomhang = '" + nameG + "';";
							rs = st.executeQuery(sql);
							
							while (rs.next()){
								idG = rs.getString("ID_NhomHang");
								//System.out.println(idG);
							}
							//System.out.println(idP);
							//System.out.println(idG);
							sql = "INSERT INTO thuoc_nhom(ID_MatHang, ID_NhomHang)VALUES ('" + idP + "','" + idG +"');";
							st.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Mặt hàng đã được thêm vào nhóm!");
							
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
