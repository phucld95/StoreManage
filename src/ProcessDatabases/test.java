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
		System.out.println(checkTime2("2016-11-10", "2015-11-16"));
	}		
		
	private static int checkTime2 (String t1, String t2){
		int i;
		for(i=0; i<=9; i++){
			if(t1.charAt(i) > t2.charAt(i)) return 0;
		}
		return 1;
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
