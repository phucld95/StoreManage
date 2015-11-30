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

public class SearchAndFixProduce {

	public JFrame frmTmKimChnh;
	private JTextField textField;
	private JComboBox comboBox;
	private JTextField textField_1;
	private JTable table;
	Object[] colump = {"ID mặt hàng","Tên sản phẩm","Tên nhóm hàng","Số lượng đang có", "Tên nhà cung cấp", "Giá nhập vào", "Giá bán ra"};
	Object[] row = new Object[7];
	DefaultTableModel model = new DefaultTableModel();
	private String com1 = "Tên sản phẩm";
	private String com2 = "Số lượng đang có";
	private String com3 = "Giá bán ra";
	private String com4 = "Giá nhập vào";
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
//	
//	
//	public static void main(String args[]) {
//	try {
//    	con = DriverManager.getConnection(url, user, password);
//        System.out.println("Connect Success!");
//        Statement sts;
//        sts = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        sts.executeUpdate ("Use managedatabase;");
//        SearchAndFixProduce lr = new SearchAndFixProduce(sts);
//    	lr.frmTmKimChnh.setVisible(true);
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		}	
//	}

	/**
	 * Create the application.
	 */
	public SearchAndFixProduce(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTmKimChnh = new JFrame();
		frmTmKimChnh.setTitle("Tìm kiếm, chỉnh sửa thông tin sản phẩm.");
		//frmTmKimChnh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTmKimChnh.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmTmKimChnh.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmTmKimChnh.getContentPane().setLayout(null);
		
		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm hoặc mã sản phẩm :");
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
		sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC;";
		refress(sql);
		
		JButton btnNewButton = new JButton("Chỉnh sửa thông tin.");
		btnNewButton.setBounds(609, 596, 200, 39);
		frmTmKimChnh.getContentPane().add(btnNewButton);
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomSuaClick();
					sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC;";
					refress(sql);
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttomSuaClick();
                sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC;";
                refress(sql);
            }
        });
		
		
		JLabel lblThngTinMi = new JLabel("Thông tin mới :");
		lblThngTinMi.setBounds(31, 632, 120, 14);
		frmTmKimChnh.getContentPane().add(lblThngTinMi);
		
		textField_1 = new JTextField();
		textField_1.setBounds(157, 629, 333, 20);
		frmTmKimChnh.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblChnhSa = new JLabel("Chỉnh sửa :");
		lblChnhSa.setBounds(31, 599, 120, 14);
		frmTmKimChnh.getContentPane().add(lblChnhSa);
		
		comboBox = new JComboBox();
		comboBox.setBounds(157, 596, 333, 20);
		frmTmKimChnh.getContentPane().add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[]{com1, com2, com3, com4} ));
		
		JButton btnXaMtHng = new JButton("Xóa mặt hàng");
		btnXaMtHng.setIcon(null);
		btnXaMtHng.setBounds(950, 596, 200, 39);
		frmTmKimChnh.getContentPane().add(btnXaMtHng);
		btnXaMtHng.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomXoaClick();
					sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC;";
	                refress(sql);
				}
			}
		});
		btnXaMtHng.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttomXoaClick();
                sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC;";
                refress(sql);
            }
        });
	}
	
	private void refress(String sql){
		int tempI = model.getRowCount();
		for (int i = tempI-1; i >= 0; i--) {
			model.removeRow(i);
		}
		
		try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	row[0] = rs.getString("ID_MatHang");
            	row[1] = rs.getString("Ten_MH");
            	row[2] = rs.getString("Ten_Nhomhang");
            	row[3] = rs.getString("Soluong");
            	row[4] = rs.getString("Ten_NCC");
            	row[5] = rs.getString("Gia_Nhap");
            	row[6] = rs.getString("Gia_Ban");
            	model.addRow(row);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void buttomXoaClick(){
		int rows = table.getSelectedRow();
		if(rows >= 0){
			model.removeRow(rows);
			JOptionPane.showMessageDialog(null, "Đã xóa mặt hàng!");
		}
		else{
			JOptionPane.showMessageDialog(null, "Hãy chọn hàng muốn xóa!");
		}
	}
	
	
	private void buttomSuaClick(){
		int rows = table.getSelectedRow();
		if(rows < 0){
			return;
		}
		String id = (String) model.getValueAt(rows, 0);
		String ncc = (String) model.getValueAt(rows, 4);
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
					sql = "UPDATE mat_hang SET Ten_MH = '" + newIn + "' WHERE ID_MatHang = '" + id +"';";
					st.executeUpdate (sql);
					JOptionPane.showMessageDialog(null, "Tên sản phẩm đã được chỉnh sửa!");
					}
				else{
					if(checkString(newIn) == 0){
							JOptionPane.showMessageDialog(null, "Dữ liệu mới phải là 1 số!");
						}
						else{
							if(chose == com4){
								sql = "select ID_NCC from ncc where Ten_ncc = '" + ncc + "';";
								rs = st.executeQuery(sql);
								rs.next();
								ncc = rs.getString("ID_NCC");
								sql = "UPDATE cung_cap SET Gia_Nhap = '" + newIn + "' WHERE ID_MatHang = '" + id +"' and ID_NCC = '" + ncc +"';";
								st.executeUpdate (sql);
								JOptionPane.showMessageDialog(null, "Giá nhập sản phẩm đã được chỉnh sửa!");
							}
							if(chose == com3){
								sql = "UPDATE mat_hang SET Gia_Ban = '" + newIn + "' WHERE ID_MatHang = '" + id +"';";
								st.executeUpdate (sql);
								JOptionPane.showMessageDialog(null, "Giá bán sản phẩm đã được chỉnh sửa!");
							}
							if(chose == com2){
								sql = "UPDATE mat_hang SET Soluong = '" + newIn + "' WHERE ID_MatHang = '" + id +"';";
								st.executeUpdate (sql);
								JOptionPane.showMessageDialog(null, "Số lượng sản phẩm đã được chỉnh sửa!");
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
        sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC and( substring(Ten_mh from 1 for length('" + text+ "')) = '" + text +"' or mh.ID_MatHang = '" + text + "');";
        
        refress(sql);
    }
}
