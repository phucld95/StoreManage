package ProcessDatabases;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

public class ProductManage {

	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "123456";
	private static String sql = new String();
	private static String name = new String();
	private static String id = new String();
	private static java.sql.Connection con;
	private static Statement st;
	private static String cb11 = "Tên mặt hàng.";
	private static String cb12 = "Giá nhập.";
	private static String cb13 = "Giá bán.";
	private static String cb14 = "Số lượng còn lại.";
	private static String cb15 = "Mã số nhà cũng cấp.";
	//private static String cb16 = "Mã số nhà cung cấp.";
	private static String chose = new String();
	private static String newIn = new String();
	private static String temp = new String();
	private static int i = 0;
	
	public ProductManage(){
		// Connect to database and creat statement.
        try {
        	con = DriverManager.getConnection(url, user, password);
            System.out.println("Connect Success!");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeUpdate ("Use test2;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ShowAllProduct(){
		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang;";
		inputData(sql);
      
	}
	
	public void searchProduct(){
		JTextField textField;
		JTextField textField_1;

		
		SearchAndEditProduct sae = new SearchAndEditProduct();
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		
		
		JLabel lblNhpTnSn = new JLabel("Nh\u1EADp t\u00EAn s\u1EA3n ph\u1EA9m ho\u1EB7c ID \u0111\u1EC3 t\u00ECm ki\u1EBFm!");
		lblNhpTnSn.setBounds(10, 34, 293, 14);
		frame.getContentPane().add(lblNhpTnSn);
		
		JLabel lblTnSnPhm = new JLabel("Tên SP : ");
		lblTnSnPhm.setBounds(10, 66, 85, 14);
		frame.getContentPane().add(lblTnSnPhm);
		
		JLabel lblId = new JLabel("ID : ");
		lblId.setBounds(284, 66, 46, 14);
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(93, 63, 168, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(307, 63, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btSearch = new JButton("T\u00ECm ki\u1EBFm");
		btSearch.setBounds(172, 96, 89, 23);
		frame.getContentPane().add(btSearch);
		
		btSearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	name = textField.getText();
            	id = textField_1.getText();
            	
            	//System.out.println("|"+name+"|"+id+"|");
            	
            	if(name.length() != 0){
            		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and Ten_MH = '" + name + "';";
            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có sản phẩm có tên cần tìm!");
						}
						else{
							rs.beforeFirst();
							TableDatabase stt = new TableDatabase(rs);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}                 
            	}
            	else if(id.length() != 0){
            		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and mh.ID_MatHang = '" + id + "';";
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
							TableDatabase stt = new TableDatabase(rs);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
            	}
            	if(id.length() == 0 && name.length() == 0){
            		JOptionPane.showMessageDialog(null, "Hãy nhập tên sản phẩm hoặc ID trước khi bấm tìm kiếm!");
            	}
            }
        });
		
		JLabel lblNewLabel = new JLabel("Sửa thông tin");
		lblNewLabel.setBounds(10, 134, 106, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 134, 235, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {cb11,cb12,cb13,cb14,cb15}));
		frame.getContentPane().add(comboBox);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(130, 178, 235, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Sửa thành");
		lblNewLabel_1.setBounds(10, 181, 85, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnChose = new JButton("Chỉnh sửa");
		btnChose.setBounds(172, 209, 131, 23);;
		frame.getContentPane().add(btnChose);
		btnChose.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	name = textField.getText();
            	id = textField_1.getText();
            	chose = comboBox.getSelectedItem().toString();
            	newIn = textField_3.getText();
            	//System.out.println("|"+name+"|"+id+"|");
            	
            	if(name.length() != 0){
            		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and Ten_MH = '" + name + "';";
            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có sản phẩm có tên cần sửa!");						
						}	
						rs.beforeFirst();
						if(rs.getRow() > 1){
							rs.beforeFirst();
							TableDatabase stt = new TableDatabase(rs);
							JOptionPane.showMessageDialog(null, "Mời nhập ID sản phẩm để sửa thông tin!");
						}
						else{
							if(chose == cb11){
								sql = "UPDATE mat_hang SET Ten_MH = '" + newIn + "' WHERE Ten_MH = '" + name +"';";
								st.executeUpdate (sql);
								JOptionPane.showMessageDialog(null, "Tên sản phẩm đã được chỉnh sửa!");
							}
							else{
								if(checkString(newIn) == 0){
									JOptionPane.showMessageDialog(null, "Dữ liệu mới phải là 1 số!");
								}
								else{
									if(chose == cb12){
										sql = "UPDATE mat_hang SET Gia_Nhap = '" + newIn + "' WHERE Ten_MH = '" + name +"';";
										st.executeUpdate (sql);
										JOptionPane.showMessageDialog(null, "Giá nhập sản phẩm đã được chỉnh sửa!");
									}
									if(chose == cb13){
										sql = "UPDATE mat_hang SET Gia_Ban = '" + newIn + "' WHERE Ten_MH = '" + name +"';";
										st.executeUpdate (sql);
										JOptionPane.showMessageDialog(null, "Giá bán sản phẩm đã được chỉnh sửa!");
									}
									if(chose == cb14){
										sql = "UPDATE mat_hang SET Soluong = '" + newIn + "' WHERE Ten_MH = '" + name +"';";
										st.executeUpdate (sql);
										JOptionPane.showMessageDialog(null, "Số lượng sản phẩm đã được chỉnh sửa!");
									}
									if(chose == cb15){
//										rsTableModel stt = new rsTableModel(rs);
//										Object k = stt.getValueAt(1, 1);
//										sql = "UPDATE cung_cap SET ID_NCC = '" + newIn + "' WHERE ID_MatHang = '" + k +"';";
//										
//										st.executeUpdate (sql);
										JOptionPane.showMessageDialog(null, "Nhà cung cấp sản phẩm đã được chỉnh sửa!");
									}
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}                 
            	}
            	else if(id.length() != 0){
            		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and mh.ID_MatHang = '" + id + "';";
            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có sản phẩm có tên cần sửa!");						
						}	
						else{
							if(chose == cb11){
								sql = "UPDATE mat_hang SET Ten_MH = '" + newIn + "' WHERE ID_MatHang = '" + id +"';";
								st.executeUpdate (sql);
								JOptionPane.showMessageDialog(null, "Tên sản phẩm đã được chỉnh sửa!");
							}
							else{
								if(checkString(newIn) == 0){
									JOptionPane.showMessageDialog(null, "Dữ liệu mới phải là 1 số!");
								}
								else{
									if(chose == cb12){
										sql = "UPDATE mat_hang SET Gia_Nhap = '" + newIn + "' WHERE ID_MatHang = '" + id +"';";
										st.executeUpdate (sql);
										JOptionPane.showMessageDialog(null, "Giá nhập sản phẩm đã được chỉnh sửa!");
									}
									if(chose == cb13){
										sql = "UPDATE mat_hang SET Gia_Ban = '" + newIn + "' WHERE ID_MatHang = '" + id +"';";
										st.executeUpdate (sql);
										JOptionPane.showMessageDialog(null, "Giá bán sản phẩm đã được chỉnh sửa!");
									}
									if(chose == cb14){
										sql = "UPDATE mat_hang SET Soluong = '" + newIn + "' WHERE ID_MatHang = '" + id +"';";
										st.executeUpdate (sql);
										JOptionPane.showMessageDialog(null, "Số lượng sản phẩm đã được chỉnh sửa!");
									}
									if(chose == cb15){
										sql = "UPDATE cung_cap SET ID_NCC = '" + newIn + "' WHERE ID_MatHang = '" + id +"';";
										
										st.executeUpdate (sql);
										JOptionPane.showMessageDialog(null, "Nhà cung cấp sản phẩm đã được chỉnh sửa!");
									}
								}
							}
						}
					}
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            }
        });
	}
	
	public static int checkString(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) > '9' || s.charAt(j) < '0') return 0;
		}
		return 1;
	}
	
	public static void inputData(String sql){
		try {
            ResultSet rs = st.executeQuery(sql);
            TableDatabase stt = new TableDatabase(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}