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

public class CreateEven {

	private JFrame frmThmSKin;
	private JTextField textField;
	private JTextField txtMmddyyyy;
	private JTextField txtMmddyyyy_1;

	private static String sql;
	private static ResultSet rs;
	private static Statement st;
	
	private static String tgdr;
	private static String tgkt;
	private static String name;
	private static int idS;

	/**
	 * Create the application.
	 */
	public CreateEven(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThmSKin = new JFrame();
		frmThmSKin.setTitle("Th\u00EAm s\u1EF1 ki\u1EC7n");
		frmThmSKin.setResizable(false);
		frmThmSKin.setBounds(100, 100, 450, 226);
		frmThmSKin.setVisible(true);
		frmThmSKin.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmThmSKin.getContentPane().setLayout(null);
		
		JLabel lblThngTinS = new JLabel("Th\u00F4ng tin s\u1EF1 ki\u1EC7n :");
		lblThngTinS.setBounds(22, 23, 129, 14);
		frmThmSKin.getContentPane().add(lblThngTinS);
		
		JLabel lblTnSKin = new JLabel("T\u00EAn s\u1EF1 ki\u1EC7n :");
		lblTnSKin.setBounds(44, 48, 107, 14);
		frmThmSKin.getContentPane().add(lblTnSKin);
		
		JLabel lblNewLabel = new JLabel("Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u :");
		lblNewLabel.setBounds(44, 77, 107, 14);
		frmThmSKin.getContentPane().add(lblNewLabel);
		
		JLabel lblThiGianKt = new JLabel("Th\u1EDDi gian k\u1EBFt th\u00FAc :");
		lblThiGianKt.setBounds(44, 106, 107, 14);
		frmThmSKin.getContentPane().add(lblThiGianKt);
		
		textField = new JTextField();
		textField.setBounds(174, 42, 215, 20);
		frmThmSKin.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtMmddyyyy = new JTextField();
		txtMmddyyyy.setText("yyyy-mm-dd");
		txtMmddyyyy.setColumns(10);
		txtMmddyyyy.setBounds(174, 71, 215, 20);
		frmThmSKin.getContentPane().add(txtMmddyyyy);
		
		txtMmddyyyy_1 = new JTextField();
		txtMmddyyyy_1.setText("yyyy-mm-dd");
		txtMmddyyyy_1.setColumns(10);
		txtMmddyyyy_1.setBounds(174, 100, 215, 20);
		frmThmSKin.getContentPane().add(txtMmddyyyy_1);
		
		JButton btnNewButton = new JButton("Th\u00EAm s\u1EF1 ki\u1EC7n");
		btnNewButton.setBounds(132, 139, 175, 31);
		frmThmSKin.getContentPane().add(btnNewButton);
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
	private void clickButtom (){
		name = textField.getText();
		tgdr = txtMmddyyyy.getText();
		tgkt = txtMmddyyyy_1.getText();
		if(checkTime(tgdr) == 0 || checkTime(tgkt) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian viết liền, theo mẫu yyyy-mm-dd (vd: 2015-12-01) !");
		}
		else if(checkInputTime(tgdr) == 0 || checkInputTime(tgkt) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian nhập vào phải có thực!");
		}
		else if(checkTime2(tgdr, tgkt) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian bắt đầu phải trước thời gian kết thúc!");
		}
		else{
			try {					
				sql = "select Id_KM from khuyen_mai order by Id_KM ASC;";
				rs = st.executeQuery(sql);
				while( rs.next()) {
					idS = rs.getInt("Id_KM");
				}		
				idS = idS+1;
				//System.out.println("Đã nhận dc id = " + idS);
				sql = "INSERT INTO khuyen_mai(Id_KM, Ten_KM, TGDR, TGKT)VALUES ('" + idS + "','" + name + "','" + tgdr + "','" + tgkt + "');";
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Sự kiện đã được thêm!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//2015-11-11
	private int checkTime2 (String t1, String t2){
		int i;
		for(i=0; i<=10; i++){
			if(t1.charAt(i) > t2.charAt(i)) return 0;
			if(t1.charAt(i) < t2.charAt(i)) return 1;
		}
		return 1;
	}
	
	private int checkTime(String str){
		String temp = "2015-11-12";	
		if(str.length() != temp.length()){
			
			return 0;
		}
		if(checkString(str) == 0){
			
			return 0;
		}
		if(checkSpace(str) == 0){
			
			return 0;
		}
		return 1;
	}
	
	private static int checkInputTime(String input_Time) {

		int year, month, day;

		// Check year
		String[] result_String = input_Time.split("-");

		year = Integer.parseInt(result_String[0]);
		month = Integer.parseInt(result_String[1]);
		day = Integer.parseInt(result_String[2]);
		
		

		// Ki?m tra di?u ki?n t?i thi?u
		if (year <= 0)
			return 0;
		if (month <= 0 || month > 12)
			return 0;
		if (day <= 0 || day > 31)
			return 0;

		// Tru?ng h?p nam ko nhu?n
		if (year % 4 != 0) {
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				if (day <= 0 || day > 30)
					return 0;
			}
			if (month == 2) {
				if (day <= 0 || day > 28)
					return 0;
			}

		}

		// Tru?ng h?p nam nhu?n chia h?t cho 4
		if (year % 4 == 0) {

			if (year % 400 == 0) {
				if (month == 4 || month == 6 || month == 9 || month == 11) {
					if (day <= 0 || day > 30)
						return 0;
				}

				if (month == 2) {
					if (day <= 0 || day > 29)
						return 0;
				}
			}
		}
		
		// Tru?ng h?p nam chia h?t cho 100 nhung ko chia h?t cho 400 ( ko là nam nhu?n ) 

			if (year % 100 == 0) {
				if(year % 400 != 0)
				if (month == 4 || month == 6 || month == 9 || month == 11) {
					if (day <= 0 || day > 30)
						return 0;
				}
				if (month == 2) {
					if (day <= 0 || day > 29)
						return 0;
				}

			}
			return 1;
		}
	
	private int checkSpace(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) == ' ') return 0;
		}
		if(s.charAt(4) != '-' || s.charAt(7) != '-'){
			return 0;
		}
		return 1;
	}
	
	public static int checkString(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) == '-') continue;
			if(s.charAt(j) > '9' || s.charAt(j) < '0') return 0;
		}
		return 1;
	}
}
