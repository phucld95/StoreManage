package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FixSupplyProduce {

	private JFrame frmSaThngTin;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FixSupplyProduce window = new FixSupplyProduce();
					window.frmSaThngTin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FixSupplyProduce() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSaThngTin = new JFrame();
		frmSaThngTin.setTitle("S\u1EEDa th\u00F4ng tin nh\u1EADp h\u00E0ng.");
		frmSaThngTin.setResizable(false);
		frmSaThngTin.setBounds(100, 100, 450, 300);
		frmSaThngTin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSaThngTin.getContentPane().setLayout(null);
		
		JLabel lblNhpThngTin = new JLabel("Nh\u1EADp th\u00F4ng tin s\u1EA3n ph\u1EA9m :");
		lblNhpThngTin.setBounds(25, 21, 145, 14);
		frmSaThngTin.getContentPane().add(lblNhpThngTin);
		
		JLabel lblTnSnPhm = new JLabel("T\u00EAn s\u1EA3n ph\u1EA9m :");
		lblTnSnPhm.setBounds(46, 44, 124, 14);
		frmSaThngTin.getContentPane().add(lblTnSnPhm);
		
		textField = new JTextField();
		textField.setBounds(146, 38, 191, 20);
		frmSaThngTin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblMSnPhm = new JLabel("M\u00E3 s\u1EA3n ph\u1EA9m :");
		lblMSnPhm.setBounds(46, 74, 124, 14);
		frmSaThngTin.getContentPane().add(lblMSnPhm);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 69, 191, 20);
		frmSaThngTin.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(360, 44, 63, 37);
		frmSaThngTin.getContentPane().add(btnNewButton);
	}
}
