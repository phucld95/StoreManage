/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_JDBC;

import java.util.Scanner;

/**
 *
 * @author luatnguyen88
 */
public class Tinh_Tien {
     String name;
     int so_luong=0;
    public static void main(String[] args) {
            
    }

    public static Tinh_Tien Tinh_Tien() {
        Tinh_Tien tinh=new Tinh_Tien();
        Scanner Input = new Scanner(System.in);
        System.out.print("nhap ten mat hang:");
        tinh.name = Input.nextLine();
        System.out.println(tinh.name);
        System.out.print("nhap so luong:");
        tinh.so_luong=Input.nextInt();
        return tinh;
    }
}
