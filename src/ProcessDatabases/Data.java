package ProcessDatabases;
import java.util.Scanner;

public class Data {
   public String name,nameGroup;
   public int so_luong,Gia_Ban,dot;
   public String inp = new String();
   public String temp = new String();
   public int sum=0;
   public int id;
   private Scanner Input = new Scanner(System.in) ;
   public void InputName() {
       System.out.print("nhap ten:");
       name = Input.nextLine();
   }
   public void InputSum(){
	   System.out.print("nhap so luong:");
	   temp=Input.nextLine();
	   so_luong=Integer.parseInt(temp);
   }
   public void InputID(){
	   System.out.print("nhap ID:");
	   temp=Input.nextLine();
	   id=Integer.parseInt(temp);
   }
}