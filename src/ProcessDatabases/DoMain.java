package ProcessDatabases;

import java.sql.*;
import java.util.Formatter;
import java.util.Scanner;

public class DoMain {
	public static void main(String args[]) {
        try {
			Layer2Interface window = new Layer2Interface();
			window.frmStoreManager.setVisible(true);}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}