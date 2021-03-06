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
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowBestAccount {

	public JFrame frmDoanhSCa;
	private JTextField txtYyyymmdd;
	private JTextField txtYyyymmdd_1;
	private JTable table;
	
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
	
	Object[] colump = {"ID Nhân Viên","Tên nhân viên","Số lượng hóa đơn giải quyết","Tổng tiền thu về", "Doanh thu trung bình"};
	Object[] row = new Object[5];
	DefaultTableModel model = new DefaultTableModel();
	
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
		ShowBestAccount lr = new ShowBestAccount(st);
		lr.frmDoanhSCa.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public ShowBestAccount(Statement sts) {
		initialize();
		st = sts;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoanhSCa = new JFrame();
		frmDoanhSCa.setTitle("Bảng xếp hạng nhân viên.");
		frmDoanhSCa.setResizable(false);
		frmDoanhSCa.setBounds(100, 100, 672, 412);
		frmDoanhSCa.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmDoanhSCa.getContentPane().setLayout(null);
		
		JLabel lblThiGianBt = new JLabel("Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u :");
		lblThiGianBt.setBounds(152, 36, 111, 14);
		frmDoanhSCa.getContentPane().add(lblThiGianBt);
		
		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setText("yyyy-mm-dd");
		txtYyyymmdd.setBounds(290, 33, 256, 20);
		frmDoanhSCa.getContentPane().add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);
		
		JLabel lblThiGianKt = new JLabel("Th\u1EDDi gian k\u1EBFt th\u00FAc :");
		lblThiGianKt.setBounds(152, 73, 111, 14);
		frmDoanhSCa.getContentPane().add(lblThiGianKt);
		
		txtYyyymmdd_1 = new JTextField();
		txtYyyymmdd_1.setText("yyyy-mm-dd");
		txtYyyymmdd_1.setBounds(290, 70, 256, 20);
		frmDoanhSCa.getContentPane().add(txtYyyymmdd_1);
		txtYyyymmdd_1.setColumns(10);
		
		JButton btnXemTngDoanh = new JButton("Xem xếp hạng");
		btnXemTngDoanh.setBounds(275, 112, 158, 23);
		frmDoanhSCa.getContentPane().add(btnXemTngDoanh);
		
		model.setColumnIdentifiers(colump);
		table = new JTable();
		table.setModel(model);
		table.setBounds(35, 167, 602, 189);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(35, 167, 602, 189);
		frmDoanhSCa.getContentPane().add(jsp);
		
		
		
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
	
	private void buttomRevenucClick(){
		
		startTime = txtYyyymmdd.getText();
		endTime = txtYyyymmdd_1.getText();
		if(startTime.length() == 0 || endTime.length() == 0){
			JOptionPane.showMessageDialog(null, "Không được để trống thông tin!");
		}
		if(checkTime(startTime) == 0 || checkTime(endTime) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian viết liền, theo mẫu yyyy-mm-dd (vd: 2015-12-01) !");
		}
		else if(checkInputTime(startTime) == 0 || checkInputTime(endTime) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian nhập phải là 1 ngày có thực!");
		}
		else if(checkTime2(startTime, endTime) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian bắt đầu phải trước thời gian kết thúc!");
		}
		else{
			try {			
				sql = "select ID_ThuNgan, tenNV, count(ID_HoaDon) as soluongHD, sum(Tong_Tien) as sum, avg(Tong_Tien) as avg from Account, hoa_don where Thoi_Gian > '" + startTime + " 0:0:0' and Thoi_Gian < '" + endTime + " 0:0:0' and Account.ID_Account = hoa_don.ID_ThuNgan group by (ID_ThuNgan) order by sum DESC;";
				rs = st.executeQuery(sql);
				while(rs.next()){
					row[0] = rs.getString("ID_ThuNgan");
					row[1] = rs.getString("tenNV");
					row[2] = rs.getString("soluongHD");
					row[3] = rs.getString("sum");
					row[4] = rs.getString("avg");
					model.addRow(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	private int checkTime2 (String t1, String t2){
		int i;
		for(i=0; i<=9; i++){
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
