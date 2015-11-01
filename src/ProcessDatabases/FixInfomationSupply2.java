package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class FixInfomationSupply2 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	
	private static String cb11 = "Tên nhà cung cấp.";
	private static String cb12 = "Địa chỉ liên hệ.";
	private static String cb13 = "Số điện thoại liên hệ.";
	private static String cb14 = "Mail liên hệ.";
	private static String chose = new String();
	private static String newIn = new String();
	private static String id;
	private static String name;
	private static String sql;
	private static ResultSet rs;
	private static Statement st;
	

	/**
	 * Create the application.
	 */
	public FixInfomationSupply2(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 486, 300);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.setTitle("Chỉnh sửa thông tin nhà cung cấp.");
		frame.getContentPane().setLayout(null);
		
		JLabel lblTnNhCung = new JLabel("T\u00EAn nh\u00E0 cung c\u1EA5p :");
		lblTnNhCung.setBounds(25, 36, 128, 14);
		frame.getContentPane().add(lblTnNhCung);
		
		textField = new JTextField();
		textField.setBounds(151, 33, 195, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblMNhCung = new JLabel("M\u00E3 số nh\u00E0 cung c\u1EA5p :");
		lblMNhCung.setBounds(25, 68, 128, 14);
		frame.getContentPane().add(lblMNhCung);
		
		textField_1 = new JTextField();
		textField_1.setBounds(151, 65, 195, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Ki\u1EC3m tra");
		btnNewButton.setBounds(356, 42, 89, 33);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	name = textField.getText();
            	id = textField_1.getText();
            	
            	//System.out.println("|"+name+"|"+id+"|");
            	
            	if(name.length() != 0){
            		sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc where Ten_NCC = '" + name + "';";            		ResultSet rs;
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
            		sql = "Select Id_NCC, Ten_NCC, SDT, Mail, TTLH from ncc where Id_NCC = '" + id + "';";
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
		
		JLabel lblChnhSa = new JLabel("S\u1EEDa th\u00F4ng tin");
		lblChnhSa.setBounds(25, 123, 128, 14);
		frame.getContentPane().add(lblChnhSa);
		
		comboBox = new JComboBox();
		comboBox.setBounds(151, 120, 252, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {cb11,cb12,cb13,cb14}));
		frame.getContentPane().add(comboBox);
		
		JLabel lblSaThnh = new JLabel("S\u1EEDa th\u00E0nh");
		lblSaThnh.setBounds(25, 164, 128, 14);
		frame.getContentPane().add(lblSaThnh);
		
		textField_2 = new JTextField();
		textField_2.setBounds(151, 161, 252, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Ch\u1EC9nh s\u1EEDa\r\n");
		btnNewButton_1.setBounds(145, 211, 209, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickButtom();
			}
		});
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					clickButtom();
				}
			}
		});
	}
	
	private void clickButtom(){
		name = textField.getText();
    	id = textField_1.getText();
    	chose = comboBox.getSelectedItem().toString();
    	newIn = textField_2.getText();
    	//System.out.println("|"+name+"|"+id+"|");
    	
    	if(newIn.length() == 0){
    		JOptionPane.showMessageDialog(null, "Hãy nhập thông tin mới trước khi chọn chỉnh sửa!");
    	}
    	else{
    		if(name.length() != 0 && id.length() == 0){
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
						JOptionPane.showMessageDialog(null, "Nhập mã nhà cung cấp để thực hiện chỉnh sửa!");
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
						if(chose == cb13){
							if(checkString(newIn) == 0){
								JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ!");
							}
							else{
								sql = "UPDATE ncc SET SDT = '" + newIn + "' WHERE Id_NCC = '" + id +"';";
								st.executeUpdate(sql);
							}
						}
						if(chose == cb14){
							sql = "UPDATE ncc SET Mail = '" + newIn + "' WHERE Id_NCC = '" + id +"';";
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
	
	public static int checkString(String s){
		int j;
		for (j= 0; j< s.length(); j++){
			if(s.charAt(j) == '+') continue;
			if(s.charAt(j) > '9' || s.charAt(j) < '0') return 0;
		}
		return 1;
	}
}
