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
    
    
    public TableDatabase(ResultSet rs, String title) throws SQLException{
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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setIcon();
        this.setTitle(title);
    }
    
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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setIcon();
    }
    
    private void setIcon(){
    	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }

//    
}
