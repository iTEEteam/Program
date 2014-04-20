package txtComparer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TxtComparer {

	public static void main(String[] args) {
		
		if(args.length != 2) {
			System.out.println("Nem megfelelo szamu parameter! A program parameterei a ket osszehasonlitando fajl neve.");
			System.exit(1);
		}
		
		BufferedReader file1 = null;
		BufferedReader file2 = null;

		try {
			file1 = new BufferedReader(new FileReader(args[0]));
			file2 = new BufferedReader(new FileReader(args[1]));
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			System.exit(2);
		}
		
		
		boolean equals = true;
		int whosLonger = 0;
		
		try {
			String line1 = null;
			String line2 = null;
			int lineNumber = 0;
			
			while(((line1 = file1.readLine()) != null) & ((line2 = file2.readLine()) != null)) {
				if(!line1.equals(line2)) {
					if(equals) {
						System.out.print("Eltero sor(ok):");
					}
					equals = false;
					System.out.print(" " + String.valueOf(lineNumber));
				}
				lineNumber++;
			}
			
			if(line1 != null) {
				whosLonger = 1;
			} else if(line2 != null) {
				whosLonger = 2;
			}
			
			System.out.println();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			System.exit(3);
		}
		
		try {
			file1.close();
			file2.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			System.exit(4);
		}
		
		if(whosLonger == 1) {
			System.out.println("A(z) " + args[0] + " hosszabb.");
		} else if(whosLonger == 2) {
			System.out.println("A(z) " + args[1] + " hosszabb.");
		} else if(equals && (whosLonger == 0)) {
			System.out.println("A ket fajl tartalma megegyezik.");
		} else {
			System.out.println("A ket fajl a fent megadott sorokban ter el.");
		}
	}

}
