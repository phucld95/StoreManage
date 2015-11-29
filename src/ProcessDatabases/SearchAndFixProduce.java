package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class SearchAndFixProduce {

	private JFrame frmTmKimChnh;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
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
        SearchAndFixProduce lr = new SearchAndFixProduce(sts);
    	lr.frmTmKimChnh.setVisible(true);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}	
	}

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
		frmTmKimChnh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		Object[] colump = {"ID mặt hàng","Tên sản phẩm","Tên nhóm hàng","Số lượng đang có", "Tên nhà cung cấp", "Giá nhập vào", "Giá bán ra"};
		Object[] row = new Object[7];
		DefaultTableModel model = new DefaultTableModel();
		//TableValues tv = new TableValues();
		model.setColumnIdentifiers(colump);
		JTable table = new JTable();
		table.setModel(model);
		table.setBounds(10, 69, 685, 268);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(31, 119, 1291, 416);		
		frmTmKimChnh.getContentPane().add(jsp);
		sql = "select mh.ID_MatHang, Ten_MH, Ten_Nhomhang, Soluong, Ten_NCC, Gia_Nhap, Gia_Ban from ncc, cung_cap cc, mat_hang mh, nhomhang nh, thuoc_nhom tn where tn.ID_MatHang = mh.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and cc.ID_MatHang = mh.ID_MatHang and cc.Id_NCC = ncc.Id_NCC;";
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
		final TableRowSorter<TableModel> sorter =
                new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
		
		JButton btnNewButton = new JButton("Chỉnh sửa thông tin.");
		btnNewButton.setBounds(609, 596, 200, 39);
		frmTmKimChnh.getContentPane().add(btnNewButton);
		
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(157, 596, 333, 20);
		frmTmKimChnh.getContentPane().add(comboBox);
		
		JButton btnXaMtHng = new JButton("Xóa mặt hàng");
		btnXaMtHng.setIcon(null);
		btnXaMtHng.setBounds(950, 596, 200, 39);
		frmTmKimChnh.getContentPane().add(btnXaMtHng);
	}
}
