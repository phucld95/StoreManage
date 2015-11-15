package ProcessDatabases;

import java.awt.Dialog;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ChoseEven {

	public JFrame frmSKinKhuyn = new JFrame();
	private static Statement st;
	private static ResultSet rs;
	private static String sql;
	private String idE;
	public int temp = 0;
	JComboBox comboBox;
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public ChoseEven(ResultSet result) throws SQLException {
		rs = result;
		initialize();
	}

	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "sieunhan";
	private static java.sql.Connection con;
	//private static Statement st;
	
//	public static void main(String[] args) {
//		try {
//        	con = DriverManager.getConnection(url, user, password);
//            System.out.println("Connect Success!");
//            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            st.executeUpdate ("Use managedatabase;");
//            sql = String.format("select dkm.Id_KM, Ten_KM, ID_MatHang, Gia_KM from khuyen_mai km, duoc_khuyen_mai dkm where TGDR <= '%s' and TGKT >= '%s' and dkm.Id_KM = km.Id_KM and ID_MatHang = '%d';",
//					"2015-10-11","2015-10-11",1);
//			ResultSet result = st.executeQuery(sql);
//			ChoseEven ce = new ChoseEven(result);
//			ce.frmSKinKhuyn.setVisible(true);
//            
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		PriceProduct pp = new PriceProduct(st);
//			
//	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frmSKinKhuyn.setVisible(true);
		frmSKinKhuyn.setTitle("Chọn đợt giảm giá.");
		frmSKinKhuyn.setBounds(100, 100, 450, 313);
		frmSKinKhuyn.setResizable(false);
		frmSKinKhuyn.getContentPane().setLayout(null);
		frmSKinKhuyn.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		 
		
		
		rsTableModel tv = new rsTableModel(rs);
		JTable table = new JTable(tv);
		table.setBounds(35,78,360,73);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(35, 78, 360, 73);
		frmSKinKhuyn.getContentPane().add(scrollPane);
		
		JLabel lblNhngSKin = new JLabel("Nh\u1EEFng s\u1EF1 ki\u1EC7n c\u00F9ng khuy\u1EBFn m\u1EA1i s\u1EA3n ph\u1EA9m n\u00E0y :");
		lblNhngSKin.setBounds(31, 31, 297, 22);
		frmSKinKhuyn.getContentPane().add(lblNhngSKin);
		
		JLabel lblNewLabel = new JLabel("Ch\u1ECDn m\u00E3 \u0111\u1EE3t khuy\u1EBFn m\u1EA1i \u0111\u00EA thanh to\u00E1n :\r\n");
		lblNewLabel.setBounds(31, 161, 238, 22);
		frmSKinKhuyn.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(303, 162, 92, 20);
		rs.beforeFirst();
		try {
			while(rs.next()) {
				comboBox.addItem( rs.getString("Id_KM"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frmSKinKhuyn.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("X\u00E1c nh\u1EADn");
		btnNewButton.setBounds(140, 210, 159, 23);
		frmSKinKhuyn.getContentPane().add(btnNewButton);
		
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					clickButtomXacNhan();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                clickButtomXacNhan();
            }
        });
		
		
		
	}
	
	public int check(){
		return temp;
	}
	
	public void clickButtomXacNhan(){
		idE = comboBox.getSelectedItem().toString();
		temp = convertStringToIn(idE);
		if(check() != 0){
			frmSKinKhuyn.setVisible(false);
		}
	}
	
	private static int convertStringToIn(String str){
		int sum = 0;
		int i = str.length();
		for(int j=0;j<i;j++){
			sum += (int)(str.charAt(j)-48)*Math.pow(10, i-j-1);
		}
		return sum;
	}
}
