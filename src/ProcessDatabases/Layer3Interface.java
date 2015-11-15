package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;

public class Layer3Interface {

	public JFrame frmTnhTin;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JLabel lblSumprice;
	timeSystem time = new timeSystem();

	
	
	Object[] colump = {"ID mặt hàng","Tên sản phẩm","Số lượng mua","Giá sản phẩm", "Thành tiền"};
	Object[] row = new Object[5];
	DefaultTableModel model = new DefaultTableModel();
	
	
	
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "sieunhan";
	private static java.sql.Connection con;
	private static Statement st;
	private static ResultSet rs;
	private static String sql;

	private static String nameP;
	private static String idP;
	private static String numP;
	private static int SumPrice = 0;
	private static int idPint;
	private static int numPint;
	private static int price;
	/**
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
		Layer3Interface lr = new Layer3Interface(st);
		lr.frmTnhTin.setVisible(true);
	}
	
	/**
	 * Create the application.
	 */
	public Layer3Interface(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTnhTin = new JFrame();
		frmTnhTin.setTitle("T\u00EDnh ti\u1EC1n");
		frmTnhTin.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmTnhTin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTnhTin.getContentPane().setLayout(null);
		frmTnhTin.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
//		 String title = "Title";
//		    boolean modal = true; // whether modal or not
//		    Dialog dlg=new Dialog(myframe, title, modal); 
		
		model.setColumnIdentifiers(colump);
		
		
		
		JLabel lblThanhTonHa = new JLabel("Thanh to\u00E1n");
		lblThanhTonHa.setForeground(new Color(255, 51, 0));
		lblThanhTonHa.setFont(new Font("iCiel Cucho Bold", Font.PLAIN, 70));
		lblThanhTonHa.setBounds(272, 3, 539, 114);
		frmTnhTin.getContentPane().add(lblThanhTonHa);
		
		JLabel lblNhnVin = new JLabel("Nh\u00E2n vi\u00EAn : ");
		lblNhnVin.setBounds(872, 21, 99, 14);
		frmTnhTin.getContentPane().add(lblNhnVin);
		
		JLabel lblIdNhnVin = new JLabel("ID nh\u00E2n vi\u00EAn : ");
		lblIdNhnVin.setBounds(872, 43, 99, 14);
		frmTnhTin.getContentPane().add(lblIdNhnVin);
		
		JLabel lblThiGian = new JLabel("Th\u1EDDi gian :");
		lblThiGian.setBounds(872, 68, 99, 14);
		frmTnhTin.getContentPane().add(lblThiGian);
		
		JLabel lblTnSnPhm = new JLabel("T\u00EAn s\u1EA3n ph\u1EA9m :");
		lblTnSnPhm.setBounds(171, 125, 159, 14);
		frmTnhTin.getContentPane().add(lblTnSnPhm);
		
		JLabel lblMSnPhm = new JLabel("M\u00E3 s\u1EA3n ph\u1EA9m :");
		lblMSnPhm.setBounds(171, 153, 159, 14);
		frmTnhTin.getContentPane().add(lblMSnPhm);
		
		JLabel lblSLng = new JLabel("S\u1ED1 l\u01B0\u1EE3ng :");
		lblSLng.setBounds(171, 187, 159, 14);
		frmTnhTin.getContentPane().add(lblSLng);
		
		textField = new JTextField();
		textField.setBounds(340, 122, 208, 20);
		frmTnhTin.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(340, 153, 353, 20);
		frmTnhTin.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(340, 184, 353, 20);
		frmTnhTin.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Thêm vào hóa đơn");
		btnNewButton.setBounds(771, 153, 200, 51);
		frmTnhTin.getContentPane().add(btnNewButton);
		
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomThemClick();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttomThemClick();
            }
        });
		
		JButton btnXaKhiHa = new JButton("Xóa");
		btnXaKhiHa.setBounds(1290, 393, 58, 56);
		frmTnhTin.getContentPane().add(btnXaKhiHa);	
		
		btnXaKhiHa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomXoaClick();
				}
			}
		});
		btnXaKhiHa.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttomXoaClick();
            }
        });
		
		
		//TableValues tv = new TableValues();
		table = new JTable();
		table.setModel(model);
		table.setBounds(45, 290, 1288, 301);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(41, 282, 1239, 292);		
		frmTnhTin.getContentPane().add(jsp);
		
		
		JButton btnThanhTon = new JButton("Thanh to\u00E1n");
		btnThanhTon.setBounds(528, 615, 271, 56);
		frmTnhTin.getContentPane().add(btnThanhTon);
		
		btnThanhTon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomThanhToanClick();
				}
			}
		});
		btnThanhTon.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttomThanhToanClick();
            }
        });
		
		JButton btnNewButton_1 = new JButton("Xem m\u00E3");
		btnNewButton_1.setBounds(574, 121, 119, 23);
		frmTnhTin.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					buttomKiemTraClick();
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buttomKiemTraClick();
            }
        });
		
		
		JLabel lblNewLabel = new JLabel("Tổng tiền");
		lblNewLabel.setBounds(171, 236, 141, 20);
		frmTnhTin.getContentPane().add(lblNewLabel);
		
		JLabel lblNamenv = new JLabel("nameNV");
		lblNamenv.setBounds(979, 21, 231, 14);
		frmTnhTin.getContentPane().add(lblNamenv);
		
		JLabel lblIdnv = new JLabel("idNV");
		lblIdnv.setBounds(981, 43, 229, 14);
		frmTnhTin.getContentPane().add(lblIdnv);
		
		JLabel lblTimenow = new JLabel("timeNow");
		lblTimenow.setBounds(981, 68, 229, 14);
		frmTnhTin.getContentPane().add(lblTimenow);		
		
		lblSumprice = new JLabel("SumPrice");
		lblSumprice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSumprice.setBounds(340, 230, 99, 28);
		frmTnhTin.getContentPane().add(lblSumprice);
		
		
		lblSumprice.setText(Integer.toString(SumPrice));
	}
	
	private void buttomThanhToanClick(){
		if(SumPrice == 0){
			JOptionPane.showMessageDialog(null, "Hóa đơn phải có giá trị trên 0đ !");
		}
		else{
			
		}
	}
		
	private void buttomXoaClick(){
		int rows = table.getSelectedRow();
		if(rows >= 0){
			row[0] = model.getValueAt(rows, 4);
			int price = ((Integer) row[0]).intValue();
			model.removeRow(rows);
			SumPrice -= price;
			lblSumprice.setText(Integer.toString(SumPrice));
		}
		else{
			JOptionPane.showMessageDialog(null, "Hãy chọn hàng muốn xóa!");
		}
	}
	
	private int kiemTra(String str){
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) < '0' || str.charAt(i) > '9'){
				return 0;
			}
		}
		return 1;
	}
	
	private void buttomThemClick(){
		
		idP = textField_1.getText();
		numP = textField_2.getText();
		if(idP.length() == 0 || numP.length() == 0){
			JOptionPane.showMessageDialog(null, "Nhập mã và số lượng sản phẩm trước khi thêm vào hóa đơn!");
		}
		else if(kiemTra(numP) == 0){
			JOptionPane.showMessageDialog(null, "Số lượng nhập vào phải là 1 số tự nhiên > 0!");
		}
		else{
			idPint = convertStringToIn(idP);
			numPint = convertStringToIn(numP);
			//System.out.println(idP);
			//System.out.println(numP);
			if(numPint < 1){
				JOptionPane.showMessageDialog(null, "Số lượng thanh toán phải lớn hơn 0!");
			}
			else{
				sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and mh.ID_MatHang = '" + idP + "';";
	        	ResultSet rs = null;
				try {
					rs = st.executeQuery(sql);
					rs.last();
					//System.out.print(rs.getRow());
					if(rs.getRow() == 0){
						JOptionPane.showMessageDialog(null, "Không có sản phẩm có mã cần thanh toán!");
					}
					else{
						Invoice iv = new Invoice(st);
						price = iv.tinh_tien(idPint, numPint);
						System.out.println("Trả về sau Invoice = " + price);
						if(price == -1){
							JOptionPane.showMessageDialog(null, "Không còn đủ lượng sản phẩm trong kho!");
						}						
						else if(price == 0){
							sql = String.format("select dkm.Id_KM, Ten_KM, ID_MatHang, Gia_KM from khuyen_mai km, duoc_khuyen_mai dkm where TGDR <= '%s' and TGKT >= '%s' and dkm.Id_KM = km.Id_KM and ID_MatHang = '%d';",
								"2015-10-11","2015-10-11",idPint);
//							sql = String.format("select dkm.Id_KM, Ten_KM, ID_MatHang, Gia_KM from khuyen_mai km, duoc_khuyen_mai dkm where TGDR <= '%s' and TGKT >= '%s' and dkm.Id_KM = km.Id_KM and ID_MatHang = '%d';",
//									time.Date(),time.Date(),idPint);
							rs = st.executeQuery(sql);
							ChoseEven ce = new ChoseEven(rs);										
							ce.frmSKinKhuyn.addWindowListener(new WindowAdapter() {
								@Override
								public void windowDeactivated(WindowEvent e) {
									int j = ce.check();
									ce.temp = 0;
									specialElse(idPint, j);
								}							
							});
						}
						else{
							sql = "select ID_MatHang, Ten_MH, Gia_Ban from mat_hang where ID_MatHang = '" + idP + "';";
							try {
								rs = st.executeQuery(sql);
								rs.next();
								String price = rs.getString("Gia_Ban");
								getInTable(rs, idPint, numPint, price);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	
			
			}
		}
	}
	
	private void specialElse(int id, int km){
		sql = "select Gia_KM from duoc_khuyen_mai dkm where ID_MatHang = '" + id +"' and Id_KM = " + km + ";";
		try {
			rs = st.executeQuery(sql);
			rs.next();
			String price = rs.getString("Gia_KM");
			sql = "select ID_MatHang, Ten_MH, Gia_Ban from mat_hang where ID_MatHang = '" + idP + "';";
			rs = st.executeQuery(sql);
			getInTable(rs, idPint, numPint, price);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void getInTable(ResultSet rst ,int ids, int numb, String gia){
		try {
			rst.beforeFirst();
			rst.next();
			row[0] = rst.getString("ID_MatHang");
			row[1] = rst.getString("Ten_MH");
			row[3] = gia;
			row[2] = numb;
			int price = convertStringToIn(gia)*numb;
			row[4] = price;
			model.addRow(row);
			SumPrice += price;
			lblSumprice.setText(Integer.toString(SumPrice));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	private void buttomKiemTraClick(){
		nameP = textField.getText();
		if(nameP.length() != 0){
    		sql = "Select mh.ID_MatHang, Ten_MH, Ten_NCC, Ten_Nhomhang, Soluong, Gia_Nhap, Gia_Ban from mat_hang mh, ncc, cung_cap cc, nhomhang nh, thuoc_nhom tn where mh.ID_MatHang = cc.ID_MatHang and cc.Id_NCC = ncc.ID_NCC and mh.ID_MatHang = tn.ID_MatHang and tn.ID_NhomHang = nh.ID_NhomHang and Ten_MH = '" + nameP + "';";
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
		else{
			JOptionPane.showMessageDialog(null, "Nhập tên mặt hàng trước khi ấn tìm kiếm!");
		}
	}
}
