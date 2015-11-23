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

public class ShowRevenueOfStore {

	public JFrame frmDoanhSCa;
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
//		ShowRevenueOfStore lr = new ShowRevenueOfStore(st);
//		lr.frmDoanhSCa.setVisible(true);
//	}

	/**
	 * Create the application.
	 */
	public ShowRevenueOfStore(Statement sts) {
		initialize();
		st = sts;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoanhSCa = new JFrame();
		frmDoanhSCa.setTitle("Doanh số của cửa hàng.");
		frmDoanhSCa.setResizable(false);
		frmDoanhSCa.setBounds(100, 100, 450, 286);
		frmDoanhSCa.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmDoanhSCa.getContentPane().setLayout(null);
		
		JLabel lblThiGianBt = new JLabel("Th\u1EDDi gian b\u1EAFt \u0111\u1EA7u :");
		lblThiGianBt.setBounds(31, 36, 111, 14);
		frmDoanhSCa.getContentPane().add(lblThiGianBt);
		
		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setText("yyyy-mm-dd");
		txtYyyymmdd.setBounds(152, 33, 256, 20);
		frmDoanhSCa.getContentPane().add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);
		
		JLabel lblThiGianKt = new JLabel("Th\u1EDDi gian k\u1EBFt th\u00FAc :");
		lblThiGianKt.setBounds(31, 73, 111, 14);
		frmDoanhSCa.getContentPane().add(lblThiGianKt);
		
		txtYyyymmdd_1 = new JTextField();
		txtYyyymmdd_1.setText("yyyy-mm-dd");
		txtYyyymmdd_1.setBounds(152, 70, 256, 20);
		frmDoanhSCa.getContentPane().add(txtYyyymmdd_1);
		txtYyyymmdd_1.setColumns(10);
		
		JButton btnXemTngDoanh = new JButton("Xem doanh số");
		btnXemTngDoanh.setBounds(152, 113, 158, 23);
		frmDoanhSCa.getContentPane().add(btnXemTngDoanh);
		
		JLabel lblTngSTin = new JLabel("Tổng số tiền thu được :");
		lblTngSTin.setBounds(31, 158, 151, 14);
		frmDoanhSCa.getContentPane().add(lblTngSTin);
		
		JLabel lblSTinTrung = new JLabel("Số tiền trung bình 1 ngày :");
		lblSTinTrung.setBounds(31, 191, 151, 14);
		frmDoanhSCa.getContentPane().add(lblSTinTrung);
		
		lblSumprice = new JLabel("sumPrice");
		lblSumprice.setBounds(192, 158, 111, 14);
		frmDoanhSCa.getContentPane().add(lblSumprice);
		lblSumprice.setText("0");
		
		lblNewLabel = new JLabel("average");
		lblNewLabel.setBounds(192, 191, 118, 14);
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
		
		startTime = txtYyyymmdd.getText();
		endTime = txtYyyymmdd_1.getText();
		if(startTime.length() == 0 || endTime.length() == 0){
			JOptionPane.showMessageDialog(null, "Không được để trống thông tin!");
		}
		if(checkTime(startTime) == 0 || checkTime(endTime) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian viết liền, theo mẫu yyyy-mm-dd (vd: 2015-12-01) !");
		}
		else if(checkTime2(startTime, endTime) == 0){
			JOptionPane.showMessageDialog(null,"Thời gian bắt đầu phải trước thời gian kết thúc!");
		}
		else{
			try {			
				sql = "select sum(Tong_Tien) as sum from hoa_don where Thoi_Gian > '" + startTime + " 0:0:0' and Thoi_Gian < '" + endTime + " 0:0:0';";
				rs = st.executeQuery(sql);
				rs.next();
				lblSumprice.setText(rs.getString("sum"));
				sql = "select avg(Tong_Tien) as avg from hoa_don where Thoi_Gian > '" + startTime + " 0:0:0' and Thoi_Gian < '" + endTime + " 0:0:0';";
				rs = st.executeQuery(sql);
				rs.next();
				String temp = rs.getString("avg");
				lblNewLabel.setText(temp.substring(0, temp.length() -5));			
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
