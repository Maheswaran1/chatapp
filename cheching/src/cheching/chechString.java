package cheching;

import java.util.Scanner;

public class chechString {

	public static void main(String[] args) {
		System.out.println("enter first");
			Scanner scan = new Scanner(System.in);
			System.out.println("enter second");
			String str = scan.nextLine();
			String str2 = scan.nextLine();
			
			if(str.equals(str2))
				System.out.println("matching");
	}

}
