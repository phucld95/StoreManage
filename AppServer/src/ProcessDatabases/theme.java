package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class theme {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					theme window = new theme();
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
	public theme() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 216, 155);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.setTitle("Server");
		frame.setResizable(false);
		
		JLabel lblMyChang = new JLabel("Máy chủ đang hoạt động!");
		lblMyChang.setBounds(10, 10, 180, 26);
		frame.getContentPane().add(lblMyChang);
		
		JLabel lblNewLabel = new JLabel("Phiên bản 1.0\r\n");
		lblNewLabel.setBounds(10, 42, 180, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPhtHnhNgy = new JLabel("Phát hành ngày 26/11/2015");
		lblPhtHnhNgy.setBounds(10, 74, 180, 26);
		frame.getContentPane().add(lblPhtHnhNgy);
	}
}
