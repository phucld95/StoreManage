package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ShowInvoice {

	private JFrame frmHan;
	private JTextField textField;
	private JTextField txtYyyymmdd;
	private JTextField txtYyyymmdd_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowInvoice window = new ShowInvoice();
					window.frmHan.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowInvoice() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHan = new JFrame();
		frmHan.setResizable(false);
		frmHan.setTitle("Hóa đơn");
		frmHan.setBounds(100, 100, 510, 700);
		frmHan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHan.getContentPane().setLayout(null);
		
		JLabel lblTiKhonThanh = new JLabel("Tài khoản thanh toán :");
		lblTiKhonThanh.setBounds(34, 26, 152, 14);
		frmHan.getContentPane().add(lblTiKhonThanh);
		
		textField = new JTextField();
		textField.setBounds(198, 23, 281, 20);
		frmHan.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblThiGianThanh = new JLabel("Thời gian thanh toán từ");
		lblThiGianThanh.setBounds(34, 57, 168, 14);
		frmHan.getContentPane().add(lblThiGianThanh);
		
		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setText("yyyy-mm-dd");
		txtYyyymmdd.setBounds(198, 54, 117, 20);
		frmHan.getContentPane().add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);
		
		JLabel lbln = new JLabel("đến");
		lbln.setBounds(325, 57, 44, 14);
		frmHan.getContentPane().add(lbln);
		
		txtYyyymmdd_1 = new JTextField();
		txtYyyymmdd_1.setText("yyyy-mm-dd");
		txtYyyymmdd_1.setBounds(362, 54, 117, 20);
		frmHan.getContentPane().add(txtYyyymmdd_1);
		txtYyyymmdd_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(198, 142, 227, 20);
		frmHan.getContentPane().add(comboBox);
		
		JLabel lblHanTm = new JLabel("Hóa đơn tìm được :");
		lblHanTm.setBounds(34, 145, 130, 14);
		frmHan.getContentPane().add(lblHanTm);
		
		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.setBounds(198, 100, 110, 23);
		frmHan.getContentPane().add(btnTmKim);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.setBounds(435, 141, 44, 23);
		frmHan.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 185, 455, 460);
		frmHan.getContentPane().add(scrollPane);
	}
}
