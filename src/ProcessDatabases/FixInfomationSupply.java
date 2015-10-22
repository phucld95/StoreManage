package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class FixInfomationSupply {

	
	
	private static String cb11 = "Tên nhà cung cấp.";
	private static String cb12 = "Địa chỉ liên hệ.";
	private static String chose = new String();
	private static String newIn = new String();
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static String id;
	private static String name;
	private static String sql;
	private static ResultSet rs;
	private static Statement st;

	
	
	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public FixInfomationSupply(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 493, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		
		
		JLabel lblNhpTnSn = new JLabel("Nhập tên nhà cung cấp hoặc mã số:");
		lblNhpTnSn.setBounds(10, 34, 293, 14);
		frame.getContentPane().add(lblNhpTnSn);
		
		JLabel lblTnSnPhm = new JLabel("Tên NCC : ");
		lblTnSnPhm.setBounds(10, 66, 85, 14);
		frame.getContentPane().add(lblTnSnPhm);
		
		JLabel lblId = new JLabel("ID NCC : ");
		lblId.setBounds(284, 66, 67, 14);
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(93, 63, 168, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(361, 63, 86, 20);
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
            		sql = "Select Id_NCC, Ten_NCC, TTLH from ncc where Ten_NCC = '" + name + "';";            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có nhà sản xuất có tên cần tìm!");
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
            		sql = "Select Id_NCC, Ten_NCC, TTLH from ncc where Id_NCC = '" + id + "';";            		ResultSet rs;
					try {
						rs = st.executeQuery(sql);
						rs.last();
						//System.out.print(rs.getRow());
						if(rs.getRow() == 0){
							JOptionPane.showMessageDialog(null, "Không có nhà sản xuất có mã cần tìm!");
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
            		JOptionPane.showMessageDialog(null, "Hãy nhập tên nhà cung cấp hoặc ID của nhà cung cấp trước khi bấm tìm kiếm!");
            	}
            }
        });
		
		JLabel lblNewLabel = new JLabel("Sửa thông tin");
		lblNewLabel.setBounds(10, 134, 106, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 134, 235, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {cb11,cb12}));
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
            		JOptionPane.showMessageDialog(null, "Hãy nhập thông tin mới trước khi chọn chỉnh sửa!");
            	}
            	else{
            		if(name.length() != 0){
                		sql = "Select Id_NCC, Ten_NCC, TTLH from ncc where Ten_NCC = '" + name + "';";            		ResultSet rs;
    					try {
    						rs = st.executeQuery(sql);
    						rs.last();
    						//System.out.print(rs.getRow());
    						if(rs.getRow() == 0){
    							JOptionPane.showMessageDialog(null, "Không có nhà sản xuất có tên cần tìm!");
    						}
    						else{
    							rs.beforeFirst();
    							TableDatabase stt = new TableDatabase(rs);
    							JOptionPane.showMessageDialog(null, "Nhập mã sản phẩm để thực hiện chỉnh sửa!");
    						}
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}                 
                	}
                	else if(id.length() != 0){
                		sql = "Select Id_NCC, Ten_NCC, TTLH from ncc where Id_NCC = '" + id + "';";            		ResultSet rs;
    					try {
    						rs = st.executeQuery(sql);
    						rs.last();
    						//System.out.print(rs.getRow());
    						if(rs.getRow() == 0){
    							JOptionPane.showMessageDialog(null, "Không có nhà sản xuất có mã cần tìm!");
    						}
    						else{
    							if(chose == cb11){
    								sql = "UPDATE ncc SET Ten_NCC = '" + newIn + "' WHERE Id_NCC = '" + id +"';";
    								st.executeUpdate(sql);
    							}
    							if(chose == cb12){
    								sql = "UPDATE ncc SET TTLH = '" + newIn + "' WHERE Id_NCC = '" + id +"';";
    								st.executeUpdate(sql);
    							}
    							JOptionPane.showMessageDialog(null, "Thông tin sản phẩm đã được chỉnh sửa!");
    						}
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					} 
                	}
                	if(id.length() == 0 && name.length() == 0){
                		JOptionPane.showMessageDialog(null, "Hãy nhập tên nhà cung cấp hoặc ID của nhà cung cấp trước khi bấm sửa!");
                	}
            	}
            }
        });
	}

}
