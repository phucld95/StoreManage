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
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class SearchAndFixSupply {

	public JFrame frmTmKimChnh;
	private JTextField textField;
	private JComboBox comboBox;
	private JTextField textField_1;
	private JTable table;
	private JTable table2;
	Object[] colump = {"ID Nhà cung cấp","Tên nhà cung cấp","Số điện thoại","E-Mail", "Thông tin liên hệ"};
	Object[] row = new Object[5];
	DefaultTableModel model = new DefaultTableModel();
	Object[] colump2 = {"ID Mặt hàng","Tên mặt hàng","Giá bán"};
	Object[] row2 = new Object[3];
	DefaultTableModel model2 = new DefaultTableModel();
	private String com1 = "Tên nhà cung cấp";
	private String com2 = "Số điện thoại";
	private String com3 = "Email";
	private String com4 = "Thông tin liên hệ";
	private String com5 = "Thêm 1 sản phẩm vào cung cấp";
	private static Statement st;
	private static ResultSet rs;
	private static String sql;
	/**
	 * Launch the application.
	 */
//	private static final String url = "jdbc:mysql://localhost";
//	private static final String user = "root"; 
//	private static final String password = "sieunhan";
//	private static java.sql.Connection con;
	private JTextField textField_2;
	
	
//	public static void main(String args[]) {
//	try {
//    	con = DriverManager.getConnection(url, user, password);
//        System.out.println("Connect Success!");
//        Statement sts;
//        sts = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        sts.executeUpdate ("Use managedatabase;");
//        SearchAndFixSupply lr = new SearchAndFixSupply(sts);
//    	lr.frmTmKimChnh.setVisible(true);
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		}	
//	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public SearchAndFixSupply(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTmKimChnh = new JFrame();
		frmTmKimChnh.setTitle("Tìm kiếm, chỉnh sửa thông tin các nhà cung cấp.");
		//frmTmKimChnh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTmKimChnh.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmTmKimChnh.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmTmKimChnh.getContentPane().setLayout(null);
		
		JLabel lblTnSnPhm = new JLabel("Tên hoặc mã nhà cung cấp :");
		lblTnSnPhm.setBounds(31, 70, 217, 14);
		frmTmKimChnh.getContentPane().add(lblTnSnPhm);
		
		textField = new JTextField();
		textField.setBounds(258, 67, 396, 20);
		frmTmKimChnh.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filter();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filter();
			}
         

			
        });
		
		//TableValues tv = new TableValues();
		model.setColumnIdentifiers(colump);
		table = new JTable();
		table.setModel(model);
		table.setBounds(10, 69, 685, 268);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(31, 119, 1291, 416);		
		frmTmKimChnh.getContentPane().add(jsp);
		//table.setRowSorter(new TableRowSorter<DefaultTableModel>(model));
		sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc;";
		refress(sql);
		
		JButton btnNewButton = new JButton("Chỉnh sửa thông tin.");
		btnNewButton.setBounds(609, 596, 200, 39);
		frmTmKimChnh.getContentPane().add(btnNewButton);
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomSuaClick();
					sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc;";
					refress(sql);
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttomSuaClick();
                sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc;";
                refress(sql);
            }
        });
		
		
		JLabel lblThngTinMi = new JLabel("Thông tin mới :");
		lblThngTinMi.setBounds(31, 608, 120, 14);
		frmTmKimChnh.getContentPane().add(lblThngTinMi);
		
		textField_1 = new JTextField();
		textField_1.setBounds(157, 608, 333, 20);
		frmTmKimChnh.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filter2();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filter2();
			}
         

			
        });
		
		JLabel lblChnhSa = new JLabel("Chỉnh sửa :");
		lblChnhSa.setBounds(31, 583, 120, 14);
		frmTmKimChnh.getContentPane().add(lblChnhSa);
		
		comboBox = new JComboBox();
		comboBox.setBounds(157, 580, 333, 20);
		frmTmKimChnh.getContentPane().add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[]{com1, com2, com3, com4, com5} ));
		
		JButton btnXaMtHng = new JButton("Xóa nhà cung cấp");
		btnXaMtHng.setIcon(null);
		btnXaMtHng.setBounds(950, 596, 200, 39);
		frmTmKimChnh.getContentPane().add(btnXaMtHng);
		
		model2.setColumnIdentifiers(colump2);
		table2 = new JTable();
		table2.setModel(model2);
		table2.setBounds(157, 643, 333, 51);
		JScrollPane scrollPane = new JScrollPane(table2);
		scrollPane.setBounds(157, 643, 333, 51);
		sql = "Select Id_MatHang, Ten_MH, Gia_Ban from mat_hang;";
		refress2(sql);
		frmTmKimChnh.getContentPane().add(scrollPane);
		
		textField_2 = new JTextField();
		textField_2.setBounds(31, 674, 86, 20);
		frmTmKimChnh.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblGiBn = new JLabel("Giá nhập vào :");
		lblGiBn.setBounds(31, 649, 86, 14);
		frmTmKimChnh.getContentPane().add(lblGiBn);
		btnXaMtHng.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomXoaClick();
					sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc;";
	                refress(sql);
				}
			}
		});
		btnXaMtHng.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttomXoaClick();
                sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc;";
                refress(sql);
            }
        });
	}
	
	private void refress2(String sql){
		int tempI = model2.getRowCount();
		for (int i = tempI-1; i >= 0; i--) {
			model2.removeRow(i);
		}
		
		try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	row2[0] = rs.getString("ID_MatHang");
            	row2[1] = rs.getString("Ten_MH");
            	row2[2] = rs.getString("Gia_Ban");
            	model2.addRow(row2);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void refress(String sql){
		int tempI = model.getRowCount();
		for (int i = tempI-1; i >= 0; i--) {
			model.removeRow(i);
		}
		
		try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	row[0] = rs.getString("ID_NCC");
            	row[1] = rs.getString("Ten_NCC");
            	row[2] = rs.getString("SDT");
            	row[3] = rs.getString("Mail");
            	row[4] = rs.getString("TTLH");
            	model.addRow(row);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void buttomXoaClick(){
		int rows = table.getSelectedRow();
		if(rows < 0){
			JOptionPane.showMessageDialog(null, "Hãy chọn nhà cung cấp muốn sửa! bằng cách click vào hàng tương ứng");
			return;
		}	
		try {
			String id = (String) model.getValueAt(rows, 0);
			sql = "DELETE FROM cung_cap WHERE ID_NCC = '"+ id +"';";
			st.executeUpdate(sql);
			sql = "DELETE FROM ncc WHERE Id_NCC = '"+ id +"';";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Đã xóa nhà cung cấp!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void buttomSuaClick(){
		int rows = table.getSelectedRow();
		if(rows < 0){
			JOptionPane.showMessageDialog(null, "Hãy chọn nhà cung cấp bằng cách click vào dòng tương ứng!");
			return;
		}
		String id = (String) model.getValueAt(rows, 0);
		//String ncc = (String) model.getValueAt(rows, 4);
     	String chose = comboBox.getSelectedItem().toString();
     	String newIn = textField_1.getText();
     	//System.out.println("|"+name+"|"+id+"|");
     	if(newIn.length() == 0){
     		JOptionPane.showMessageDialog(null, "Thông tin mới không được để trống!");
     	}
     	else{     		
     		ResultSet rs;
			try {
				if(chose == com1){
					sql = "UPDATE ncc SET Ten_NCC = '" + newIn + "' WHERE ID_NCC = '" + id +"';";
					st.executeUpdate (sql);
					JOptionPane.showMessageDialog(null, "Tên nhà cung cấp đã được chỉnh sửa!");
					}
				else if(chose == com3){
					if(checkMail(newIn) == 0){
						JOptionPane.showMessageDialog(null, "Mai phải là địa chỉ hợp lệ! \n Theo dạng: phucld95@gmail.com");
					}
					else{
						sql = "UPDATE ncc SET Mail = '" + newIn + "' WHERE ID_NCC = '" + id +"';";
						st.executeUpdate (sql);
						JOptionPane.showMessageDialog(null, "E-mail nhà cung cấp đã được chỉnh sửa!");
					}
				}
				else if(chose == com4){
					sql = "UPDATE ncc SET TTLH = '" + newIn + "' WHERE ID_NCC = '" + id +"';";
					st.executeUpdate (sql);
					JOptionPane.showMessageDialog(null, "Địa chỉ nhà cung cấp đã được chỉnh sửa!");
				}
				else if(chose == com5){
					int rows2 = table2.getSelectedRow();
					if(rows2 < 0){
						JOptionPane.showMessageDialog(null, "Hãy chọn mặt hàng bằng cách click vào dòng tương ứng!");
					}
					else{
						String price = textField_2.getText();
						if(price.length() < 4){
							JOptionPane.showMessageDialog(null, "Giá tiền phải lớn hơn 1000!");
						}
						else{
							String idmh = (String) model2.getValueAt(rows2, 0);
							sql = "INSERT INTO cung_cap VALUES ('"+ idmh +"','"+ id+"','"+ price +"');";
							st.executeUpdate (sql);
							JOptionPane.showMessageDialog(null, "Mặt hàng đã được thêm vào nhà cung cấp!");
						}
					}
				}
				else{
					if(checkString(newIn) == 0){
							JOptionPane.showMessageDialog(null, "Dữ liệu mới phải là 1 số!");
						}
						else{
							if(chose == com2){
								if(newIn.length() > 11){
									JOptionPane.showMessageDialog(null, "Số điện thoại phải hợp lệ!");
								}
								else{
									sql = "UPDATE ncc SET SDT = '" + newIn + "' WHERE ID_NCC = '" + id +"';";
									st.executeUpdate (sql);
									JOptionPane.showMessageDialog(null, "Số điện thoại đã được chỉnh sửa!");
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
	
	private int checkMail(String s){
		if(s.endsWith(".com") == false && s.endsWith(".Com") == false && s.endsWith(".COM") == false){
			return 0;
		}
		if(s.indexOf("@") == -1){
			return 0;
		}
		return 1;
	}

	public static int checkString(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) > '9' || s.charAt(j) < '0') return 0;
		}
		return 1;
	}
	
	public void filter() {
        //TableRowSorter tableRowSort = (TableRowSorter)table.getRowSorter();
        String text = textField.getText();
        //tableRowSort.setRowFilter(RowFilter.regexFilter(textField.getText(), 0,1));  
        sql = "select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc where( substring(Ten_NCC from 1 for length('" + text+ "')) = '" + text +"' or ID_NCC = '" + text + "');";
        
        refress(sql);
    }
	public void filter2() {
        //TableRowSorter tableRowSort = (TableRowSorter)table.getRowSorter();
        String chose = comboBox.getSelectedItem().toString();
        if(chose == com5){
		String text = textField_1.getText();
        //tableRowSort.setRowFilter(RowFilter.regexFilter(textField.getText(), 0,1));  
        sql = "select Id_MatHang, Ten_MH, Gia_Ban from mat_hang where( substring(Ten_MH from 1 for length('" + text+ "')) = '" + text +"' or ID_MatHang = '" + text + "');"; 
        refress2(sql);
        }
    }
}
