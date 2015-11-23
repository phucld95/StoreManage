package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ShowRevenucOf1Account {

	public JFrame frmDoanhSCa;
	private JTextField textField;
	private JTextField txtYyyymmdd;
	private JTextField txtYyyymmdd_1;
	private JLabel lblSumprice;
	private JLabel lblNewLabel;
	
	
	private static String startTime;
	private static String endTime;
	private static String account;
	private static String IdAccount;
	
	private static String sql;
	private static ResultSet rs;
	private static Statement st;
	
	
//	private static final String url = "jdbc:mysql://localhost";
//	private static final String user = "root"; 
//	private static final String password = "sieunhan";
//	private static java.sql.Connection con;
//	/**
//	 * 
//	 * Launch the application.
//	 */
//	public static void main(String args[]) {
//		try {
//        	con = DriverManager.getConnection(url, user, password);
//            System.out.println("Connect Success!");
//            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            st.executeUpdate ("Use managedatabase;");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ShowRevenucOf1Account lr = new ShowRevenucOf1Account(st);
//		lr.frmDoanhSCa.setVisible(true);
//	}

	/**
	 * Create the application.
	 */
	public ShowRevenucOf1Account(Statement sts) {
		initialize();
		st = sts;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoanhSCa = new JFrame();
		frmDoanhSCa.setTitle("Doanh s\u1ED1 c\u1EE7a nh\u00E2n vi\u00EAn");
		frmDoanhSCa.setResizable(false);
		frmDoanhSCa.setBounds(100, 100, 450, 286);
		frmDoanhSCa.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmDoanhSCa.getContentPane().setLayout(null);
		
		JLabel lblTnTiKhon = new JLabel("T\u00EAn t\u00E0i kho\u1EA3n :");
		lblTnTiKhon.setBounds(31, 32, 111, 14);
		frmDoanhSCa.getContentPane().add(lblTnTiKhon);
		
		textField = new JTextField();
		textField.setBounds(152, 29, 256, 20);
		frmDoanhSCa.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblThiGianBt = new JLabel("Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u :");
		lblThiGianBt.setBounds(31, 72, 111, 14);
		frmDoanhSCa.getContentPane().add(lblThiGianBt);
		
		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setText("yyyy-mm-dd");
		txtYyyymmdd.setBounds(152, 69, 256, 20);
		frmDoanhSCa.getContentPane().add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);
		
		JLabel lblThiGianKt = new JLabel("Th\u1EDDi gian k\u1EBFt th\u00FAc :");
		lblThiGianKt.setBounds(31, 113, 111, 14);
		frmDoanhSCa.getContentPane().add(lblThiGianKt);
		
		txtYyyymmdd_1 = new JTextField();
		txtYyyymmdd_1.setText("yyyy-mm-dd");
		txtYyyymmdd_1.setBounds(152, 110, 256, 20);
		frmDoanhSCa.getContentPane().add(txtYyyymmdd_1);
		txtYyyymmdd_1.setColumns(10);
		
		JButton btnXemTngDoanh = new JButton("Xem tổng doanh số");
		btnXemTngDoanh.setBounds(152, 156, 158, 23);
		frmDoanhSCa.getContentPane().add(btnXemTngDoanh);
		
		JLabel lblTngSTin = new JLabel("Tổng số tiền thu được :");
		lblTngSTin.setBounds(31, 207, 151, 14);
		frmDoanhSCa.getContentPane().add(lblTngSTin);
		
		JLabel lblSTinTrung = new JLabel("Số tiền trung bình 1 ngày :");
		lblSTinTrung.setBounds(31, 232, 151, 14);
		frmDoanhSCa.getContentPane().add(lblSTinTrung);
		
		lblSumprice = new JLabel("sumPrice");
		lblSumprice.setBounds(192, 207, 111, 14);
		frmDoanhSCa.getContentPane().add(lblSumprice);
		lblSumprice.setText("0");
		
		lblNewLabel = new JLabel("average");
		lblNewLabel.setBounds(192, 232, 118, 14);
		frmDoanhSCa.getContentPane().add(lblNewLabel);
		lblNewLabel.setText("0");
		
		btnXemTngDoanh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomRevenucClick();
				}
			}
		});
		btnXemTngDoanh.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	buttomRevenucClick();
            }
        });
	}
	
	private void buttomRevenucClick(){
		account =  textField.getText();
		startTime = txtYyyymmdd.getText();
		endTime = txtYyyymmdd_1.getText();
		if(account.length() == 0 || startTime.length() == 0 || endTime.length() == 0){
			JOptionPane.showMessageDialog(null, "Không được để trống thông tin!");
		}
		if(checkTime(startTime) == 0 || checkTime(endTime) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian viết liền, theo mẫu yyyy-mm-dd (vd: 2015-12-01) !");
		}
		else if(checkTime2(startTime, endTime) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian bắt đầu phải trước thời gian kết thúc!");
		}
		else{
			sql = "select * from account where username = '" + account +"';";
			try {
				rs = st.executeQuery(sql);
				rs.last();
				
				//System.out.print(rs.getRow());
				if(rs.getRow() == 0){
					JOptionPane.showMessageDialog(null, "Không có nhân viên có tài khoản cần tìm!");
				}
				else{
					rs.beforeFirst();
					rs.next();
					IdAccount = rs.getString("ID_Account");
					sql = "select sum(Tong_Tien) as sum from hoa_don where ID_ThuNgan = " + IdAccount + " and Thoi_Gian > '" + startTime + " 0:0:0' and Thoi_Gian < '" + endTime + " 0:0:0';";
					rs = st.executeQuery(sql);
					rs.next();
					lblSumprice.setText(rs.getString("sum"));
					sql = "select avg(Tong_Tien) as avg from hoa_don where ID_ThuNgan = " + IdAccount + " and Thoi_Gian > '" + startTime + " 0:0:0' and Thoi_Gian < '" + endTime + " 0:0:0';";
					rs = st.executeQuery(sql);
					rs.next();
					String temp = rs.getString("avg");
					lblNewLabel.setText(temp.substring(0, temp.length() -5));			
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	private int checkTime2 (String t1, String t2){
		int i;
		for(i=0; i<=7; i++){
			if(t1.charAt(i) > t2.charAt(i)) return 0;
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
