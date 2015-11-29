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

//	private static String sql = new String();
	private static String name = new String();
	private static String id = new String();
//	private static Statement st;
//	private static ResultSet rs;
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
	
	
	//private static String name;
	private static String prizeI;
	private static String prizeO;
	private static String number;
	private static String supply;
	private static String group;
	//private static String temp;
	private static int idP = 0;
	private static int idG = 0;
	private static int idS = 0;
	
	//private static String sql;
	//private static Statement st;
	
	
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "sieunhan";
	private static java.sql.Connection con;
	private static Statement st;
	private static ResultSet rs;
	private static String sql;
	private JTextField textField_1;
	public static void main(String args[]) {
	try {
    	con = DriverManager.getConnection(url, user, password);
        System.out.println("Connect Success!");
        Statement sts;
        sts = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        sts.executeUpdate ("Use managedatabase;");
        ProductManage lr = new ProductManage(sts);
    	lr.addNewProduct();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}	
	}
	
	
	
	
	public ProductManage(Statement sts){
		st = sts;
		// Connect to database and creat statement.
        
	}
	public void showProductNeedAdd (){
		sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC and Soluong <= 20;";
		try {
            ResultSet rs = st.executeQuery(sql);
            TableDatabase stt = new TableDatabase(rs,"All produce need add more.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void deleteProduct(){
		DeleteProduct window = new DeleteProduct(st);
	}
	
	public void ShowAllProduct(){
		sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC;";
		try {
            ResultSet rs = st.executeQuery(sql);
            TableDatabase stt = new TableDatabase(rs,"All produce in store.");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
      
	}
	
	public void addNewProduct(){
		JFrame frame;
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		JTextField textField_3;
		JTextField textField_4;
		JTextField textField_5;
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 386, 396);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		frame.setTitle("Thêm mặt hàng.");
		frame.setResizable(false);
		
		JLabel lblNhpThngTin = new JLabel("Nh\u1EADp th\u00F4ng tin s\u1EA3n ph\u1EA9m m\u1EDBi c\u1EA7n th\u00EAm:");
		lblNhpThngTin.setBounds(10, 21, 255, 14);
		frame.getContentPane().add(lblNhpThngTin);
		
		JLabel lblTnSnPhm = new JLabel("T\u00EAn s\u1EA3n ph\u1EA9m:");
		lblTnSnPhm.setBounds(33, 63, 111, 14);
		frame.getContentPane().add(lblTnSnPhm);
		
		JLabel lblGiNhpVo = new JLabel("Gi\u00E1 nh\u1EADp v\u00E0o:");
		lblGiNhpVo.setBounds(33, 92, 111, 14);
		frame.getContentPane().add(lblGiNhpVo);
		
		JLabel lblGiBnRa = new JLabel("Gi\u00E1 b\u00E1n ra:");
		lblGiBnRa.setBounds(33, 122, 111, 14);
		frame.getContentPane().add(lblGiBnRa);
		
		JLabel lblSLngHin = new JLabel("S\u1ED1 l\u01B0\u1EE3ng hi\u1EC7n c\u00F3:");
		lblSLngHin.setBounds(33, 152, 111, 14);
		frame.getContentPane().add(lblSLngHin);
		
		JLabel lblNhCungCp = new JLabel("Nh\u00E0 cung c\u1EA5p:");
		lblNhCungCp.setBounds(33, 182, 111, 14);
		frame.getContentPane().add(lblNhCungCp);
		
		JLabel lblNhCungCp_1 = new JLabel("Nh\u00E0 cung c\u1EA5p m\u1EDBi:");
		lblNhCungCp_1.setBounds(33, 213, 111, 14);
		frame.getContentPane().add(lblNhCungCp_1);
		
		JLabel lblThucNhmHng = new JLabel("Thu\u1ED9c nh\u00F3m h\u00E0ng:");
		lblThucNhmHng.setBounds(33, 242, 111, 14);
		frame.getContentPane().add(lblThucNhmHng);
		
		JLabel lblNhmHngMi = new JLabel("Nh\u00F3m h\u00E0ng m\u1EDBi:");
		lblNhmHngMi.setBounds(33, 272, 111, 14);
		frame.getContentPane().add(lblNhmHngMi);
		
		textField = new JTextField();
		textField.setBounds(154, 57, 180, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(154, 86, 180, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(154, 116, 180, 20);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(154, 146, 180, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(154, 207, 180, 20);
		frame.getContentPane().add(textField_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(154, 179, 180, 20);
		frame.getContentPane().add(comboBox);
		sql = "select Ten_NCC from ncc";
		try {
			rs = st.executeQuery(sql);
			while( rs.next()) {
				comboBox.addItem( rs.getString("Ten_NCC"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(154, 266, 180, 20);
		frame.getContentPane().add(textField_5);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(154, 236, 180, 20);
		frame.getContentPane().add(comboBox_1);
		sql = "select Ten_Nhomhang from Nhomhang";
		try {
			rs = st.executeQuery(sql);
			while( rs.next()) {
				comboBox_1.addItem( rs.getString("Ten_Nhomhang"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnNewButton = new JButton("Th\u00EAm s\u1EA3n ph\u1EA9m");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name = textField.getText();
				prizeI = textField_1.getText();
				prizeO = textField_2.getText();
				number = textField_3.getText();
				supply = textField_4.getText();
				group = textField_5.getText();
				idP = idS = idG = 0;
				if(supply.length() == 0){
					supply = comboBox.getSelectedItem().toString();
				}
				if(group.length() == 0){
					group = comboBox_1.getSelectedItem().toString();
					//System.out.print(group);
				}
				if(name.length() == 0 || prizeI.length() == 0 || prizeO.length() == 0 || number.length() == 0){
					JOptionPane.showMessageDialog(null, "Thông tin không được để trống!");
				}				
				else{
					//System.out.println (supply + group + name);
					try {						
						sql = "select ID_MatHang from mat_hang order by ID_MatHang ASC;";
						rs = st.executeQuery(sql);
						while( rs.next()) {
							idP = rs.getInt("ID_MatHang");
							}
						idP = idP+1;
						//System.out.println(id);
						sql = "select Id_NCC from ncc where Ten_NCC = '"+supply+"';";
						rs = st.executeQuery(sql);
						while( rs.next()) {
							idS = rs.getInt("Id_NCC");
						}						
						if(idS == 0){
							sql = "select Id_NCC from ncc order by ID_NCC ASC;";
							rs = st.executeQuery(sql);
							idS = 0;
							while( rs.next()) {
								idS = rs.getInt("Id_NCC");
								}
							idS = idS+1;
							sql = "INSERT INTO ncc(Id_NCC, Ten_NCC, SDT, Mail, TTLH)VALUES ('" + idS + "','" + supply  +"','0000','Chưa có @domain.com','Chưa có');";
							st.executeUpdate(sql);
							//System.out.println("Qua");
							
						}
						
						sql = "select ID_NhomHang from nhomhang where Ten_Nhomhang = '"+group+"';";
						rs = st.executeQuery(sql);
						
						while( rs.next()) {
							idG = rs.getInt("ID_NhomHang");
						}
						if(idG == 0){
							sql = "select ID_NhomHang from nhomhang;";
							rs = st.executeQuery(sql);
							while( rs.next()) {
								idG = rs.getInt("ID_NhomHang");
								}
							idG = idG+1;
							sql = "INSERT INTO nhomhang(ID_NhomHang, Ten_Nhomhang)VALUES ('" + idG + "','" + group +"');";
							st.executeUpdate(sql);
						}
						
						sql = "INSERT INTO mat_hang(ID_MatHang,Ten_MH, Soluong, Gia_Ban)VALUES ('" + idP + "','" + name +"','" + number + "','" + prizeO + "');";
						st.executeUpdate(sql);
						//  + prizeI + "','" + prizeO + "','"  	
						sql = "INSERT INTO cung_cap(ID_MatHang, ID_NCC, Gia_Nhap)VALUES (" + idP + "," + idS + ",'" +prizeI + "');";
						System.out.println(sql);
						st.executeUpdate(sql);
						sql = "INSERT INTO thuoc_nhom(ID_MatHang, ID_NhomHang)VALUES (" + idP + "," + idG +");";
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Mặt hàng đã được tạo!");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(115, 312, 145, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	public void searchProduct(){
		JTextField textField;
		JTextField textField_1;
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.setTitle("Kiểm tra, sửa thông tin mặt hàng.");
		frame.setResizable(false);
		
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
							TableDatabase stt = new TableDatabase(rs,"Những sản phẩm có tên cần tìm.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}                 
            	}
            	else if(id.length() != 0){
            		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and mh.ID_MatHang = '" + id + "';";
            		ResultSet rs = null;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có sản phẩm có mã cần tìm!");
						}
						else{
							rs.beforeFirst();
							TableDatabase stt = new TableDatabase(rs,"Những sản phẩm có ID cần tìm.");
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {cb11,cb12,cb13,cb14}));
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
            	if(newIn.length() == 0){
            		JOptionPane.showMessageDialog(null, "Thông tin mới không được để trống!");
            	}
            	else{
            		if(name.length() != 0 && id.length() == 0){
                		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and Ten_MH = '" + name + "';";
                		ResultSet rs;
    					try {
    						rs = st.executeQuery(sql);
    						rs.last();
    						//System.out.print(rs.getRow());
    						if(rs.getRow() == 0){
    							JOptionPane.showMessageDialog(null, "Không có sản phẩm có tên cần sửa!");						
    						}	
    						else{
    							rs.beforeFirst();
    							TableDatabase stt = new TableDatabase(rs,"Những sản phẩm có tên cần sửa.");
    							JOptionPane.showMessageDialog(null, "Mời nhập ID sản phẩm để sửa thông tin!");
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
    									sql = "select mh.ID_MatHang, Ten_MH, cc.Id_NCC, ncc.Ten_NCC, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.Id_NCC and mh.ID_MatHang = '" + id +"';";
    									rs.beforeFirst();
    									TableDatabase stt = new TableDatabase(rs,"Những nhà cung cấp sản phẩm.");
    									fixPriceInput fpi = new fixPriceInput(st,id, newIn);
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
//    									
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
            }   	
        });
	}
	
	public static void fixSupplyProduce(){
		
	}
	
	public static int checkString(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) > '9' || s.charAt(j) < '0') return 0;
		}
		return 1;
	}
}