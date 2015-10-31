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
import javax.swing.JButton;
import javax.swing.JTextField;

public class AddNewSupply2 {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private static String nameS;
	private static String addressS;
	private static String sdtS;
	private static String mailS;
	private static int idS;
	
	private static String sql;
	private static Statement st;
	private static ResultSet rs;
	
	
	

	/**
	 * Create the application.
	 */
	public AddNewSupply2(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 272);
		
		frame.getContentPane().setLayout(null);
		frame.setTitle("Thêm 1 nhà cung cấp.");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		
		JLabel lblThngTinNh = new JLabel("Th\u00F4ng tin nh\u00E0 cung c\u1EA5p m\u1EDBi :");
		lblThngTinNh.setBounds(24, 25, 185, 14);
		frame.getContentPane().add(lblThngTinNh);
		
		JLabel lblTnNhCung = new JLabel("T\u00EAn nh\u00E0 cung c\u1EA5p :");
		lblTnNhCung.setBounds(48, 50, 161, 14);
		frame.getContentPane().add(lblTnNhCung);
		
		JLabel lblaCh = new JLabel("\u0110\u1ECBa ch\u1EC9 li\u00EAn h\u1EC7 :");
		lblaCh.setBounds(48, 81, 103, 14);
		frame.getContentPane().add(lblaCh);
		
		JLabel lblSinThoi = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i li\u00EAn h\u1EC7 :");
		lblSinThoi.setBounds(48, 111, 120, 14);
		frame.getContentPane().add(lblSinThoi);
		
		JLabel lblMailLinH = new JLabel("Mail li\u00EAn h\u1EC7 :");
		lblMailLinH.setBounds(48, 140, 103, 14);
		frame.getContentPane().add(lblMailLinH);		
		
		textField_1 = new JTextField();
		textField_1.setBounds(179, 50, 197, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(179, 78, 197, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(178, 108, 198, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(179, 137, 197, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		

		JButton btnThmNhCung = new JButton("Th\u00EAm nh\u00E0 cung c\u1EA5p");
		btnThmNhCung.setBounds(142, 186, 174, 23);
		frame.getContentPane().add(btnThmNhCung);
		btnThmNhCung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickButtom();
			}
		});
		btnThmNhCung.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					clickButtom();
				}
			}
		});
	}
	
	private void clickButtom(){
		nameS = textField_1.getText();
		addressS = textField_2.getText();
		sdtS = textField_3.getText();
		mailS = textField_4.getText();
		idS = 0;
		if(nameS.length() == 0 || addressS.length() == 0 || sdtS.length() == 0 || mailS.length() == 0){
			JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
		}	
		else if(checkString(sdtS) == 0){
			JOptionPane.showMessageDialog(null, "Số điện thoại không thể dùng chữ cái!");
		}
		else{
			
			try {						
				sql = "select Id_NCC from ncc order by Id_NCC ASC;";
				rs = st.executeQuery(sql);
				while( rs.next()) {
					idS = rs.getInt("Id_NCC");
				}		
				idS = idS+1;
				//System.out.println("Đã nhận dc id = " + idS);
				sql = "INSERT INTO ncc(Id_NCC, Ten_NCC, TTLH, SDT, Mail)VALUES ('" + idS + "','" + nameS + "','" + addressS + "','" + sdtS + "','" + mailS +"');";
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Nhà cung cấp đã được thêm!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static int checkString(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) == '+') continue;
			if(s.charAt(j) > '9' || s.charAt(j) < '0') return 0;
		}
		return 1;
	}
}
