package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class Casier {

	private JFrame frmThanhTon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Casier window = new Casier();
					window.frmThanhTon.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Casier() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThanhTon = new JFrame();
		frmThanhTon.setSize(800, 400);
        frmThanhTon.setVisible(true); 
        frmThanhTon.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmThanhTon.getContentPane().setLayout(null);
        
        JLabel lblThanhTonHa = new JLabel("Thanh to\u00E1n");
        lblThanhTonHa.setForeground(new Color(255, 51, 51));
        lblThanhTonHa.setFont(new Font("iCiel Cucho", Font.PLAIN, 70));
        lblThanhTonHa.setBounds(329, 11, 510, 161);
        frmThanhTon.getContentPane().add(lblThanhTonHa);
        
        JLabel lblNhnVinThu = new JLabel("Nh\u00E2n vi\u00EAn thu ng\u00E2n :");
        lblNhnVinThu.setBounds(869, 64, 150, 14);
        frmThanhTon.getContentPane().add(lblNhnVinThu);
        
        JLabel lblTiKhonNhn = new JLabel("T\u00E0i kho\u1EA3n nh\u00E2n vi\u00EAn :");
        lblTiKhonNhn.setBounds(869, 39, 150, 14);
        frmThanhTon.getContentPane().add(lblTiKhonNhn);
        
        JLabel lblThiGian = new JLabel("Th\u1EDDi gian :");
        lblThiGian.setBounds(869, 99, 150, 14);
        frmThanhTon.getContentPane().add(lblThiGian);
        
		
		
	}
}
