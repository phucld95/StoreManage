package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class SearchAndEditProduct {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	private String name = new String();
	private String id = new String();
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchAndEditProduct window = new SearchAndEditProduct();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchAndEditProduct() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNhpTnSn = new JLabel("Nhập thông tin sản phẩm muốn thêm : ");
		lblNhpTnSn.setBounds(10, 34, 293, 14);
		frame.getContentPane().add(lblNhpTnSn);
		
		JLabel lblTnSnPhm = new JLabel("Tên SP : ");
		lblTnSnPhm.setBounds(10, 66, 85, 14);
		frame.getContentPane().add(lblTnSnPhm);
		
		JLabel lblId = new JLabel("Giá nhập: ");
		lblId.setBounds(10, 94, 74, 14);
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(93, 63, 168, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(94, 91, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Chỉnh sửa thông tin");
		lblNewLabel.setBounds(10, 134, 106, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 134, 235, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Chỉnh sửa");
		btnNewButton.setBounds(172, 209, 131, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField_2 = new JTextField();
		textField_2.setBounds(130, 178, 235, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Sửa thành");
		lblNewLabel_1.setBounds(10, 181, 85, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("Giá nhập: ");
		label.setBounds(215, 94, 74, 14);
		frame.getContentPane().add(label);
	}
}
