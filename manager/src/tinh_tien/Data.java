package tinh_tien;

import java.util.Scanner;

public class Data {
	public String name, nameGroup;
	public int so_luong, Gia_Ban, dot, Gia_Nhap,idGroup,id,Id_KM;
	public String inp = new String();
	public String temp = new String();
	public String Ten_KM = new String();
	public String TGDR = new String();
	public String TGKT = new String();
	public int sum = 0;
	private Scanner Input = new Scanner(System.in);

	public void InputName() {
		System.out.print("nhap ten:");
		name = Input.nextLine();
	}

	public void InputSum() {
		System.out.print("nhap so luong:");
		temp = Input.nextLine();
		so_luong = Integer.parseInt(temp);
	}

	public void InputID() {
		System.out.print("nhap ID:");
		temp = Input.nextLine();
		id = Integer.parseInt(temp);
	}

	public void Input_GiaNhap() {
		System.out.print("Gia nhap:");
		Gia_Nhap = Integer.parseInt(Input.nextLine());
	}

	public void Input_GiaBan() {
		System.out.print("Gia ban:");
		Gia_Ban=Integer.parseInt(Input.nextLine());
	}

	public void Input_idGroup() {
		System.out.print("id Group:");
		idGroup=Integer.parseInt(Input.nextLine());
	}
	public void Input_NameGroup() {
		System.out.print("nhap ten group:");
		nameGroup = Input.nextLine();
	}
	public void Input_IdKM() {
		System.out.print("nhap id dot khuyen mai:");
		Id_KM = Integer.parseInt(Input.nextLine());
	}
	public void Input_TenKM() {
		System.out.print("nhap ten dot khuyen mai:");
		Ten_KM = Input.nextLine();
	}
	public void Input_TGDR() {
		System.out.print("nhap TGDR (yyyy/mm/dd):");
		TGDR = Input.nextLine();
	}
	public void Input_TGKT() {
		System.out.print("nhap TGKT (yyyy/mm/dd):");
		TGKT = Input.nextLine();
	}
}