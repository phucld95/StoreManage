package tinh_tien;

import java.util.Scanner;

public class Data {
   public String name;
   public int so_luong=0,sum=0;
   private Scanner Input ;
   public void InputName() {
	   Input = new Scanner(System.in);
       System.out.print("nhap ten mat hang:");
       name = Input.nextLine();
   }
   public void InputSum(){
	   System.out.print("nhap so luong:");
	   so_luong=Input.nextInt();
   }
}