package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class test {

	private JFrame frmTestDcmm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frmTestDcmm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTestDcmm = new JFrame();
		frmTestDcmm.setTitle("test dcmm");
		frmTestDcmm.setResizable(false);
		frmTestDcmm.setBounds(100, 100, 450, 300);
		frmTestDcmm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTestDcmm.getContentPane().setLayout(null);
		
		JLabel lblDcmm = new JLabel("DCMM");
		lblDcmm.setBounds(78, 65, 46, 14);
		frmTestDcmm.getContentPane().add(lblDcmm);
		
		JLabel lblCh = new JLabel("Ch\u00FA \u00FD : Ph\u1EA3i c\u00E0i \u0111\u1EB7t ph\u1EA7n m\u1EC1m MySQL tr\u01B0\u1EDBc khi s\u1EED d\u1EE5ng ch\u01B0\u01A1ng tr\u00ECnh!");
		lblCh.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lblCh.setBounds(24, 143, 397, 21);
		frmTestDcmm.getContentPane().add(lblCh);
	}
}
