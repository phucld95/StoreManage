package ProcessDatabases;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Layer1Interface {

	public static JFrame frame;
	private JTextField textField;
	public static String acc = "";
	public static String pass = "";
	public static String accS = "1";
	public static String passS = "1";
	public static String sql;
	
	private JPasswordField passwordField;
	private static Statement st;
	private static ResultSet rs;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Layer1Interface(Statement sts) {
		st = sts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		//frame.setVisible(true);
		frame.setBounds(100, 100, 450, 229);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblngNhp = new JLabel("Nh\u1EADp th\u00F4ng tin ng\u01B0\u1EDDi s\u1EED d\u1EE5ng :");
		lblngNhp.setBounds(31, 26, 179, 14);
		frame.getContentPane().add(lblngNhp);
		
		JLabel lblTnNgiDng = new JLabel("T\u00EAn ng\u01B0\u1EDDi d\u00F9ng :");
		lblTnNgiDng.setBounds(41, 53, 122, 14);
		frame.getContentPane().add(lblTnNgiDng);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u :");
		lblMtKhu.setBounds(41, 97, 95, 14);
		frame.getContentPane().add(lblMtKhu);
		
		textField = new JTextField();
		textField.setBounds(173, 50, 218, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u0110\u0103ng nh\u1EADp");
		btnNewButton.setBounds(147, 139, 159, 23);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(173, 91, 218, 20);
		frame.getContentPane().add(passwordField);
		btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                acc = textField.getText();
                pass = passwordField.getText();
                if(acc.length() == 0 || pass.length() == 0){
                	JOptionPane.showMessageDialog(null, "Không được để trống thông tin!");
                }
         
            }
        });	
	}
	
	public static int testString(String s1, String s2){
		int i;
		if(s1.length() != s2.length()){
			//System.out.print("Bang 0");
			return 0;
		}
		for(i=0; i<s1.length(); i++){
			if(s1.charAt(i) != s2.charAt(i)){
				//System.out.print("Bang 02");
				return 0;
			}
		}
		return 1;
	}
	
	public static int check(){		
		sql = "select username, password from account where username = '" + acc + "';";
		try {
			rs = st.executeQuery(sql);
			while( rs.next()) {
				accS = rs.getString("username");
				passS = rs.getString("password");
				//System.out.println("|"+pass+"|" + passS+"|");
				if (testString(pass, passS) == 1 && testString(acc, "admin") == 1){
						return 2;
				}
				if (testString(pass, passS) == 1 && testString(acc, "admin") != 1)return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/*
	 * Return account in account field;
	 */
	public static String getAcc(){
		return acc;
	}
	/*
	 * Return password in password field;
	 */
	public static String getPass(){
		return pass;
	}
}
