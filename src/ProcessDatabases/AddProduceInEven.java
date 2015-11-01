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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddProduceInEven {

	private JFrame frmThmSnPhm;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	
	private static String sql;
	private static Statement st;
	private static ResultSet rs;
	private static ResultSet rs2;
	private static String idG;
	private static String idP;
	private static String nameG;
	private static String temp;
	private static String Gia;
	
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AddProduceInEven(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThmSnPhm = new JFrame();
		frmThmSnPhm.setTitle("Th\u00EAm s\u1EA3n ph\u1EA9m v\u00E0o \u0111\u1EE3t khuy\u1EBFn m\u1EA1i.");
		frmThmSnPhm.setResizable(false);
		frmThmSnPhm.setVisible(true);
		frmThmSnPhm.setBounds(100, 100, 450, 256);

		frmThmSnPhm.getContentPane().setLayout(null);
		frmThmSnPhm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		
		JLabel lbltKhuynMi = new JLabel("\u0110\u1EE3t khuy\u1EBFn m\u1EA1i");
		lbltKhuynMi.setBounds(29, 30, 111, 22);
		frmThmSnPhm.getContentPane().add(lbltKhuynMi);
		
		comboBox = new JComboBox();
		comboBox.setBounds(170, 31, 247, 20);
		frmThmSnPhm.getContentPane().add(comboBox);
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
		
		JLabel lblTnSnPhm = new JLabel("T\u00EAn s\u1EA3n ph\u1EA9m :");
		lblTnSnPhm.setBounds(29, 77, 111, 14);
		frmThmSnPhm.getContentPane().add(lblTnSnPhm);
		
		textField = new JTextField();
		textField.setBounds(170, 69, 149, 22);
		frmThmSnPhm.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Xem m\u00E3");
		btnNewButton.setBounds(329, 69, 88, 22);
		frmThmSnPhm.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				temp = textField.getText();
				try {
					sql = "select ID_MatHang, Ten_MH, Gia_Ban from mat_hang where Ten_MH = '" + temp + "';";
					rs = null;
					rs = st.executeQuery(sql);
					TableDatabase stt = new TableDatabase(rs,"Các sản phẩm tìm được.");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		JLabel lblMSnPhm = new JLabel("M\u00E3 s\u1EA3n ph\u1EA9m :");
		lblMSnPhm.setBounds(29, 112, 111, 14);
		frmThmSnPhm.getContentPane().add(lblMSnPhm);
		
		textField_1 = new JTextField();
		textField_1.setBounds(170, 104, 247, 22);
		frmThmSnPhm.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Th\u00EAm s\u1EA3n ph\u1EA9m");
		btnNewButton_1.setBounds(157, 183, 162, 22);
		frmThmSnPhm.getContentPane().add(btnNewButton_1);
		
		JLabel lblGiKhuynMi = new JLabel("Giá khuyến mại :");
		lblGiKhuynMi.setBounds(29, 147, 130, 14);
		frmThmSnPhm.getContentPane().add(lblGiKhuynMi);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(170, 139, 247, 22);
		frmThmSnPhm.getContentPane().add(textField_2);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickButtom();
			}
		});
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					clickButtom();
				}
			}
		});
	}
	
	private void clickButtom(){
		idP = textField_1.getText();
		nameG = comboBox.getSelectedItem().toString();
		Gia = textField_2.getText();
		if(idP.length() == 0 || Gia.length() == 0){
			JOptionPane.showMessageDialog(null, "Phải điền đầy đủ thông tin!");
		}
		else{
			try {
				sql = "select ID_KM from khuyen_mai where Ten_KM = '" + nameG + "';";
				rs = st.executeQuery(sql);
				while( rs.next()) {
					idG = rs.getString("ID_KM");
				}
				sql = "select * from mat_hang where ID_MatHang = '" + idP +"';";
				//System.out.print(idP);
				rs = null;
				rs = st.executeQuery(sql);			
				
				//System.out.print(rs2.getInt("ID_MatHang"));
				rs.last();
				//System.out.print(rs2.getRow());
				if(rs.getRow() == 0){
					JOptionPane.showMessageDialog(null, "Không có sản phẩm có mã cần thêm!");
				}
				
				else{
					sql = "INSERT INTO duoc_khuyen_mai(Id_KM, ID_MatHang, Gia_KM)VALUES ('" + idG + "','" + idP + "','" + Gia + "');";
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm vào đợt khuyến mại!");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}
}
