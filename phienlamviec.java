package first;
import java.io.*;
import java.util.Scanner;

public class phienlamviec {
	public static void taofilekiemtra(){
		try {
			FileOutputStream fos= new FileOutputStream("test.txt",false);
			PrintWriter pw= new PrintWriter(fos);
	        pw.println(0);
	        pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void kiemtraphienlamviec(){
		/*
		 * kiem tra bang 1 file text(ten test.txt), ben trong ghi 0 neu chua co lan nao chay, ghi 1 neu da chay qua lan dau tien
		 * doc file, 
		 * + neu trong file ghi 0 thi chay class CreateDatabase.java, roi ghi lai vao file la 1
		 * + neu trong file ghi 1 thi bo qua.
		 */
		
		try {
			FileReader fr = new FileReader("test.txt");
			BufferedReader input= new BufferedReader(fr);
			try {
				
				String tmp = input.readLine();
				String a="0";
				if(a.equals(tmp)){
					 CreateDatabase.importdata();

					 FileOutputStream fos= new FileOutputStream("test.txt",false);
			         PrintWriter pw= new PrintWriter(fos);
			         pw.println(1);
			         pw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	public static void main(String[] args){
		int i;
		System.out.println("1.tao file kiem tra test.txt\n2.kiemtraphienlamviec\n nhap lua chon");
		Scanner nhap = new Scanner(System.in);
		i=nhap.nextInt();
		switch(i){
		case 1:{
			taofilekiemtra();
			break;
		}
		case 2:{
			kiemtraphienlamviec();
			break;
		}
		default :break;
		}
	}
}
