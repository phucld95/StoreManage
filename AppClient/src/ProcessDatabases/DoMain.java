package ProcessDatabases;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Formatter;
import java.util.Scanner;


public class DoMain {
	public static void main(String args[]) throws IOException, IOException {	
		Client cl = new Client();
		Layer1Interface ly1 = new Layer1Interface(cl);
        ly1.frame.setVisible(true);
	}
}