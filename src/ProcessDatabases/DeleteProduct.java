package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DeleteProduct {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static String id;
	private static String name;
	private static String sql;
	private static ResultSet rs;
	private static Statement st;
	private static Statement st1;
	
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "123456";
	

	/**
	 * Create the application.
	 */
	public DeleteProduct(Statement st1) {		
		st = st1;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 192);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setTitle("Xóa 1 mặt hàng.");
		frame.setResizable(false);
		
		
		JLabel lblXaMt = new JLabel("X\u00F3a 1 m\u1EB7t h\u00E0ng \u0111ang kinh doanh.");
		lblXaMt.setBounds(25, 22, 291, 14);
		frame.getContentPane().add(lblXaMt);
		
		JLabel lblNhpTnSn = new JLabel("Nh\u1EADp t\u00EAn s\u1EA3n ph\u1EA9m:");
		lblNhpTnSn.setBounds(43, 55, 115, 14);
		frame.getContentPane().add(lblNhpTnSn);
		
		textField = new JTextField();
		textField.setBounds(167, 52, 189, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNhpMSn = new JLabel("Nh\u1EADp m\u00E3 s\u1EA3n ph\u1EA9m:");
		lblNhpMSn.setBounds(43, 90, 115, 14);
		frame.getContentPane().add(lblNhpMSn);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 87, 189, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btSearch = new JButton("Ki\u1EC3m tra");
		btSearch.setBounds(390, 51, 102, 23);
		frame.getContentPane().add(btSearch);
		
		btSearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	name = textField.getText();
            	id = textField_1.getText();
            	
            	//System.out.println("|"+name+"|"+id+"|");
            	
            	if(name.length() != 0){
            		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and Ten_MH = '" + name + "';";
            		
					try {
						rs = st.executeQuery(sql);
						//System.out.println("Qua");
						rs.last();
						
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có sản phẩm có tên cần tìm!");
						}
						else{
							rs.beforeFirst();
							TableDatabase stt = new TableDatabase(rs,"Thông tin những mặt hàng có tên muốn xóa.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
            	}
            	else if(id.length() != 0){
            		sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC and mh.ID_MatHang = '" + id + "';";
            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có sản phẩm có mã cần tìm!");
						}
						else{
							rs.beforeFirst();
							TableDatabase stt = new TableDatabase(rs, "Thông tin những sản phẩm có mã muốn xóa.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
            	}
            	if(id.length() == 0 && name.length() == 0){
            		JOptionPane.showMessageDialog(null, "Hãy nhập tên sản phẩm hoặc ID trước khi bấm kiểm tra!");
            	}
            }
        });
		
		
		JButton btnNewButton_1 = new JButton("X\u00F3a");
		btnNewButton_1.setBounds(390, 86, 102, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	name = textField.getText();
            	id = textField_1.getText();
            	
            	if(id.length() != 0){
            		sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC and mh.ID_MatHang = '" + id + "';";
            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có sản phẩm có mã cần tìm!");
						}
						else{
							rs.beforeFirst();
							
							sql = "DELETE FROM thuoc_nhom WHERE ID_MatHang = '"+ id +"';";
							st.executeUpdate(sql);
							sql = "DELETE FROM cung_cap WHERE ID_MatHang = '"+ id +"';";
							st.executeUpdate(sql);
							sql = "DELETE FROM mat_hang WHERE ID_MatHang = '"+ id +"';";
							st.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Đã xóa thành công mặt hàng!!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
            	}
            	if(id.length() == 0){
            		JOptionPane.showMessageDialog(null, "Hãy nhập mã sản phẩm trước khi bấm xóa!");
            	}
            }
        });
	}
}
