package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class test {

	private JFrame frmTestDcmm;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		Random rn = new Random();
		for (int i = 0; i < 10000; i++) {
			System.out.println("(" + rn.nextInt(1000000) + "," + rn.nextInt() + "," + rn.nextInt() +"),");
		}
		
		
//	
//		String k = "12";
//		System.out.println((int)(k.charAt(0)-48));
//		System.out.println(convertStringToIn("12"));
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					test window = new test();
//					window.frmTestDcmm.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	private static int convertStringToIn(String str){
		int sum = 0;
		int i = str.length();
		for(int j=0;j<i;j++){
			sum += (int)(str.charAt(j)-48)*Math.pow(10, i-j-1);
		}
		return sum;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(46, 45, 330, 152);
		frmTestDcmm.getContentPane().add(scrollPane);
	}
}
