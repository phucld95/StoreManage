package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	private JLabel lblAvg;
	
	private static String startTime;
	private static String endTime;
	private static String account;
	private static String IdAccount;
	
	private static String sql;
	private static ResultSet rs;
	private static Statement st;
	
	
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "sieunhan";
	private static java.sql.Connection con;
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String args[]) {
		try {
        	con = DriverManager.getConnection(url, user, password);
            System.out.println("Connect Success!");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeUpdate ("Use managedatabase;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShowRevenucOf1Account lr = new ShowRevenucOf1Account(st);
		lr.frmDoanhSCa.setVisible(true);
	}

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
		frmDoanhSCa.setBounds(100, 100, 450, 308);
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
		
		lblSumprice = new JLabel("sumPrice");
		lblSumprice.setBounds(192, 207, 111, 14);
		frmDoanhSCa.getContentPane().add(lblSumprice);
		lblSumprice.setText("0");
		
		JLabel lblTrungBnhTheo = new JLabel("Trung bình theo ngày :");
		lblTrungBnhTheo.setBounds(31, 240, 151, 14);
		frmDoanhSCa.getContentPane().add(lblTrungBnhTheo);
		
		lblAvg = new JLabel(" ");
		lblAvg.setBounds(192, 240, 46, 14);
		frmDoanhSCa.getContentPane().add(lblAvg);
		
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
		else if(checkInputTime(startTime) == 0 || checkInputTime(endTime)==0){
			JOptionPane.showMessageDialog(null,"Thời gian nhập phải là 1 ngày có thực!");
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
					String s = "0";
					String a = "0.0";
					while(rs.next()){
						int sum = rs.getInt("sum");
						s = rs.getString("sum");
						float avg = sum/daysBetween(startTime, endTime);
						a = Float.toString(avg);
					}
					lblSumprice.setText(s);
					lblAvg.setText(a);
						
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private long daysBetween(String day1,String day2) {


        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();


        // Định nghĩa 2 mốc thời gian ban đầu
        Date date1 = Date.valueOf(day1);
        Date date2 = Date.valueOf(day2);


        c1.setTime(date1);
        c2.setTime(date2);


        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime())
                / (24 * 3600 * 1000);


       return noDay;


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
	
	private int checkTime2 (String t1, String t2){
		int i;
		for(i=0; i<=9; i++){
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
