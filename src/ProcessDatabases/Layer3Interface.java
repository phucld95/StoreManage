package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Font;
import java.sql.Statement;
import java.awt.Color;

public class Layer3Interface {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	private static Statement st;
	/**
	 * Launch the application.
	 */

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
		frame = new JFrame();
		frame.setBounds(100, 100, 757, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblThanhTonHa = new JLabel("Thanh to\u00E1n");
		lblThanhTonHa.setForeground(Color.GRAY);
		lblThanhTonHa.setFont(new Font("iCiel Cucho Bold", Font.PLAIN, 50));
		lblThanhTonHa.setBounds(74, 0, 395, 80);
		frame.getContentPane().add(lblThanhTonHa);
		
		JLabel lblNhnVin = new JLabel("Nh\u00E2n vi\u00EAn : ");
		lblNhnVin.setBounds(400, 23, 318, 14);
		frame.getContentPane().add(lblNhnVin);
		
		JLabel lblIdNhnVin = new JLabel("ID nh\u00E2n vi\u00EAn : ");
		lblIdNhnVin.setBounds(400, 50, 318, 14);
		frame.getContentPane().add(lblIdNhnVin);
		
		JLabel lblThiGian = new JLabel("Th\u1EDDi gian :");
		lblThiGian.setBounds(400, 72, 318, 14);
		frame.getContentPane().add(lblThiGian);
		
		JLabel lblTnSnPhm = new JLabel("T\u00EAn s\u1EA3n ph\u1EA9m :");
		lblTnSnPhm.setBounds(21, 100, 159, 14);
		frame.getContentPane().add(lblTnSnPhm);
		
		JLabel lblMSnPhm = new JLabel("M\u00E3 s\u1EA3n ph\u1EA9m :");
		lblMSnPhm.setBounds(21, 125, 159, 14);
		frame.getContentPane().add(lblMSnPhm);
		
		JLabel lblSLng = new JLabel("S\u1ED1 l\u01B0\u1EE3ng :");
		lblSLng.setBounds(21, 153, 159, 14);
		frame.getContentPane().add(lblSLng);
		
		textField = new JTextField();
		textField.setBounds(153, 97, 200, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(153, 122, 263, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(153, 147, 263, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Th\u00EAm v\u00E0o h\u00F3a \u0111\u01A1n");
		btnNewButton.setBounds(465, 107, 200, 51);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblMSnPhm_1 = new JLabel("M\u00E3 s\u1EA3n ph\u1EA9m mu\u1ED1n x\u00F3a :");
		lblMSnPhm_1.setBounds(23, 204, 159, 14);
		frame.getContentPane().add(lblMSnPhm_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(216, 201, 200, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnXaKhiHa = new JButton("X\u00F3a kh\u1ECFi h\u00F3a \u0111\u01A1n");
		btnXaKhiHa.setBounds(465, 200, 200, 23);
		frame.getContentPane().add(btnXaKhiHa);
		
		table = new JTable();
		table.setBounds(21, 246, 697, 235);
		frame.getContentPane().add(table);
		
		JList list = new JList();
		list.setBounds(703, 245, 15, 167);
		frame.getContentPane().add(list);
		
		JButton btnThanhTon = new JButton("Thanh to\u00E1n");
		btnThanhTon.setBounds(236, 492, 271, 56);
		frame.getContentPane().add(btnThanhTon);
		
		JButton btnNewButton_1 = new JButton(">\r\n");
		btnNewButton_1.setBounds(366, 96, 51, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
