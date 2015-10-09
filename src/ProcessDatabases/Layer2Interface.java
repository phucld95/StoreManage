package ProcessDatabases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Layer2Interface implements ActionListener {

	public static JFrame frmStoreManager;

	/**
	 * Create the application.
	 */
	public Layer2Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStoreManager = new JFrame();
		frmStoreManager.setTitle("Store manager");
		frmStoreManager.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Java\\Project\\store_manager\\Untitled-1-01.png"));
		frmStoreManager.setBounds(100, 100, 529, 300);
		frmStoreManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStoreManager.getContentPane().setLayout(null);
		
		JLabel lblStoreManager = new JLabel("Store Manager");
		lblStoreManager.setForeground(Color.DARK_GRAY);
		lblStoreManager.setFont(new Font("iCiel Cucho Bold", Font.PLAIN, 34));
		lblStoreManager.setBounds(157, 25, 214, 50);
		frmStoreManager.getContentPane().add(lblStoreManager);
		
		JLabel lblQunLMt = new JLabel("Qu\u1EA3n l\u00FD m\u1EB7t h\u00E0ng");
		lblQunLMt.setBounds(10, 84, 146, 14);
		frmStoreManager.getContentPane().add(lblQunLMt);
		/*
		 *	ComboBox Các mặt hàng đang kinh doanh.
		 */
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"C\u00E1c m\u1EB7t h\u00E0ng \u0111ang kinh doanh.", "T\u00ECm ki\u1EBFm, ch\u1EC9nh s\u1EEDa 1 s\u1EA3n ph\u1EA9m.", "Th\u00EAm 1 m\u1EB7t h\u00E0ng v\u00E0o c\u1EEDa h\u00E0ng.", "X\u00F3a1 m\u1EB7t h\u00E0ng \u0111\u00E3 c\u00F3.", "Nh\u1EEFng s\u1EA3n ph\u1EA9m s\u1EAFp h\u1EBFt h\u00E0ng."}));
		comboBox.setToolTipText("");
		comboBox.setBounds(180, 81, 230, 20);
		frmStoreManager.getContentPane().add(comboBox);
		
		JLabel lblQunLNh = new JLabel("Qu\u1EA3n l\u00FD nh\u00E0 ph\u00E2n ph\u1ED1i");
		lblQunLNh.setBounds(10, 114, 146, 14);
		frmStoreManager.getContentPane().add(lblQunLNh);
		
		JLabel lblNewLabel = new JLabel("Qu\u1EA3n l\u00FD nh\u00F3m h\u00E0ng");
		lblNewLabel.setBounds(10, 143, 146, 14);
		frmStoreManager.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Qu\u1EA3n l\u00FD s\u1EF1 ki\u1EC7n khuy\u1EBFn m\u1EA1i");
		lblNewLabel_1.setBounds(10, 173, 146, 14);
		frmStoreManager.getContentPane().add(lblNewLabel_1);
		
		/*
		 * 	ComboBox Các nhà cung cấp
		 */
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Tra c\u1EE9u th\u00F4ng tin c\u1EE7a 1 nh\u00E0 cung c\u1EA5p.", "Th\u00EAm nh\u00E0 cung c\u1EA5p.", "X\u00F3a nh\u00E0 cung c\u1EA5p."}));
		comboBox_1.setBounds(180, 111, 230, 20);
		frmStoreManager.getContentPane().add(comboBox_1);
		
		/*
		 * 	ComboBox tra cứu nhóm hàng.
		 */
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Tra c\u1EE9u 1 nh\u00F3m h\u00E0ng.", "Th\u00EAm nh\u00F3m h\u00E0ng."}));
		comboBox_2.setBounds(180, 140, 230, 20);
		frmStoreManager.getContentPane().add(comboBox_2);
		
		/*
		 *	Combobox Tra cứu sự kiện.
		 */
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Xem c\u00E1c s\u1EF1 ki\u1EC7n \u0111ang di\u1EC5n ra.", "Th\u00EAm 1 s\u1EF1 ki\u1EC7n."}));
		comboBox_3.setBounds(180, 170, 230, 20);
		frmStoreManager.getContentPane().add(comboBox_3);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(420, 81, 41, 20);
		frmStoreManager.getContentPane().add(btnNewButton);
		
		// Bắt sự kiện chọn item combobox
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString());
            }
        });
		
		JButton button = new JButton(">");
		button.setBounds(420, 111, 41, 20);
		frmStoreManager.getContentPane().add(button);
		
		JButton button_1 = new JButton(">");
		button_1.setBounds(420, 140, 41, 20);
		frmStoreManager.getContentPane().add(button_1);
		
		JButton button_2 = new JButton(">");
		button_2.setBounds(420, 170, 41, 20);
		frmStoreManager.getContentPane().add(button_2);
	}

	/*
	 * button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==button) System.exit(0); //nếu event này có source do button sinh ra
            }
        });
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, comboBox_1.getSelectedItem().toString);
	}
}
