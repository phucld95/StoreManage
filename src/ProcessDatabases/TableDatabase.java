package ProcessDatabases;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTable;

public class TableDatabase extends JFrame{
    private JTable table;
    
    /*
     * Đoạn code dưới đây sẽ được viết trong func mà bạn muốn show ra bảng từ database
     */
	 
	//private static final String user = "root";
	//
	//private static final String password = "123456";
	//
	//public static void main(String [] args){
	// inputData();
	// 
	//}
	//
	//public static void inputData(){
	//	try {
	//   	// Connect to database and creat statement.
	//       java.sql.Connection con = DriverManager.getConnection(url, user, password);
	//       System.out.println("Connect Success!");
	//       
	//       Statement st = con.createStatement();
	//       st.executeUpdate ("Use lab;");
	//       
	//       ResultSet rs = st.executeQuery("select * from mat_hang;");
	//       SimpleTableTest stt = new SimpleTableTest(rs);
	//   } catch (Exception e) {
	//       e.printStackTrace();
	//   }
	//}
    
    public TableDatabase(ResultSet rs) throws SQLException{
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        //TableValues tv = new TableValues();
        table = new JTable();
        table.setModel(new rsTableModel(rs));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jsp = new JScrollPane(table);
        pane.add(jsp, BorderLayout.CENTER);
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setVisible(true); 
        this.setIcon();
    }
    
    private void setIcon(){
    	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }

//    
}
