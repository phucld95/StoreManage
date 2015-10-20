package support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class demo {
	public static void main(String[] args){
		try {
			Process p=Runtime.getRuntime().exec("ls -l");
			BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
				String line = null;
			while ((line = in.readLine()) != null) {
    System.out.println(line);}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("1");
		}
	}
}
