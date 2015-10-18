package ProcessDatabases;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timeSystem {
	public static String Date(){
		//lấy thời gian hệ thống
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // tạo 1 đối tượng có định dạng thời gian yyyy-MM-dd HH:mm:ss
        Date date = new Date(); // lấy thời gian hệ thống
        String stringDate = dateFormat.format(date);//Định dạng thời gian theo trên
        //System.out.println("Date: " + stringDate);
        return stringDate;
	}
	public static void PrintDate(){
		//lấy thời gian hệ thống
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // tạo 1 đối tượng có định dạng thời gian yyyy-MM-dd HH:mm:ss
        Date date = new Date(); // lấy thời gian hệ thống
        String PDate = dateFormat.format(date);//Định dạng thời gian theo trên
        System.out.println("Date: " + PDate);
	}
}
