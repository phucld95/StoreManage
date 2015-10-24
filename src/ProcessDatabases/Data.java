package ProcessDatabases;

import java.util.Scanner;

public class Data {
	public String name, nameGroup;
	public int so_luong, Gia_Ban, dot, Gia_Nhap,idGroup,id,Id_KM,Id_NCC,Gia_KM,SDT,ID_Account;
	public int[] id_KM = new int[100];
	public String inp = new String();
	public String temp = new String();
	public String Ten_KM = new String();
	public String TGDR = new String();
	public String TGKT = new String();
	public String Ten_NCC = new String();
	public String TTLH = new String();
	public String Mail = new String();
	public String time = new String();
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
		System.out.print("nhap ID mat hang:");
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
	public void Input_IdNCC(){
		System.out.print("Nhap ID NCC:");
		Id_NCC=Integer.parseInt(Input.nextLine());
	}

	public void Input_TenNCC() {
		System.out.print("nhap ten NCC:");
		Ten_NCC = Input.nextLine();
	}

	public void Input_TTLH() {
		System.out.print("nhap thong tin lien he:");
		TTLH = Input.nextLine();
	}
	public void Input_GiaKM(){
		System.out.print("Nhap gia khuyen mai:");
		Gia_KM=Integer.parseInt(Input.nextLine());
	}
	public void Input_SDT() {
		System.out.print("nhap SDT nha cung cap:");
		SDT = Integer.parseInt(Input.nextLine());
	}
	public void Input_Mail() {
		System.out.print("nhap mail nhac cung cap:");
		Mail = Input.nextLine();
	}
}