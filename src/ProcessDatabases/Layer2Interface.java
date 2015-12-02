package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Layer2Interface implements ActionListener {

	public static JFrame frmStoreManager;
	public static String chose = new String();
	
	public static String com11 = "Xem, chỉnh sửa mặt hàng.";
	public static String com13 = "Thêm 1 mặt hàng vào cửa hàng.";
	public static String com15 = "Những sản phẩm sắp hết hàng";
	
	public static String com21 = "Xem, chỉnh sửa nhà cung cấp.";
	public static String com22 = "Thêm 1 nhà cung cấp.";
	
	public static String com31 = "Các nhóm hàng đang có.";
	public static String com32 = "Thêm 1 nhóm hàng mới.";
	public static String com33 = "Thêm các mặt hàng vào 1 nhóm.";
	public static String com34 = "Xem các mặt hàng trong 1 nhóm.";
	
	public static String com41 = "Xem tất cả các sự kiện.";
	public static String com42 = "Xem chi tiết 1 sự kiện.";
	public static String com43 = "Tạo thêm 1 sự kiện.";
	public static String com44 = "Thêm các sản phẩm vào 1 sự kiện.";
	public static String com45 = "Xóa 1 sự kiện.";
	
	public static String com51 = "Xem tất cả các người dùng.";
	public static String com52 = "Thêm 1 người dùng mới.";
	public static String com53 = "Xóa 1 người dùng.";
	public static String com54 = "Sửa thông tin của 1 người dùng.";
	
	public static String com61 = "Xem doanh số trung bình của 1 nhân viên.";
	public static String com62 = "Xem doanh thu của cửa hàng.";
	//public static String com63 = "Xem chi tiết 1 hóa đơn.";
	public static String com64 = "Xem bảng xếp hạng doanh thu nhân viên.";
	
	private static Statement st;
	/**
	 * Create the application.
	 */
	public Layer2Interface(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ProductManage pma = new ProductManage(st);
		SupplyManage sma = new SupplyManage(st);
		ProductGroupManage pgm = new ProductGroupManage(st);
		EvenManage em = new EvenManage(st);
		AccManage am = new AccManage(st);
		frmStoreManager = new JFrame();
		frmStoreManager.setTitle("Store manager");
		frmStoreManager.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frmStoreManager.setBounds(100, 100, 529, 330);
		frmStoreManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStoreManager.getContentPane().setLayout(null);
		frmStoreManager.setResizable(false);
		
		JLabel lblStoreManager = new JLabel("Store Manager");
		lblStoreManager.setForeground(Color.DARK_GRAY);
		lblStoreManager.setFont(new Font("iCiel Cucho Bold", Font.PLAIN, 34));
		lblStoreManager.setBounds(157, 25, 214, 50);
		frmStoreManager.getContentPane().add(lblStoreManager);
		
		JLabel lblQunLMt = new JLabel("Qu\u1EA3n l\u00FD m\u1EB7t h\u00E0ng");
		lblQunLMt.setBounds(10, 84, 146, 14);
		frmStoreManager.getContentPane().add(lblQunLMt);
		/*
		 *	ComboBox Các mặt hàng đang kinh doanh.
		 */
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[]{com11, com13, com15} ));
		comboBox.setToolTipText("");
		comboBox.setBounds(180, 81, 230, 20);
		frmStoreManager.getContentPane().add(comboBox);
		
		JLabel lblQunLNh = new JLabel("Qu\u1EA3n l\u00FD nh\u00E0 ph\u00E2n ph\u1ED1i");
		lblQunLNh.setBounds(10, 114, 146, 14);
		frmStoreManager.getContentPane().add(lblQunLNh);
		
		JLabel lblNewLabel = new JLabel("Qu\u1EA3n l\u00FD nh\u00F3m h\u00E0ng");
		lblNewLabel.setBounds(10, 143, 146, 14);
		frmStoreManager.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quản lý sự kiện ");
		lblNewLabel_1.setBounds(10, 173, 146, 14);
		frmStoreManager.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quản lý người dùng ");
		lblNewLabel_2.setBounds(10, 203, 146, 14);
		frmStoreManager.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quản lý doanh số ");
		lblNewLabel_3.setBounds(10, 233, 146, 14);
		frmStoreManager.getContentPane().add(lblNewLabel_3);
		/*
		 * 	ComboBox Các nhà cung cấp
		 */
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {com21,com22}));
		comboBox_1.setBounds(180, 111, 230, 20);
		frmStoreManager.getContentPane().add(comboBox_1);
		
		/*
		 * 	ComboBox tra cứu nhóm hàng.
		 */
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {com31,com32,com33,com34}));
		comboBox_2.setBounds(180, 140, 230, 20);
		frmStoreManager.getContentPane().add(comboBox_2);
		
		/*
		 *	Combobox Tra cứu sự kiện.
		 */
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {com41,com42, com43,com44,com45}));
		comboBox_3.setBounds(180, 170, 230, 20);
		frmStoreManager.getContentPane().add(comboBox_3);
		/*
		 * Combobox quản lý người dùng.
		 */
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {com51,com52, com53,com54}));
		comboBox_4.setBounds(180, 200, 230, 20);
		frmStoreManager.getContentPane().add(comboBox_4);
		/*
		 * Combobox quản lý doanh số
		 */
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {com61,com62,com64}));
		comboBox_5.setBounds(180, 230, 230, 20);
		frmStoreManager.getContentPane().add(comboBox_5);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.setBounds(420, 81, 41, 20);
		frmStoreManager.getContentPane().add(btnNewButton);
		
		/*
		 * Bắt sự kiện chọn fonction trong comboBox quản lý mặt hàng
		 */
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chose = comboBox.getSelectedItem().toString();
                if(chose == com11){
                	pma.searchProduct();
                }
               
                if(chose == com13){
                	pma.addNewProduct();
                }
                
                if(chose == com15){
                	pma.showProductNeedAdd();
                }
            	//JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString());
            }
        });
		
		JButton button = new JButton(">");
		button.setBounds(420, 111, 41, 20);
		frmStoreManager.getContentPane().add(button);
		
		/*
		 * Bắt sự kiện chọn function trong conbobox quản lý nhà cung cấp
		 */
		button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chose = comboBox_1.getSelectedItem().toString();
                if(chose == com21){
                	sma.fixInfomationSupply();
                }
                if(chose == com22){
                	sma.addNewSupply();
                }
            	//JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString());
            }
        });
		
		JButton button_1 = new JButton(">");
		button_1.setBounds(420, 140, 41, 20);
		frmStoreManager.getContentPane().add(button_1);
		
		/*
		 * Bắt sự kiện chọn function trong conbobox quản lý nhóm hàng
		 */
		button_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chose = comboBox_2.getSelectedItem().toString();
                if(chose == com31){
                	pgm.ShowAllGroup();
                }
                if(chose == com32){
                	pgm.addNewGroup();
                }
                if(chose == com33){
                	pgm.addProductToGroup();
                }
                if(chose == com34){
                	pgm.showProdutInGroup();
                }
            	//JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString());
            }
        });
		
		JButton button_2 = new JButton(">");
		button_2.setBounds(420, 170, 41, 20);
		frmStoreManager.getContentPane().add(button_2);
		
		/*
		 * Bắt sự kiện chọn function trong conbobox quản lý sự kiện
		 */
		button_2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chose = comboBox_3.getSelectedItem().toString();
                if(chose == com41){
                	try {
						em.ShowAllEven();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                if(chose == com42){
                	em.showOneEven();
                }
                if(chose == com43){
                	em.createEven();
                }
                if(chose == com44){
                	em.addProduceInEven();
                }
                if(chose == com45){
                	em.deleteEven();
                }
            	//JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString());
            }
        });
		JButton button_3 = new JButton(">");
		button_3.setBounds(420, 200, 41, 20);
		frmStoreManager.getContentPane().add(button_3);
		
		/*
		 * Bắt sự kiện chọn function trong conbobox quản lý người dùng
		 */
		button_3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chose = comboBox_4.getSelectedItem().toString();
                if(chose == com51){
                	try {
						am.ShowAllAccount();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                if(chose == com52){
                	am.addNewAccount();
                }
                if(chose == com53){
                	am.deleteAccount();
                }
                if(chose == com54){
                	am.fixAccount();
                }
            	//JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString());
            }
        });
		/*
		 * buttom bắt sự kiện quản lý doanh số.
		 */
		RevenueManage rms = new RevenueManage(st);
		JButton button_4 = new JButton(">");
		button_4.setBounds(420, 230, 41, 20);
		frmStoreManager.getContentPane().add(button_4);
		button_4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chose = comboBox_5.getSelectedItem().toString();
                if(chose == com61){
                	rms.showRevenueOf1Account();
                }
                if(chose == com62){
                	rms.showRevenueOfStore();
                }
                if(chose == com64){
                	rms.showBestAccount();
                }
            	//JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString());
            }
        });
	}

	/*
	 * button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==button) System.exit(0); //nếu event này có source do button sinh ra
            }
        });
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, comboBox_1.getSelectedItem().toString);
	}
}
